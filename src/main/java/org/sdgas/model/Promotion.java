package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Entity
public class Promotion {

    /**
     * 活动编号
     */
    private int promotionId;

    /**
     * 活动内容
     */
    private String promotion;

    /**
     *   上传时间
     */
    private Date createTime;

    @Id
    @GeneratedValue
    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    @Column(length = 1000,nullable = false)
    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
