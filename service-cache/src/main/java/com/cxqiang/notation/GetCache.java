package com.cxqiang.notation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuqiang
 * 2017/8/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface GetCache {

	/**
	 * 缓存中key的前缀
	 */
	String prefix();

	/**
	 * 过期时间，不设置则不过期
	 */
	long expire() default -1;

	/**
	 * 指定key的字段
	 */
	String[] key() default {};

	/**
	 * 缓存生效的条件
	 */
	String[] cons() default "";

	/**
	 * 是否删除
	 */
	boolean delete() default false;
}
