package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2016/7/28.
 */
@Entity
@Table(name = "bbs")
public class Notice {

    /**
     * 公告编号
     */
    private int noticeId;

    /**
     * 公告类别
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 相关组别
     */
    private String noticeGroup;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    @Id
    @GeneratedValue
    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    @Column(nullable = false,length = 25)
    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    @Column(nullable = false,length = 5000)
    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Column(nullable = false,length = 100)
    public String getNoticeGroup() {
        return noticeGroup;
    }

    public void setNoticeGroup(String noticeGroup) {
        this.noticeGroup = noticeGroup;
    }

    @Column(nullable = false,length = 15)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
