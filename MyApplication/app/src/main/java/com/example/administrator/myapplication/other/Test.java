package com.example.administrator.myapplication.other;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by Administrator on 2020/3/28.
 */

public class Test {
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    public String echo(String mesg) {
        return "echo" + mesg;
    }

    public interface IInterface{
        void add2();
    }
    abstract class Test2_1{
        abstract void add3();
    }
    public static void printStaticMethodData() {
        new MyLogcat().d(Test.class,"-----------printStaticMethodData");
    }
}
