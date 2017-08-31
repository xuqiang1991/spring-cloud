package com.cxqiang.mapper;

import com.cxqiang.entity.sys.ResourceRole;
import com.cxqiang.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/31.
 */
public interface ResourceMapper {

    List<SysMenu> findMenuByApp(@Param("appId") String appId);

    List<ResourceRole> findAllResources(@Param("appId") String appId);
}
