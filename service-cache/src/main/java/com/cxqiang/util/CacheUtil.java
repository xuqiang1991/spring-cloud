package com.cxqiang.util;

import com.cxqiang.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * Depiction:静态方法调用redis工具类
 */
@Component
public class CacheUtil {
    @Autowired
    private Cache<RedisTemplate> cache;

    public static Cache<RedisTemplate> redis;

    @PostConstruct
    public void init(){
        CacheUtil.redis=cache;
    }
}
