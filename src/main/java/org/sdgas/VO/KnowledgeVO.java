package org.sdgas.VO;

/**
 * Created by wilson.he on 2016/7/31.
 */
public class KnowledgeVO extends BaseVO {

    private String subject;
    private String knowledge;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
