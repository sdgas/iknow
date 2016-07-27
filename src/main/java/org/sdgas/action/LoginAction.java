package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.UserInfoVO;
import org.sdgas.model.UserInfo;
import org.sdgas.service.UserInfoService;
import org.sdgas.util.SystemHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 120378 on 2015-03-05.
 */
@Component("login")
@Scope("prototype")
public class LoginAction extends MyActionSupport implements ModelDriven<UserInfoVO> {

    private UserInfoVO userVO = new UserInfoVO();
    private UserInfoService userService;

    private static String ip = "";

    private static final Logger logger = LogManager.getLogger(LoginAction.class);

    //用户登录
    @Override
    public String execute() throws Exception {
        if (userVO.getUserId().isEmpty() || userVO.getPwd().isEmpty()) {
            userVO.setResultMessage("<script>alert('请填写工号或用户密码！');location.href='login.jsp';</script>");
            return ERROR;
        }

        UserInfo user = userService.findById(userVO.getUserId());
        if (user == null) {
            userVO.setResultMessage("<script>alert('用户不存在！');location.href='login.jsp';</script>");
            return ERROR;
        }
        logger.entry(user.getUserName());

        if (userVO.getPwd().equals(user.getPwd())) {

            storePersonToSession(user);
            logger.info(user.getPosition() + " " + user.getUserName() + ",登录系统 IP:" + ip);


            HttpServletRequest request = ServletActionContext.getRequest();
            Object obj = request.getSession().getAttribute("requestURI");
            if (obj != null) {
                view = obj.toString();
            } else {
                view = "/index.jsp";
                return VIEW;
            }
            logger.trace(obj.toString());
            return VIEW;
        }
        logger.info("用户:" + user.getUserName() + "登录密码错误！");
        logger.exit(user.getUserName());
        userVO.setResultMessage("<script>alert('密码错误！');location.href='login.jsp';</script>");
        return ERROR;
    }

    //退出登录
    public String loginOut() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserInfo person = (UserInfo) session.getAttribute("person");
        if (person != null) {
            logger.info(person.getPosition() + " " + person.getUserName() + "，登出系统");
            session.removeAttribute("person");
            //session.invalidate();
        }
        view = "login.jsp";
        return VIEW;
    }


    //修改密码
    public String alterPwd() {

        UserInfo user = userService.findById(userVO.getUserId());
        if (!user.getPwd().equals(userVO.getPwd())) {
            userVO.setResultMessage("<script>alert('原密码错误');location.href='/page/user/alterPwd.jsp';</script>");
            logger.info(user.getPosition() + ":" + user.getUserName() + " 修改用户密码，原密码错误）操作IP：" + ip);
            return ERROR;
        } else {
            user.setPwd(userVO.getPwd1());
            userService.update(user);
            userVO.setResultMessage("<script>alert('修改密码成功！请重新登录系统');location.href='login.jsp';</script>");
            logger.info(user.getPosition()+ ":" + user.getUserName() + " 修改用户密码成功。操作IP：" + ip);
            return SUCCESS;
        }
    }

    //todo
    public String addUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserInfo person = (UserInfo) session.getAttribute("person");

        UserInfo user = userService.findById(userVO.getUserId());
        if (user != null) {
            userVO.setResultMessage("<script>alert('该用户已存在，请直接登录');location.href='/page/user/addPerson.jsp';</script>");
            logger.info("用户：" + user.getUserId() + "已存在，添加用户失败。IP：" + ip);
            return ERROR;
        }

        UserInfo newUser = new UserInfo();
        newUser.setUserId(userVO.getUserId());
        newUser.setUserName(userVO.getUserName());
        newUser.setPwd("000000");//默认密码：000000
        newUser.setPosition(Integer.valueOf(userVO.getPosition()));
        userService.save(newUser);
        userVO.setResultMessage("<script>alert('用户添加成功！默认密码：000000');location.href='/page/user/addPerson.jsp';</script>");
        logger.info("用户：" + person.getUserId() + " 成功添加新用户：" + newUser.getUserId() + "  IP：" + ip);
        return SUCCESS;
    }

    @Override
    public UserInfoVO getModel() {
        return userVO;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserService(UserInfoService userService) {
        this.userService = userService;
    }

    private void storePersonToSession(UserInfo target) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserInfo storedPerson = (UserInfo) session.getAttribute("person");
        ip = SystemHelper.getIpAddress();
        if (!ip.trim().equals("") && (storedPerson == null || !storedPerson.equals(target))) {
            session.setAttribute("person", target);
            session.setAttribute("ip", ip);
            session.setAttribute("loginKey", "success");
            session.setMaxInactiveInterval(-1);
        }
    }
}