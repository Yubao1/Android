package com.example.administrator.jni;

/**
 * Created by Administrator on 2020/10/5.
 */

public class JNIC {
        static {
        System.loadLibrary("HelloWorld");
    }
    public native String callC();
}
