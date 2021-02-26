package com.example.administrator.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 *
 * 1、START_STICKY_COMPATIBILITY
 D/ABC: MyService onCreate
 D/ABC: MyService onStartCommand
 D/ABC: startId=1
 D/ABC: intent=Intent { cmp=com.mucfc.myapplication/.MyService }
 D/ABC: MyService onCreate



 2、START_STICKY
 D/ABC: MyService onCreate
 D/ABC: MyService onStartCommand
 D/ABC: startId=1
 D/ABC: intent=Intent { cmp=com.mucfc.myapplication/.MyService }
 D/ABC: MyService onCreate
 D/ABC: MyService onStartCommand
 D/ABC: startId=2
 D/ABC: intent=null



 3、START_NOT_STICKY
 D/ABC: MyService onCreate
 D/ABC: MyService onStartCommand
 D/ABC: startId=1
 D/ABC: intent=Intent { cmp=com.mucfc.myapplication/.MyService }


 4、START_REDELIVER_INTENT
 D/ABC: MyService onCreate
 D/ABC: MyService onStartCommand
 D/ABC: startId=1
 D/ABC: intent=Intent { cmp=com.mucfc.myapplication/.MyService }
 D/ABC: MyService onCreate
 D/ABC: MyService onStartCommand
 D/ABC: startId=1
 D/ABC: intent=Intent { cmp=com.mucfc.myapplication/.MyService }

 */

public class StartCommand2Service extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        final String s = null;
        Log.d("StartCommand2Service", "onCreate");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Log.d("StartCommand2Service", "postDelayed");
//                // 制造异常，kill 掉该 Service
//                int a = 1 / 0;
                stopSelf();
            }
        }, 5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("StartCommand2Service", "onStartCommand");
//        Log.d("StartCommand2Service", "startId=" + startId);
//        Log.d("StartCommand2Service", "intent=" + intent);
        //这里的返回值可以修改为START_STICKY、START_NOT_STICKY、START_REDELIVER_INTENT
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("StartCommand2Service", "onStartCommand----结束");
        return START_REDELIVER_INTENT;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("StartCommand2Service", "MyService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("StartCommand2Service", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
