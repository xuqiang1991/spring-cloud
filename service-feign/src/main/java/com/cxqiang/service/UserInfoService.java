package com.cxqiang.service;


import com.cxqiang.entity.sys.Account;
import com.cxqiang.entity.sys.SysThirdPartyUser;
import com.cxqiang.entity.sys.SysUser;
import com.cxqiang.service.hystric.UserInfoServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xuqiang
 * 2017/8/28.
 */
@FeignClient(value = "service-hi", fallback = UserInfoServiceHystric.class)
public interface UserInfoService {

    /**
     * 平台登录
     * @param name
     * @return
     */
    @RequestMapping(value = "/user/findAccountByUsername",method = RequestMethod.GET)
    Account findAccountByUsername(@RequestParam(value = "name") String name);

    /**
     * 第三方登录
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/findAccountByUserId",method = RequestMethod.GET)
    Account findAccountByUserId(@RequestParam(value = "userId") Long userId);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/findByUserId",method = RequestMethod.GET)
    Account findByUserId(@RequestParam(value = "userId") Long userId);


    /**
     * 查询第三方用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/findByThirdPartyUserId",method = RequestMethod.GET)
    SysThirdPartyUser findByThirdPartyUserId(@RequestParam(value = "thirdPartyUserId") Long userId, @RequestParam(value = "type") Integer type);

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/user/insertUser",method = RequestMethod.POST)
    int insertUser(SysUser sysUser);


    /**
     * 添加第三方用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/insertUserAndSysThirdPartyUser",method = RequestMethod.POST)
    Long insertUserAndSysThirdPartyUser(SysThirdPartyUser user);
}
