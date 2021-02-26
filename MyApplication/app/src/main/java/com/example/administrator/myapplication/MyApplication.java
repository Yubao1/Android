package com.example.administrator.myapplication;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.administrator.androidart.other.CrashHandler;

/**
 * Created by 86188 on 2020/6/1.
 */

public class MyApplication extends Application {
    private static MyApplication sInstance;

    @NonNull
    public static String diskPath;
    @Override
    public void onCreate() {
        super.onCreate();
        if (sInstance == null) {
            sInstance = this;
        }
        diskPath = getExternalFilesDir(null).getAbsolutePath();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    public static MyApplication get() {
        return sInstance;
    }
}
