package com.hongguaninfo.hgdf.wadp.core.aop.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {
    /**
     * 用户操作名称
     * 
     * @return 用户操作名称，默认为空串
     */
    String name() default "";

    /**
     * 用户操作名称
     * 
     * @return 用户操作名称，默认为空串
     */
    String code() default "";

    /**
     * 用户操作类型，默认类型
     * 
     * @return 用户操作类型
     */
    int type() default 0;

    /**
     * 需要存储remark_json的类
     * 
     * @return key
     */
    Class remarkClass() default UserLog.class;
}