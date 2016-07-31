package org.sdgas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Entity
public class GovDoc {

    /**
     * 序号
     */
    private int docId;

    /**
     * 文件
     */
    private String file;

    /**
     * 主题
     */
    private String subject;

    @Id
    @GeneratedValue
    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
