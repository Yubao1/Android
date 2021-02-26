package com.example.administrator.androidart.activites;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.example.administrator.R;
import com.example.administrator.androidart.other.MyNotification;
import com.example.administrator.androidart.other.MyRemoteViews;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 仅限于 Api = 26以下的使用
 */
public class RemoteViewsNotificationActivity extends XActivity {
    Intent intent2 = null;
    Intent intent4;
    PendingIntent pendingIntent2;
    PendingIntent pendingIntent3;
    int y = 1;
    @Override
    public void init() {
        intent2 = new Intent(this,DemoActivity_2.class);
        intent4 = new Intent(this,DemoActivity_2.class);
       pendingIntent2 = PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent3 = PendingIntent.getActivity(this,1,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        int pid = android.os.Process.myPid();
        new MyLogcat().d(getClass(),"---------------------RemoteViewsNotificationActivity------" + pid);
        sendNotification("remoteViews内容");
    }
    private void sendNotification(String s) {
        Notification notification = new MyNotification();
        notification.icon = R.drawable.ic_launcher_background;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        Intent intent = new Intent(this,DemoActivity_1.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new MyRemoteViews(getPackageName(),R.layout.remote_view_layout);
        remoteViews.setTextViewText(R.id.tv,s);
        remoteViews.setImageViewResource(R.id.iv,R.drawable.img);
        intent2.putExtra("key","空值");
        remoteViews.setOnClickPendingIntent(R.id.iv,pendingIntent2);
        intent2.putExtra("key","what is your");
        remoteViews.setOnClickPendingIntent(R.id.tv,pendingIntent3);
        notification.contentView = remoteViews;
//        notification.contentIntent = pendingIntent;
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2,notification);
    }
    public void onClick(View view) {
//        sendNotification(pendingIntent3,"呵呵" + y);
//        y++;
    }
    @Override
    public int getId() {
        return R.layout.activity_remote_views_notification;
    }
}
