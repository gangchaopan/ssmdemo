package com.aisino.model;

import com.constants.UserConstant;

import java.util.Date;

/**
 * Created by gangchaopan on 2017/7/13.
 */
public class UserPO {
    /**
     * 用户ID
     */
    private long userId;

    /**
     * 公共用户ID
     */
    private long openId;

    /**
     * 帐号类型
     */
    private UserConstant.Type type;

    /**
     * 用户名
     */
    private String name;

    /**
     * 时间
     */
    private Date createTime;
    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOpenId() {
        return openId;
    }

    public void setOpenId(long openId) {
        this.openId = openId;
    }

    public UserConstant.Type getType() {
        return type;
    }

    public void setType(UserConstant.Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
