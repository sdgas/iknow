package org.sdgas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Entity
public class Knowledge {

    /**
     * 序号
     */
    private int knowledgeId;

    /**
     * 类别
     */
    private String subject;

    /**
     * 知识内容
     */
    private String knowledge;

    @Id
    @GeneratedValue
    public int getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(int knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
}
