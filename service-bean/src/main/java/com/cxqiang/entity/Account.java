package com.cxqiang.entity;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
public class Account {
    private String id;
    private String username;
    private String password;
    private String[] authoritys;

    public Account(){}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public String[] getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(String[] authoritys) {
        this.authoritys = authoritys;
    }
}