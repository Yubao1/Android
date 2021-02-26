package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.receiver.DynamicRegistBroadcastReceiver;

/**
 * filter.addAction(ACTION_1)起到过滤的效果，只有过滤的action，广播接收者才可以接收到
 */
public class DynamicRegistActivity extends XActivity {
    DynamicRegistBroadcastReceiver dynamicReceiver;
    int num = 1;
    public static final String ACTION_1 = "DynamicRegistBroadcastReceiver_1";
    public static final String ACTION_2 = "DynamicRegistBroadcastReceiver_2";
    public static final String ACTION_3 = "DynamicRegistBroadcastReceiver_3";
    public static final String ACTION_4 = "DynamicRegistBroadcastReceiver_4";
    @Override
    public void init() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_1);
        filter.addAction(ACTION_4);
        dynamicReceiver = new DynamicRegistBroadcastReceiver();
        //注册广播接收
        registerReceiver(dynamicReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynamicReceiver);
    }

    @Override
    public int getId() {
        return R.layout.activity_dynamic_regist;
    }
    public void onClick(View view) {
        Intent intent = new Intent();
        if (num == 1) {
            intent.setAction(ACTION_1);
        } else if (num == 2) {
            intent.setAction(ACTION_2);
        } else if (num == 3) {
            intent.setAction(ACTION_3);
        } else if (num == 4) {
            intent.setAction(ACTION_4);
        }
        num++;
        int j = num % 4;
        num = j+1;
        sendBroadcast(intent);
    }
}
