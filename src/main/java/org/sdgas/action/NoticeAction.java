package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.NoticeVO;
import org.sdgas.base.PageView;
import org.sdgas.model.Notice;
import org.sdgas.model.UserInfo;
import org.sdgas.service.NoticeService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Component("notice")
@Scope("prototype")
public class NoticeAction extends MyActionSupport implements ModelDriven<NoticeVO> {

    private NoticeVO noticeVO = new NoticeVO();

    private NoticeService noticeService;

    private static final Logger logger = LogManager.getLogger(NoticeAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");

    //增加公告栏信息
    @Override
    public String execute() {
        Notice notice = new Notice();
        notice.setCreator(noticeVO.getCreator());
        notice.setNoticeContent(noticeVO.getNoticeContent());
        notice.setNoticeGroup(noticeVO.getNoticeGroup());
        notice.setCreateTime(ChangeTime.parseStringToShortDate(null));
        notice.setNoticeType(noticeVO.getNoticeType());

        try {
            noticeService.save(notice);
        } catch (Exception e) {
            logger.error(e);
            noticeVO.setResultMessage("<script>alert(' 公告信息保存失败！');location.href='/page/notice/addNotice.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则公告信息（模板ID：" + notice.getNoticeId() + ")");
        noticeVO.setResultMessage("<script>alert(' 公告信息保存成功！');location.href='/page/notice/addNotice.jsp';</script>");
        return SUCCESS;
    }

    //查找公告
    public String findNoticeByField() {
        if (UserUtil.checkUserLogIn(user)) {
            noticeVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<Notice> pageView = new PageView<Notice>(maxResult,
                noticeVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("createTime", "DESC");
        /** 列表条件 **/
        String temp = "noticeContent like '%" + noticeVO.getMsg().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);

        if (!noticeVO.getNoticeType().trim().equals(""))
            jpql.append(" and noticeType = '" + noticeVO.getNoticeType().trim() + "'");


        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(noticeService.getScrollData(Notice.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        noticeVO.setPageView(pageView);

        view = "/page/notice/search.jsp?msg=" + noticeVO.getMsg() + "&noticeType=" + noticeVO.getNoticeType().trim();
        return VIEW;
    }

    @Resource(name = "noticeServiceImpl")
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Override
    public NoticeVO getModel() {
        return noticeVO;
    }
}
