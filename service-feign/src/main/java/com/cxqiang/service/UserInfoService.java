package com.cxqiang.service;


import com.cxqiang.entity.Account;
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

    @RequestMapping(value = "/user/findByUsername",method = RequestMethod.GET)
    Account findByUsername(@RequestParam(value = "name") String name);
}
