package com.example.administrator.myapplication.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 86188 on 2020/7/30.
 *
 * 为注解设置默认值
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Vocation3 {
    String name() default "xiao_er";
}
