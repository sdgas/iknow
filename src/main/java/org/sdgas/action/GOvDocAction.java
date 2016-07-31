package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.GovDocVO;
import org.sdgas.base.PageView;
import org.sdgas.model.GovDoc;
import org.sdgas.model.UserInfo;
import org.sdgas.service.GovDocService;
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

@Component("GovDoc")
@Scope("prototype")
public class GovDocAction extends MyActionSupport implements ModelDriven<GovDocVO> {

    private GovDocVO govDocVO = new GovDocVO();
    private GovDocService govDocService;

    private static String SAVE_PATH_DIR = "D:/iKnow/gov/";

    private static final Logger logger = LogManager.getLogger(GovDocAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");

    //新增相关文件
    @Override
    public String execute() {

        String name = FileUtils.uploadAttachment(govDocVO.getUploadFile(), govDocVO.getFileName(), SAVE_PATH_DIR);

        GovDoc govDoc = new GovDoc();
        govDoc.setFile(name);
        govDoc.setSubject(govDocVO.getSubject());

        try {
            govDocService.save(govDoc);
        } catch (Exception e) {
            logger.error(e);
            govDocVO.setResultMessage("<script>alert('文件信息保存失败！');location.href='/page/gov/addInfo.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则文件信息（产品ID：" + govDoc.getDocId() + ")");
        govDocVO.setResultMessage("<script>alert('政府文件息保存成功！');location.href='/page/gov/addInfo.jsp';</script>");
        return SUCCESS;
    }

    public String findByField() {
        if (UserUtil.checkUserLogIn(user)) {
            govDocVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<GovDoc> pageView = new PageView<GovDoc>(maxResult,
                govDocVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("docId", "DESC");
        /** 列表条件 **/
        String temp = "subject like '%" + govDocVO.getSubject().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(govDocService.getScrollData(GovDoc.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        govDocVO.setPageView(pageView);

        view = "/page/gov/search.jsp?subject=" + govDocVO.getSubject().trim();
        return VIEW;
    }

    @Override
    public GovDocVO getModel() {
        return govDocVO;
    }

    @Resource(name = "govDocServiceImpl")
    public void setGovDocService(GovDocService govDocService) {
        this.govDocService = govDocService;
    }
}
