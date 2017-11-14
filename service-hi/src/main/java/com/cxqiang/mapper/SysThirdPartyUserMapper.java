package com.cxqiang.mapper;

import com.cxqiang.entity.sys.SysThirdPartyUser;
import org.apache.ibatis.annotations.Param;

public interface SysThirdPartyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysThirdPartyUser record);

    int insertSelective(SysThirdPartyUser record);

    SysThirdPartyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysThirdPartyUser record);

    int updateByPrimaryKey(SysThirdPartyUser record);

    SysThirdPartyUser selectByThirdPartyId(@Param("thirdPartyId") Long thirdPartyId, @Param("type") Integer type);
}