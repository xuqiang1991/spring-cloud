package com.cxqiang.service;


import com.cxqiang.entity.sys.ResourceRole;
import com.cxqiang.entity.sys.SysMenu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@FeignClient(value = "service-hi")
public interface SysService {

    @RequestMapping(value = "/sys/findMenuByApp",method = RequestMethod.GET)
    List<SysMenu> findMenuByApp(@RequestParam(value = "appId") String name);

    @RequestMapping(value = "/sys/findAllResources",method = RequestMethod.GET)
    List<ResourceRole> findAllResources(@RequestParam(value = "appId") String name);
}
