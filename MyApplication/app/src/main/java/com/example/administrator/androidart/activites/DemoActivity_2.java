package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class DemoActivity_2 extends XActivity {
    private TextView mTv;
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
        String s = getIntent().getStringExtra("key");
        mTv.setText("这个值是：" + s);
    }

    @Override
    public int getId() {
        return R.layout.activity_demo_2;
    }
}
