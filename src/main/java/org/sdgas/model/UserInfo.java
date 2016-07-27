package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wilson.he on 2016/7/27.
 */
@Entity
public class UserInfo {

    /**
     * 工号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 职位
     * 0管理员   1员工   2维护人员
     */
    private int position;

    /**
     * 备注
     */
    private String remark;

    @Id
    @Column(length = 10)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(length = 10,nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(length = 20,nullable = false)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(nullable = false)
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
