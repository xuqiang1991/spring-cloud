package com.cxqiang.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by xuqiang
 * 2017/8/29.
 */
@EnableRedisHttpSession
@PropertySource("classpath:cache.properties")
public class HttpSessionConfig {
}
