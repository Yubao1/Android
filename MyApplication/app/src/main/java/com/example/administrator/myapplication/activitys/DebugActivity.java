package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * debug 详解 https://blog.csdn.net/csdn_aiyang/article/details/79483848
 */
public class DebugActivity extends XActivity {
    @Override
    public void init() {
        method();
    }
    private void method() {
        for (int i = 0; i < 10;i++) {
            new MyLogcat().d(DebugActivity.class,"i = " + i);
            method2();
        }
    }
    private void method2() {
        int j = 9;
        int k = 8;
    }
    @Override
    public int getId() {
        return R.layout.activity_debug;
    }
}
