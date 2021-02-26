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
import com.example.administrator.androidart.service.SameService;
import com.example.administrator.myapplication.XActivity;

public class NActivityStartService2Activity extends XActivity {
    SameService.IInterface iInterface;
    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iInterface = (SameService.IInterface) service;
            iInterface.print();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void init() {
         bindService(null);
    }
    public void bindService(View view) {
        Intent intent = new Intent(this, SameService.class);
        bindService(intent,sc, Context.BIND_AUTO_CREATE);
    }
    @Override
    public int getId() {
        return R.layout.activity_nstart_service2;
    }
}
