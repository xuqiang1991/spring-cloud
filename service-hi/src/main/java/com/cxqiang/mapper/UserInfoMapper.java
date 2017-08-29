package com.cxqiang.mapper;

import com.cxqiang.entity.Account;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    Account findByUsername(@Param("userName") String userName);

}