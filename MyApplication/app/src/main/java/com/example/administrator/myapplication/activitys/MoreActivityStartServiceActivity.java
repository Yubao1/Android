package com.example.administrator.myapplication.activitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.service.StartCommandService;

/**
 * 在不同的进程不同的activity中startService时，都是同一个service对象
 */
public class MoreActivityStartServiceActivity extends XActivity {
    StartCommandService.MyIBinder myIBinder;
    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myIBinder = (StartCommandService.MyIBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void init() {
        Intent intent = new Intent(this,StartCommandService.class);
        startService(intent);
//        Intent intent = new Intent(this,StartCommandService.class);
//        bindService(intent,sc, Context.BIND_AUTO_CREATE);
    }

    @Override
    public int getId() {
        return R.layout.activity_more_start_service;
    }
    public void onClick(View view) {
        startActivity(MoreActivityStartService2Activity.class);
    }
}
