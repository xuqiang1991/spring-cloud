package com.cxqiang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by xuqiang
 * 2017/8/29.
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Value("${sys.accounts}") private String[] accounts;

    @Value("${sys.whites}") private String[] whiteResources;

    @Autowired
    CustomRolesManager customRolesManager;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = new StringBuilder(filterInvocation.getRequestUrl()).toString();

        //静态资源
        if(whiteResources != null){
            for (String whiteResource:whiteResources){
                if(url.indexOf(whiteResource) == 0){
                    return;
                }
            }
        }

        Collection<GrantedAuthority> userHasRoles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority userHasRole : userHasRoles){
            //临时用户
            if("ROLE_ANONYMOUS".equals(userHasRole.getAuthority())){
                throw new AccessDeniedException("请重新登录！ ");
            }
        }

        //管理员
        if(authentication.getDetails() != null){
            User user = (User)authentication.getPrincipal();
            if(accounts != null){
                for (String account:accounts){
                    if(account.equals(user.getUsername())){
                        return;
                    }
                }
            }
        }

        //权限鉴定
        Map<String, List<String>> resources = customRolesManager.getAllResources();
        Iterator<Map.Entry<String, List<String>>> entries = resources.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            String key = entry.getKey();
            if(url.startsWith(key)){
                List<String> usrRoles = entry.getValue();
                for (GrantedAuthority userHasRole : userHasRoles){
                    if(usrRoles.contains(userHasRole.getAuthority())){
                        return;
                    }
                }
                throw new AccessDeniedException("没有权限访问！ ");
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
