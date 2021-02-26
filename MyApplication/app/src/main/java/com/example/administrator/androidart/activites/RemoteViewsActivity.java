package com.example.administrator.androidart.activites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.administrator.androidart.activites2.DemoActivity_2;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

public class RemoteViewsActivity extends XActivity {
    private LinearLayout mLl;
    public static final String ACTION_RETOME = "com.example.administrator.androidart.activites.ACTION_RETOME";
    public static final String EXTRA_VIEW = "EXTRA_REMOTE_VIEWS";
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent.getParcelableExtra(EXTRA_VIEW);
            new MyLogcat().d(getClass(),"-------------onReceive");
            if (remoteViews != null) {
                updateUi(remoteViews);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void updateUi(RemoteViews remoteViews) {
        int layoutId = getResources().getIdentifier("layout_simulated_notification","layout",getPackageName());
        View view = getLayoutInflater().inflate(layoutId,mLl,false);
        remoteViews.reapply(this,view);
        remoteViews.setTextViewText(R.id.open_activity2,"更新这个文本");
        mLl.addView(view);
        new MyLogcat().d(this.getClass(),"---------------" + this.getClass() + "--------------updateUi");
    }

    @Override
    public void init() {
        mLl = findViewById(R.id.remote_views_content);
        IntentFilter filter = new IntentFilter(ACTION_RETOME);
        registerReceiver(receiver,filter);
    }

    @Override
    public int getId() {
        return R.layout.activity_remote_views;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_general_notification:
                startActivity(GeneralNotificationActivity.class);
                break;
            case R.id.btn_remote_views_notification:
                startActivity(RemoteViewsNotificationActivity.class);
                break;
            case R.id.btn_simulation_notification:
                startActivity(DemoActivity_2.class);
                break;
            case R.id.btn_app_widget_provider:
                startActivity(AppWidgetProviderActivity.class);
                break;
        }
    }
}
