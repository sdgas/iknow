package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Entity
public class ProductInfo {

    /**
     * 型号
     */
    private String modelNumber;


    /**
     * 产品介绍
     */
    private String information;

    /**
     * 图片
     */
    private String img;

    @Id
    @Column(length = 20)
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
