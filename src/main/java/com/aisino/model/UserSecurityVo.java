package com.aisino.model;

import java.io.Serializable;

/**
 * Created by gangchaopan on 2017/7/14.
 */
public class UserSecurityVO implements Serializable{

    /**
     * 用户ID
     */
    private long userId;
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

    public String getUsername() { return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) { this.password = password;}


    @Override
    public String toString() {
        return "UserSecurityVo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
