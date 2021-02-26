package com.example.administrator.androidart.activites2;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.DemoActivity_1;
import com.example.administrator.androidart.activites.RemoteViewsActivity;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

public class DemoActivity_2 extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_demo_2_2;
    }
    public void onButtonClick(View v) {
//        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_simulated_notification);
//        remoteViews.setTextViewText(R.id.msg,"msg from process" + Process.myPid());
//        remoteViews.setImageViewResource(R.id.icon,R.drawable.ic_launcher_background);
//        Intent intent = new Intent(this, DemoActivity_1.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.item_holder,pendingIntent);
//        Intent intent2 = new Intent(this, com.example.administrator.androidart.activites.DemoActivity_2.class);
//        PendingIntent pendingIntent2 = PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.open_activity2,pendingIntent2);
//        Intent intent3 = new Intent(RemoteViewsActivity.ACTION_RETOME);
//        intent3.putExtra(RemoteViewsActivity.EXTRA_VIEW,remoteViews);
//        sendBroadcast(intent);


        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg, "msg from process:" + Process.myPid());
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.id.icon);
//        remoteViews.setImageViewBitmap(R.id.icon, bitmap);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, new Intent(this, DemoActivity_1.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, DemoActivity_2.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.item_holder, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        Intent intent = new Intent(RemoteViewsActivity.ACTION_RETOME);
        intent.putExtra(RemoteViewsActivity.EXTRA_VIEW, remoteViews);
        sendBroadcast(intent);
        new MyLogcat().d(getClass(),"sendBroadcast--------------------------------");
    }
}
