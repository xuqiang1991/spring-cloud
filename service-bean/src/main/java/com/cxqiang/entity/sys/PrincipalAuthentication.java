package com.cxqiang.entity.sys;

/**
 * 登录信息
 * Created by xuqiang
 * 2017/11/14.
 */
public class PrincipalAuthentication {

    private Long userId;
    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
