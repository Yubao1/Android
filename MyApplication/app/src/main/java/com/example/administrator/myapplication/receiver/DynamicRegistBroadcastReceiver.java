package com.example.administrator.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/9/3.
 */

public class DynamicRegistBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        new MyLogcat().d(getClass(),"---------------action---" + action);
    }
}
