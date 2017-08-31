package com.cxqiang.entity.sys;

import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/31.
 */
public class ResourceRole {
    private Integer id;

    private String url;//资源

    private List<String> roles;//所拥有的角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
