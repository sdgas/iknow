package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.TemplateVO;
import org.sdgas.model.Template;
import org.sdgas.model.UserInfo;
import org.sdgas.service.TemplateService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.WebTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    @Override
    public String execute(){

        Template template = new Template();
        template.setContent(templateVO.getContent());
        template.setCreator(templateVO.getCreator());
        template.setSubject(templateVO.getSubject());
        template.setUsed(true);
        template.setCreateTime(ChangeTime.parseStringToShortDate(null));

        try{
            templateService.save(template);
        }catch (Exception e){
            logger.error(e);
            templateVO.setResultMessage("<script>alert(' 模板保存失败！');location.href='/page/template/addTemplate.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:"+user.getUserName()+",新增一则模板信息（模板ID："+template.getTemplateId()+")");
        templateVO.setResultMessage("<script>alert(' 模板保存成功！');location.href='/page/template/addTemplate.jsp';</script>");
        return SUCCESS;
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
