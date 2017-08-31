package com.cxqiang.controller;

import com.cxqiang.entity.sys.ResourceRole;
import com.cxqiang.entity.sys.SysMenu;
import com.cxqiang.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@EnableEurekaClient
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findMenuByApp")
    public List<SysMenu> findMenuByApp(@RequestParam String appId) {
        return resourceService.findMenuByApp(appId);
    }

    @RequestMapping("/findAllResources")
    public List<ResourceRole> findAllResources(@RequestParam String appId) {
        return resourceService.findAllResources(appId);
    }

}
