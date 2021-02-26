package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class CrashActivity extends XActivity {
    @Override
    public void init() {

    }
    public void onClick(View view) {
        throw new RuntimeException("自定义异常：这是自己抛出的异常");
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        throw new RuntimeException("自定义异常：这是自己抛出的异常");
//
//                    }
//                }
//        ).start();
    }
    @Override
    public int getId() {
        return R.layout.activity_crash;
    }
}
