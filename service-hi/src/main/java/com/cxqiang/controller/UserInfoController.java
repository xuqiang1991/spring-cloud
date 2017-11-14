package com.cxqiang.controller;

import com.cxqiang.entity.sys.Account;
import com.cxqiang.entity.sys.SysThirdPartyUser;
import com.cxqiang.entity.sys.SysUser;
import com.cxqiang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/findAccountByUsername")
    public Account findByUsername(@RequestParam String name) {
        return userInfoService.findAccountByUsername(name);
    }

    @RequestMapping("/findAccountByUserId")
    public Account findAccountByUserId(@RequestParam Long userId) {
        return userInfoService.findAccountByUserId(userId);
    }

    @RequestMapping("/findByThirdPartyUserId")
    public SysThirdPartyUser findByThirdPartyUserId(@RequestParam Long thirdPartyUserId, @RequestParam Integer type) {
        return userInfoService.findByThirdPartyUserId(thirdPartyUserId, type);
    }

    @RequestMapping("/findByUserId")
    public SysUser findByUserId(@RequestParam Long userId) {
        return userInfoService.findUserId(userId);
    }

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/insertUser")
    int insertUser(@RequestBody SysUser sysUser){
        return userInfoService.insertUser(sysUser);
    }


    /**
     * 添加第三方用户
     * @param sysThirdPartyUser
     * @return
     */
    @RequestMapping(value = "/insertUserAndSysThirdPartyUser")
    Long insertUserAndSysThirdPartyUser(@RequestBody SysThirdPartyUser sysThirdPartyUser){
        return userInfoService.insertUserAndSysThirdPartyUser(sysThirdPartyUser);
    }


}
