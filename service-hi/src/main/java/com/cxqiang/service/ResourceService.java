package com.cxqiang.service;

import com.cxqiang.entity.sys.ResourceRole;
import com.cxqiang.entity.sys.SysMenu;
import com.cxqiang.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 查询菜单
     * @param appId
     * @return
     */
    public List<SysMenu> findMenuByApp(String appId) {
        return resourceMapper.findMenuByApp(appId);
    }

    /**
     * 查询角色权限
     * @param appId
     * @return
     */
    public List<ResourceRole> findAllResources(String appId) {
        return resourceMapper.findAllResources(appId);
    }
}
