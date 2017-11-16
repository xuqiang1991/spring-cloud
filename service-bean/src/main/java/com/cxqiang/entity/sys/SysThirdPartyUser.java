package com.cxqiang.entity.sys;

import java.util.Date;

public class SysThirdPartyUser {
    private Integer id;

    private Long userId;

    private Integer type;

    private String loginName;

    private String nick;

    private Long thirdPartyId;

    private Long authAppId;

    private String appAuthToken;

    private Date expiresIn;

    private Date reExpiresIn;

    private String appRefreshToken;

    private String authMethods;

    private Date authStart;

    private Date authEnd;

    private String status;

    private String url;

    private String detailUrl;

    private String phone;

    private String email;

    private Date createdTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public Long getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Long thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public Long getAuthAppId() {
        return authAppId;
    }

    public void setAuthAppId(Long authAppId) {
        this.authAppId = authAppId;
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken == null ? null : appAuthToken.trim();
    }

    public Date getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getReExpiresIn() {
        return reExpiresIn;
    }

    public void setReExpiresIn(Date reExpiresIn) {
        this.reExpiresIn = reExpiresIn;
    }

    public String getAppRefreshToken() {
        return appRefreshToken;
    }

    public void setAppRefreshToken(String appRefreshToken) {
        this.appRefreshToken = appRefreshToken == null ? null : appRefreshToken.trim();
    }

    public String getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(String authMethods) {
        this.authMethods = authMethods == null ? null : authMethods.trim();
    }

    public Date getAuthStart() {
        return authStart;
    }

    public void setAuthStart(Date authStart) {
        this.authStart = authStart;
    }

    public Date getAuthEnd() {
        return authEnd;
    }

    public void setAuthEnd(Date authEnd) {
        this.authEnd = authEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}