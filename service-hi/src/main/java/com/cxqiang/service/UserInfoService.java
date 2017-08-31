package com.cxqiang.service;

import com.cxqiang.entity.sys.Account;
import com.cxqiang.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户
     * @param name
     * @return
     */
    public Account findByUsername(String name) {
        return userInfoMapper.findByUsername(name);
    }
}
