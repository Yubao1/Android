package com.example.administrator.myapplication.other;

/**
 * Created by 86188 on 2021/1/20.
 */

public class MyThread extends Thread {
    public MyThread(Runnable r) {
        super(r);
    }
    public MyThreadLocal.ThreadLocalMap threadLocals = null;
}
