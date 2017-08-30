package com.cxqiang.config;

import com.cxqiang.entity.Account;
import com.cxqiang.service.UserInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
    }

    @Bean
    UserDetailsService userDetailsService() {

        return username -> {
            Account account = userInfoService.findByUsername(username);
            if(account != null) {
                List<GrantedAuthority> authorities = null;
                if(CollectionUtils.isNotEmpty(account.getAuthoritys())){
                    authorities = new ArrayList();
                    for(String role : account.getAuthoritys()) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                }
                return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                        authorities);
            } else {
                throw new UsernameNotFoundException("could not find the user '" + username + "'");
            }
        };
    }
}
