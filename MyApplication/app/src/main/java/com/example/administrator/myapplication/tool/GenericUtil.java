package com.example.administrator.myapplication.tool;

import com.example.administrator.myapplication.annotation.Vocation;
import com.example.administrator.myapplication.annotation.Vocation2;
import com.example.administrator.myapplication.annotation.Vocation3;
import com.example.administrator.myapplication.other.AnnotationTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 86188 on 2020/7/30.
 */

public class GenericUtil {

    /**
     * 获取单个注解对象的内容
     */
    public String performGeneric() {
        String s = null;
        AnnotationTest test = new AnnotationTest();
        Class<?> cls = test.getClass();
        try {
            Method method = cls.getMethod("test1",String.class);
            Vocation vocation = method.getAnnotation(Vocation.class);
            s = "name:" + vocation.name() + "-----age:" + vocation.age();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 获取 注解设置默认值
     */
    public String performVocationDefault() {
        String s = null;
        AnnotationTest test = new AnnotationTest();
        Class<?> cls = test.getClass();
        try {
            Method method = cls.getMethod("test3",String.class);
            Vocation3 vocation = method.getAnnotation(Vocation3.class);
            s = "name:" + vocation.name();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 获取多个注解对象
     */
    public String performAnnotations() {
        String s = "";
        AnnotationTest test = new AnnotationTest();
        Class<?> cls = test.getClass();
        try {
            Method method = cls.getMethod("test2",String.class);
            Annotation[] annotations = method.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                s = s + annotations[i].annotationType() + i + "\n";
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String performField() {
        String s = "";
        AnnotationTest test = new AnnotationTest();
        Class<?> cls = test.getClass();
        try {
            Field field = cls.getField("s");
            s = field.isAnnotationPresent(Vocation2.class) + "";
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return s;
    }
}
