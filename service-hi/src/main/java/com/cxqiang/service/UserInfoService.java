package com.cxqiang.service;

import com.cxqiang.entity.Account;
import com.cxqiang.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Account findByUsername(@RequestParam String name) {
        return userInfoMapper.findByUsername(name);
    }
}
