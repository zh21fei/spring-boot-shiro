package com.neo.aop.annotation;

import java.lang.annotation.*;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 注释
public @interface OperLog {
    String value() default "";
}
