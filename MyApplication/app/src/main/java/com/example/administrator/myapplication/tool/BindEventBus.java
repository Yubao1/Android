package com.example.administrator.myapplication.tool;

/**
 * Created by Administrator on 2020/3/10.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhoulei on 2017/11/22.
 * 绑定EventBus的注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {
}
