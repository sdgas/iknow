package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.ProductInfo;
import org.sdgas.service.ProductInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wilson.he on 2016/7/31.
 */

@Service
@Transactional
public class ProductInfoServiceImpl extends DaoSupport implements ProductInfoService {


    @Override
    public ProductInfo findById(String modelNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("modelNumber", modelNumber);
        return (ProductInfo) this.findSpecialObject(ProductInfo.class, params);
    }
}
