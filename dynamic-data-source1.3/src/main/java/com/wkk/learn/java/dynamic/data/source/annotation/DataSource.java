package com.wkk.learn.java.dynamic.data.source.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default "";
}
