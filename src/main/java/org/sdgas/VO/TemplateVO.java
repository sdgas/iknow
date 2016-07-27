package org.sdgas.VO;

import org.sdgas.model.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilson.he on 2016/7/27.
 */
public class TemplateVO extends BaseVO {

    private String creator;
    private String used;
    private String content;
    private String subject;
    private String msg;
    private List<Template> templates = new ArrayList<Template>();

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
