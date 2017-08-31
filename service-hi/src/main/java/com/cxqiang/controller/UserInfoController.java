package com.cxqiang.controller;

import com.cxqiang.entity.sys.Account;
import com.cxqiang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@EnableEurekaClient
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }

    @RequestMapping("/findByUsername")
    public Account findByUsername(@RequestParam String name) {
        return userInfoService.findByUsername(name);
    }
}
