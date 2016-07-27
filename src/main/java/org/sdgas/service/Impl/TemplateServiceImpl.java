package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Template;
import org.sdgas.service.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wilson.he on 2016/7/27.
 */

@Service
@Transactional
public class TemplateServiceImpl extends DaoSupport implements TemplateService {

    @Override
    public Template findById(int templateId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("templateId", templateId);
        return (Template) this.findSpecialObject(Template.class, params);
    }

    @Override
    public List<Template> findByContent(String msg) {
        Query query = em.createQuery("from Template t where t.subject like '%" + msg + "%' or t.content like '%" + msg + "%'");
        return query.getResultList();
    }
}
