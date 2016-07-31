package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.PromotionVO;
import org.sdgas.base.PageView;
import org.sdgas.model.Promotion;
import org.sdgas.model.UserInfo;
import org.sdgas.service.PromotionService;
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

@Component("promotion")
@Scope("prototype")
public class PromotionAction extends MyActionSupport implements ModelDriven<PromotionVO> {

    private PromotionVO promotionVO = new PromotionVO();
    private PromotionService promotionService;

    private static final Logger logger = LogManager.getLogger(PromotionAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");

    //增加促销信息
    @Override
    public String execute(){
        Promotion promotion = new Promotion();
        promotion.setPromotion(promotionVO.getPromotion());
        promotion.setCreateTime(ChangeTime.parseStringToShortDate(null));

        try {
            promotionService.save(promotion);
        } catch (Exception e) {
            logger.error(e);
            promotionVO.setResultMessage("<script>alert(' 促销信息保存失败！');location.href='/page/promotion/addInfo.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则促销信息（促销ID：" + promotion.getPromotionId() + ")");
        promotionVO.setResultMessage("<script>alert(' 促销信息保存成功！');location.href='/page/promotion/addInfo.jsp';</script>");
        return SUCCESS;
    }

    //查找公告
    public String findPromotionByField() {
        if (UserUtil.checkUserLogIn(user)) {
            promotionVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<Promotion> pageView = new PageView<Promotion>(maxResult,
                promotionVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("createTime", "DESC");
        /** 列表条件 **/
        String temp = "promotion like '%" + promotionVO.getPromotion().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);


        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(promotionService.getScrollData(Promotion.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        promotionVO.setPageView(pageView);

        view = "/page/promotion/search.jsp?promotion=" + promotionVO.getPromotion().trim();
        return VIEW;
    }


    @Override
    public PromotionVO getModel() {
        return promotionVO;
    }

    @Resource(name = "promotionServiceImpl")
    public void setPromotionService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
}
