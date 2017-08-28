package com.cxqiang.mapper;

import com.cxqiang.entity.Account;

public interface UserInfoMapper {

    Account findByUsername(String userName);

}