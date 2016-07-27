package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Template;

import java.util.List;

/**
 * Created by wilson.he on 2016/7/27.
 */
public interface TemplateService extends DAO {

    /**
     * 根据模板编号查找
     * @param templateId 模板编号
     * @return 模板
     */
    public Template findById(int templateId);
}
