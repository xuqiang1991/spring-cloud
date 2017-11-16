package com.cxqiang.config;

import com.cxqiang.entity.sys.Account;
import com.cxqiang.service.UserInfoService;
import com.cxqiang.utils.CxqiangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自已的构建用户登录（第三方登录）
 * Created by xuqiang
 * 2017/11/14.
 */
@Component
public class SampleAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserInfoService userInfoService;

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        Account account = userInfoService.findAccountByUserId(Long.parseLong(auth.getName()));
        if (account != null) {
            List<GrantedAuthority> authorities = CxqiangUtils.conversionAuthoritys(account.getAuthoritys());
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), authorities);
        }
        return null;
    }
}
