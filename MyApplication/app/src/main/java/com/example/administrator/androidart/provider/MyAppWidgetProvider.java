package com.example.administrator.androidart.provider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.AppWidgetProviderActivity;
import com.example.administrator.androidart.service.ListViewService;

/**
 * Created by 86188 on 2020/8/6.
 */

public class MyAppWidgetProvider extends AppWidgetProvider {
    public static final String CHANGE_IMAGE = "AppWidgetProvider_ClickAction";

    private RemoteViews mRemoteViews;
    private ComponentName mComponentName;

    private int[] imgs = new int[]{
            R.mipmap.a1,
            R.mipmap.b2,
            R.mipmap.c3,
            R.mipmap.d4,
            R.mipmap.e5,
            R.mipmap.f6
    };
    public MyAppWidgetProvider() {
        super();
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        mRemoteViews.setImageViewResource(R.id.iv_test, R.mipmap.ic_launcher);
        mRemoteViews.setTextViewText(R.id.btn_test, "点击跳转到Activity");
        Intent skipIntent = new Intent(context, AppWidgetProviderActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 200, skipIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_test, pi);

        // 设置 ListView 的adapter。
        // (01) intent: 对应启动 ListViewService(RemoteViewsService) 的intent
        // (02) setRemoteAdapter: 设置 ListView 的适配器
        // 通过setRemoteAdapter将 ListView 和ListViewService关联起来，
        // 以达到通过 GridWidgetService 更新 gridview 的目的
        Intent lvIntent = new Intent(context, ListViewService.class);
        mRemoteViews.setRemoteAdapter(R.id.lv_test, lvIntent);
        mRemoteViews.setEmptyView(R.id.lv_test,android.R.id.empty);

        // 设置响应 ListView 的intent模板
        // 说明：“集合控件(如GridView、ListView、StackView等)”中包含很多子元素，如GridView包含很多格子。
        // 它们不能像普通的按钮一样通过 setOnClickPendingIntent 设置点击事件，必须先通过两步。
        // (01) 通过 setPendingIntentTemplate 设置 “intent模板”，这是比不可少的！
        // (02) 然后在处理该“集合控件”的RemoteViewsFactory类的getViewAt()接口中 通过 setOnClickFillInIntent 设置“集合控件的某一项的数据”

        /*
         * setPendingIntentTemplate 设置pendingIntent 模板
         * setOnClickFillInIntent   可以将fillInIntent 添加到pendingIntent中
         */
        Intent toIntent = new Intent(CHANGE_IMAGE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 200, toIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setPendingIntentTemplate(R.id.lv_test, pendingIntent);


        mComponentName = new ComponentName(context, MyAppWidgetProvider.class);
        appWidgetManager.updateAppWidget(mComponentName, mRemoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(TextUtils.equals(CHANGE_IMAGE,intent.getAction())){
            Bundle extras = intent.getExtras();
            int position = extras.getInt(ListViewService.INITENT_DATA);
            mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            mRemoteViews.setImageViewResource(R.id.iv_test, imgs[position]);
            mComponentName = new ComponentName(context, MyAppWidgetProvider.class);
            AppWidgetManager.getInstance(context).updateAppWidget(mComponentName, mRemoteViews);
        }
    }
//}















//    @Override
//    public void onReceive(final Context context, final Intent intent) {
//        super.onReceive(context, intent);
//        // 这里判断是自己的action，做自己的事情，比如小工具被点击了要干啥，这里是做一个动画效果
//        if (intent.getAction().equals(CLICK_ACTION)) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon1);
//                    AppWidgetManager manager = AppWidgetManager.getInstance(context);
//                    for (int i = 0; i < 37; i++) {
//                        float degree  = ( i * 10) % 360;
//                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);
//                        remoteViews.setImageViewBitmap(R.id.imageView1,rotateBitmap(context, bitmap, degree));
//                        Intent intentClick = new Intent();
//                        intentClick.setAction(CLICK_ACTION);
//                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
//                        remoteViews.setOnClickPendingIntent(R.id.imageView1,pendingIntent);
//                        ComponentName cn = new ComponentName(context,getClass());
//                        manager.updateAppWidget(cn,remoteViews);
//                        SystemClock.sleep(30);
//                    }
//                }
//            }).start();
//        }
//    }
//
//    private Bitmap rotateBitmap(Context c,Bitmap b,float d) {
//        Matrix matrix = new Matrix();
//        matrix.reset();
//        matrix.setRotate(d);
//        Bitmap bitmap = Bitmap.createBitmap(b,0,0,b.getWidth(),b.getHeight(),matrix,true);
//        return bitmap;
//    }
//
//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        super.onUpdate(context, appWidgetManager, appWidgetIds);
//        final int count = appWidgetIds.length;
//        for (int i = 0; i < count; i++) {
//            int a = appWidgetIds[i];
//            onWidgetUpdate(context,appWidgetManager,a);
//        }
//    }
//
//    private void onWidgetUpdate(Context c,AppWidgetManager manager,int appWidgetId) {
//        RemoteViews remoteViews = new RemoteViews(c.getPackageName(),R.layout.widget);
//        Intent intentClick = new Intent();
//        intentClick.setAction(CLICK_ACTION);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(c,0,intentClick,0);
//        remoteViews.setOnClickPendingIntent(R.id.imageView1,pendingIntent);
//        manager.updateAppWidget(appWidgetId,remoteViews);
//    }
}
