package com.cxqiang.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqiang
 * 2017/11/14.
 */
public class CxqiangUtils {

    /**
     * 用户权限转换
     * @param authoritys
     * @return
     */
    public static List<GrantedAuthority> conversionAuthoritys(List<String> authoritys){
        List<GrantedAuthority> authorities = null;
        if(CollectionUtils.isNotEmpty(authoritys)){
            authorities = new ArrayList();
            for(String role : authoritys) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }
}
