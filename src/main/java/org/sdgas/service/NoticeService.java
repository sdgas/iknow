package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Notice;

/**
 * Created by wilson.he on 2016/7/27.
 */
public interface NoticeService extends DAO {

    /**
     * 根据模板编号查找
     * @param noticeId 模板编号
     * @return 模板
     */
    public Notice findById(int noticeId);
}
