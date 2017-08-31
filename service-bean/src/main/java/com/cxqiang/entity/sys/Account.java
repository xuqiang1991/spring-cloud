package com.cxqiang.entity.sys;

import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
public class Account {
    private Long id;
    private String username;
    private String password;
    private List<String> authoritys;

    public Account(){}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<String> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(List<String> authoritys) {
        this.authoritys = authoritys;
    }
}
