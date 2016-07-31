package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.ProductInfo;

/**
 * Created by wilson.he on 2016/7/31.
 */
public interface ProductInfoService extends DAO {

    /**
     * 根据型号查找
     * @param modelNumber  产品型号
     * @return  产品
     */
    public ProductInfo findById(String modelNumber);
}
