package org.sdgas.VO;

/**
 * Created by wilson.he on 2016/7/31.
 */
public class NoticeVO extends BaseVO  {

    private String noticeType;
    private String noticeContent;
    private String noticeGroup;
    private String creator;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeGroup() {
        return noticeGroup;
    }

    public void setNoticeGroup(String noticeGroup) {
        this.noticeGroup = noticeGroup;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
