package com.example.administrator.androidart.other;

import android.app.Notification;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/8/10.
 */

public class MyNotification extends Notification {
    public MyNotification() {
        super();
        int pid = android.os.Process.myPid();
        new MyLogcat().d(getClass(),"---------------------MyNotification------" + pid);
    }
}
