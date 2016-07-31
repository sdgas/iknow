package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.TemplateVO;
import org.sdgas.base.PageView;
import org.sdgas.model.Template;
import org.sdgas.model.UserInfo;
import org.sdgas.service.TemplateService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wilson.he on 2016/7/27.
 */

@Component("template")
@Scope("prototype")
public class TemplateAction extends MyActionSupport implements ModelDriven<TemplateVO> {

    private TemplateVO templateVO = new TemplateVO();
    private TemplateService templateService;

    private static final Logger logger = LogManager.getLogger(TemplateAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");

    //新增模板
    @Override
    public String execute() {

        Template template = new Template();
        template.setContent(templateVO.getContent());
        template.setCreator(templateVO.getCreator());
        template.setSubject(templateVO.getSubject());
        template.setUsed(true);
        template.setCreateTime(ChangeTime.parseStringToShortDate(null));

        try {
            templateService.save(template);
        } catch (Exception e) {
            logger.error(e);
            templateVO.setResultMessage("<script>alert(' 模板保存失败！');location.href='/page/template/addTemplate.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则模板信息（模板ID：" + template.getTemplateId() + ")");
        templateVO.setResultMessage("<script>alert(' 模板保存成功！');location.href='/page/template/addTemplate.jsp';</script>");
        return SUCCESS;
    }

    //查找模板
    public String findTemplateByField() {
        if (UserUtil.checkUserLogIn(user)) {
            templateVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<Template> pageView = new PageView<Template>(maxResult,
                templateVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("createTime", "DESC");
        /** 列表条件 **/
        String temp = "subject like '%" + templateVO.getMsg().trim() + "%' or content like '%" + templateVO.getMsg().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);

        if (Integer.valueOf(templateVO.getUsed().trim()) == 0)
            jpql.append("and used = 0");
        else if (Integer.valueOf(templateVO.getUsed().trim()) == 1)
            jpql.append("and used = 1");

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(templateService.getScrollData(Template.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        templateVO.setPageView(pageView);

        view = "/page/template/search.jsp?msg=" + templateVO.getMsg() + "&used=" + templateVO.getUsed();
        return VIEW;
    }


    @Override
    public TemplateVO getModel() {
        return templateVO;
    }

    @Resource(name = "templateServiceImpl")
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
