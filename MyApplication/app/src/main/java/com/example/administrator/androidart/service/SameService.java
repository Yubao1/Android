package com.example.administrator.androidart.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myapplication.other.Test;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/6/2.
 */

public class SameService extends Service {
    private int num = 1;
    private static final String TAG = "SameService";
    public MyBinder myBinder = null;
    private boolean f = true;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate-----------------num = " + num);
        num++;
        myBinder = new MyBinder();
    }
    public class MyBinder extends Binder implements IInterface{
        @Override
        public void print() {
            new Thread() {
                @Override
                public void run() {
                    while (f)

                    {
                        Log.d(TAG, "print-----------------num = " + num);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
    public interface IInterface {
        public void print();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG,"onStartCommand--------------num = " + num);
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (f) {
                    try {
                        num++;
                        Thread.sleep(1000);
                        new MyLogcat().d(SameService.this.getClass(),"-------------num = " + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        f=false;
        new MyLogcat().d(getClass(),"------------onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
