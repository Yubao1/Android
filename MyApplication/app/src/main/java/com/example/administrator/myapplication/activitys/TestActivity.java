package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.interfaces.InterfaceA;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.EventBusCall;

import org.greenrobot.eventbus.EventBus;

/**
 *  | 表示符号相叠加 TextView的gravity属性
 */
public class TestActivity extends XActivity implements InterfaceA{
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_test2;
    }
    public void onClick(View view) {
        EventBus.getDefault().post(new EventBusCall(Constant.MAIN_ACTIVITY_RECEIVE_ID));
    }

    @Override
    public void add() {

    }
}
