package com.cxqiang.mapper;

import com.cxqiang.entity.sys.Account;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    Account findByUsername(@Param("userName") String userName);

    Account findByUserId(@Param("userId") Long userId);
}