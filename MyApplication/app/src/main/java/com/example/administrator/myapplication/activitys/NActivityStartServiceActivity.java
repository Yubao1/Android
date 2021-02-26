package com.example.administrator.myapplication.activitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.androidart.service.SameService;
import com.example.administrator.myapplication.XActivity;

public class NActivityStartServiceActivity extends XActivity {
    SameService.MyBinder myBinder;
    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (SameService.MyBinder)service;
            if (myBinder != null) {
                myBinder.print();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void init() {
        Intent intent = new Intent(this, SameService.class);
        startService(intent);
        bindService(intent,sc, Context.BIND_AUTO_CREATE);

//        startService(intent);
    }
    public void onClick(View view) {
        startActivity(NActivityStartService2Activity.class);
    }
    @Override
    public int getId() {
        return R.layout.activity_nstart_service;
    }
}
