package com.cxqiang.config;

import com.cxqiang.entity.Account;
import com.cxqiang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {

        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Account account = userInfoService.findByUsername(username);
                if(account != null) {
                    return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList(account.getAuthoritys()));
                } else {
                    throw new UsernameNotFoundException("could not find the user '" + username + "'");
                }
            }

        };
    }
}
