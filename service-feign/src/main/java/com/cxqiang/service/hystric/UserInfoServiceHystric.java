package com.cxqiang.service.hystric;

import com.cxqiang.entity.Account;
import com.cxqiang.service.UserInfoService;
import org.springframework.stereotype.Component;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Component
public class UserInfoServiceHystric implements UserInfoService {
    @Override
    public Account findByUsername(String username) {
        return null;
    }
}
