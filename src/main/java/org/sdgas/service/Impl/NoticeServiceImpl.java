package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Notice;
import org.sdgas.model.Template;
import org.sdgas.service.NoticeService;
import org.sdgas.service.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wilson.he on 2016/7/27.
 */

@Service
@Transactional
public class NoticeServiceImpl extends DaoSupport implements NoticeService {

    @Override
    public Notice findById(int noticeId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("noticeId", noticeId);
        return (Notice) this.findSpecialObject(Notice.class, params);
    }
}
