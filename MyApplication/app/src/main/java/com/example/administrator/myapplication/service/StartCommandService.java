package com.example.administrator.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/8/27.
 */

public class StartCommandService extends Service {
    private int j = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyIBinder();
    }
    public class MyIBinder extends Binder {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        new MyLogcat().d(getClass(),"------------onCreate---------j = " + j);
        j++;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new MyLogcat().d(getClass(),"------------ intent = " + intent);
        new MyLogcat().d(getClass(),"------------onStartCommand---------j = " + j);
        j++;
//        String value = intent.getStringExtra("key");
//        for (int i = 1; i <10;i++) {
//            try {
//                new MyLogcat().d(getClass(),value + i);
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (i == 9) {
//                stopSelf(startId);
//            }
//        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new MyLogcat().d(getClass(),"-----------onDestroy()");
    }
}
