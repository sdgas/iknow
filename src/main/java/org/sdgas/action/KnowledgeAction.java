package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.KnowledgeVO;
import org.sdgas.base.PageView;
import org.sdgas.model.Knowledge;
import org.sdgas.model.UserInfo;
import org.sdgas.service.KnowledgeService;
import org.sdgas.util.FileUtils;
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

@Component("knowledge")
@Scope("prototype")
public class KnowledgeAction  extends MyActionSupport implements ModelDriven<KnowledgeVO> {

    private KnowledgeVO knowledgeVO = new KnowledgeVO();
    private KnowledgeService knowledgeService;

    private static final Logger logger = LogManager.getLogger(KnowledgeAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");


    @Override
    public String execute() {

        Knowledge knowledge = new Knowledge();
        knowledge.setSubject(knowledgeVO.getSubject());
        knowledge.setKnowledge(knowledgeVO.getKnowledge());

        try {
            knowledgeService.save(knowledge);
        } catch (Exception e) {
            logger.error(e);
            knowledgeVO.setResultMessage("<script>alert('安检、隐患整改知识保存失败！');location.href='/page/knowledge/addInfo.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则安检、隐患整改知识信息（知识ID：" + knowledge.getKnowledgeId() + ")");
        knowledgeVO.setResultMessage("<script>alert('安检、隐患整改知识信息保存成功！');location.href='/page/knowledge/addInfo.jsp';</script>");
        return SUCCESS;
    }

    public String findByField() {
        if (UserUtil.checkUserLogIn(user)) {
            knowledgeVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<Knowledge> pageView = new PageView<Knowledge>(maxResult,
                knowledgeVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("knowledgeId", "DESC");
        /** 列表条件 **/
        String temp = "subject like '%" + knowledgeVO.getMsg().trim() + "%' or knowledge like '%" + knowledgeVO.getMsg().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);

        if (!knowledgeVO.getSubject().trim().equals(""))
            jpql.append(" and subject = '" + knowledgeVO.getSubject().trim() + "'");

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(knowledgeService.getScrollData(Knowledge.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        knowledgeVO.setPageView(pageView);

        view = "/page/knowledge/search.jsp?msg=" + knowledgeVO.getMsg().trim()+"&subject="+ knowledgeVO.getMsg().trim();
        return VIEW;
    }

    @Override
    public KnowledgeVO getModel() {
        return knowledgeVO;
    }

    @Resource(name = "knowledgeServiceImpl")
    public void setKnowledgeService(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }
}
