package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.tool.BindEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2020/3/10.
 */

public abstract class XActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(getLayoutRes());
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
        init();
    }
    protected void setTheme() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusCall event) {
        onEventBusCall(event);
    }
    public void onEventBusCall(EventBusCall event) {

    }
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
//        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public abstract void init();
    public abstract int getId();
    public int getLayoutRes() {
        return getId();
    }
}
