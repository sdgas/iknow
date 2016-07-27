package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wilson.he on 2016/7/27.
 */

@Entity
@Table(name = "tp")
public class Template {

    /**
     * 模板编号
     */
    private int templateId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 是否使用
     */
    private boolean used;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 模板主题
     */
    private String subject;

    @Id
    @GeneratedValue
    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    @Column(length = 10, nullable = false)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(nullable = false)
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false,length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
