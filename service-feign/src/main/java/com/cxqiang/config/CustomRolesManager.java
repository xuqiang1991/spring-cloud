package com.cxqiang.config;

import com.cxqiang.entity.sys.ResourceRole;
import com.cxqiang.notation.ExpireCache;
import com.cxqiang.notation.GetCache;
import com.cxqiang.service.SysService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by xuqiang
 * 2017/8/29.
 */
@Component
public class CustomRolesManager {

    @Value("${sys.appId}")
    private String appId;

    @Autowired
    SysService sysService;

    @ExpireCache(prefix = "sysAdmin_", key = {"resources"})
    public void reload() {
    }

    /**
     * 获取资源与角色
     * 86400秒（缓存一天）
     * null 缓存的时候需要个参数
     * @return
     */
    @GetCache(prefix = "sysAdmin_", expire = 86400, key = {"resources"})
    public Map<String, List<String>> getAllResources() {
        Map<String,List<String>> resources = new LinkedHashMap<>();
        List<ResourceRole> roleDtos = sysService.findAllResources(appId);
        if (CollectionUtils.isNotEmpty(roleDtos)) {
            for (ResourceRole roleDto : roleDtos) {
                String url = roleDto.getUrl();
                List<String> roles = roleDto.getRoles();
                resources.put(url, roles);
            }
        }
        return resources;
    }
}
