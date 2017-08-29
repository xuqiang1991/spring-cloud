package com.cxqiang.config;

import com.cxqiang.notation.ExpireCache;
import com.cxqiang.notation.GetCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqiang
 * 2017/8/29.
 */
@Component
public class CustomRolesManager {

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
        Map<String, List<String>> resources = new HashMap<>();
        List<String> roles = new ArrayList<>();
        roles.add("管理员");
        resources.put("/", roles);
        return resources;
    }
}
