package com.example.administrator.androidart.activites;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;
import com.example.administrator.myapplication.tool.NotificationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GeneralNotificationActivity extends XActivity {
    MyHandler mHndler = null;
    @Override
    public void init() {
//        Intent intent = new
//                Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        try {
//            Class cls = Class.forName("android.app.Notification");
//            Constructor constructor = cls.getConstructor();
//            Method setLatestEventInfo = cls.getDeclaredMethod("setLatestEventInfo",Context.class,
//                    CharSequence.class,CharSequence.class,PendingIntent.class);
//            Object object = constructor.newInstance();
//            Notification notification = (Notification) object;
//            notification.icon = R.drawable.ic_launcher_foreground;
//            notification.tickerText = "这是一个一般通知栏演示效果哦";
//            notification.when = System.currentTimeMillis();
//            notification.flags = Notification.FLAG_AUTO_CANCEL;
//            Intent intent = new Intent(this,NotificationActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT);
//            setLatestEventInfo.invoke(notification,this,"通知栏","通知栏内容",pendingIntent);
////            notification.setLatestEventInfo(this,"title","content",pendingIntent);
//            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//            manager.notify(1,notification);
//        } catch (ClassNotFoundException e) {
//            new MyLogcat().d(getClass(),"---------ClassNotFoundException");
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            new MyLogcat().d(getClass(),"---------NoSuchMethodException");
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            new MyLogcat().d(getClass(),"---------NoSuchMethodException");
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            new MyLogcat().d(getClass(),"---------IllegalAccessException");
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            new MyLogcat().d(getClass(),"---------InvocationTargetException");
//            e.printStackTrace();
//        }
          mHndler = new MyHandler();
          MyThread t = new MyThread();
          t.start();
    }
    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(3000);
                    mHndler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sendNotification();
        }
    }
    private void sendNotification() {
//        Intent intent = new Intent(GeneralNotificationActivity.this,NotificationActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(GeneralNotificationActivity.this,0,intent,0);
//        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        Notification notification = new NotificationCompat.Builder(this)//此处会有中间一道线，并不影响运行，这是android系统版本的问题
//                .setContentTitle("标题")  //显示通知的标题
//                .setContentText("这里展示的是通知内容~")//显示消息通知的内容
//                .setWhen(System.currentTimeMillis())//显示通知的具体时间
//                .setSmallIcon(R.mipmap.ic_launcher)//这里设置显示的是手机顶部系统通知的应用图标
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//这里设置显示的是下拉通知栏后显示的系统图标
//                .setContentIntent(pendingIntent)
//                //.setAutoCancel(true)//可以在此使用此方法，点击通知后，通知内容自动取消,也可以在NotificationActivity.java中设置方法取消显示通知内容
//                .setVibrate(new long[] {0,1000,1000,1000})//设置发出通知后震动一秒，停止一秒后再震动一秒，需要在manifest.xml中设置权限
//                .build();
//        manager.notify(1,notification);
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.sendNotification(1,"这个是标题","这个是内容",R.mipmap.ic_launcher);
    }

    @Override
    public int getId() {
        return R.layout.activity_general_notification;
    }
}
