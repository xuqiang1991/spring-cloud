package com.cxqiang.service.hystric;

import com.cxqiang.entity.sys.Account;
import com.cxqiang.entity.sys.SysThirdPartyUser;
import com.cxqiang.entity.sys.SysUser;
import com.cxqiang.service.UserInfoService;
import org.springframework.stereotype.Component;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Component
public class UserInfoServiceHystric implements UserInfoService {

    @Override
    public Account findAccountByUsername(String name) {
        return null;
    }

    @Override
    public Account findAccountByUserId(Long userId) {
        return null;
    }

    @Override
    public Account findByUserId(Long userId) {
        return null;
    }

    @Override
    public SysThirdPartyUser findByThirdPartyUserId(Long userId, Integer type) {
        return new SysThirdPartyUser();
    }

    @Override
    public int insertUser(SysUser sysUser) {
        return 0;
    }

    @Override
    public Long insertUserAndSysThirdPartyUser(SysThirdPartyUser sysThirdPartyUser) {
        return null;
    }
}
