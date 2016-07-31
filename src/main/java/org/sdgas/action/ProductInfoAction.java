package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.ProductInfoVO;
import org.sdgas.base.PageView;
import org.sdgas.model.ProductInfo;
import org.sdgas.model.UserInfo;
import org.sdgas.service.ProductInfoService;
import org.sdgas.util.FileUtils;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Component("productInfo")
@Scope("prototype")
public class ProductInfoAction extends MyActionSupport implements ModelDriven<ProductInfoVO> {

    ProductInfoVO productInfoVO = new ProductInfoVO();
    private ProductInfoService productInfoService;

    private static String SAVE_PATH_DIR = "D:/iKnow/img/";

    private static final Logger logger = LogManager.getLogger(ProductInfoAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");

    //新增楼宇收费标准
    @Override
    public String execute() {

        ProductInfo productInfo = productInfoService.findById(productInfoVO.getModelNumber().trim());
        if (productInfo != null) {
            productInfoVO.setResultMessage("<script>alert('该型号的燃气具已存在！');location.href='/page/furnace/addInfo.jsp';</script>");
            return ERROR;
        }

        String name = FileUtils.uploadAttachment(productInfoVO.getUploadFile(), productInfoVO.getFileName(), SAVE_PATH_DIR);

        productInfo = new ProductInfo();
        productInfo.setImg(name);
        productInfo.setInformation(productInfoVO.getInformation());
        productInfo.setModelNumber(productInfoVO.getModelNumber());

        try {
            productInfoService.save(productInfo);
        } catch (Exception e) {
            logger.error(e);
            productInfoVO.setResultMessage("<script>alert('该燃气具产品信息保存失败！');location.href='/page/furnace/addInfo.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则燃气具产品信息（产品ID：" + productInfo.getModelNumber() + ")");
        productInfoVO.setResultMessage("<script>alert('燃气具产品信息保存成功！');location.href='/page/furnace/addInfo.jsp';</script>");
        return SUCCESS;
    }

    public String findProductByField() {
        if (UserUtil.checkUserLogIn(user)) {
            productInfoVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<ProductInfo> pageView = new PageView<ProductInfo>(maxResult,
                productInfoVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("modelNumber", "DESC");
        /** 列表条件 **/
        String temp = "modelNumber like '%" + productInfoVO.getMsg().trim() + "%' or information like '%" + productInfoVO.getMsg().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(productInfoService.getScrollData(ProductInfo.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        productInfoVO.setPageView(pageView);

        view = "/page/furnace/search.jsp?msg=" + productInfoVO.getMsg().trim();
        return VIEW;
    }

    @Resource(name = "productInfoServiceImpl")
    public void setProductInfoService(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @Override
    public ProductInfoVO getModel() {
        return productInfoVO;
    }
}
