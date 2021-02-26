package com.example.administrator.androidart.other;

import android.os.Parcel;
import android.widget.RemoteViews;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/8/10.
 */

public class MyRemoteViews extends RemoteViews {
    public MyRemoteViews(String packageName, int layoutId) {
        super(packageName, layoutId);
        print();
    }

    public MyRemoteViews(RemoteViews landscape, RemoteViews portrait) {
        super(landscape, portrait);
        print();
    }

    public MyRemoteViews(Parcel parcel) {
        super(parcel);
        print();
    }
    private void print() {
        int pid = android.os.Process.myPid();
        new MyLogcat().d(getClass(),"---------------------MyRemoteViews------" + pid);
    }
}
