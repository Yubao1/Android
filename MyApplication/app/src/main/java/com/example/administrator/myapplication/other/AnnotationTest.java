package com.example.administrator.myapplication.other;

import com.example.administrator.myapplication.annotation.Vocation;
import com.example.administrator.myapplication.annotation.Vocation2;
import com.example.administrator.myapplication.annotation.Vocation3;

/**
 * Created by 86188 on 2020/7/30.
 */

public class AnnotationTest {
    @Vocation(name = "小二",age = 21)
    public void test1(String s) {

    }
    @Vocation2(phone = "12345678")
    @Vocation(name = "小二",age = 21)
    public void test2(String s) {

    }
    @Vocation3()
    public void test3(String s) {

    }
    @Vocation(name = "小二",age = 21)
    public String s;
}
