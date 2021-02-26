package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.R;
import com.example.administrator.androidart.bean.User;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.activitys.FragmentActivity;

public class TestActivity extends XActivity {
    private Button btn;
    @Override
    public void init() {
//        User u = null;
//        Log.d("TestActivity",u.toString());
        btn = findViewById(R.id.btn);
        float x = btn.getScaleX();
        Log.e("TestActivity","------------------- x = " + x);
    }

    @Override
    public int getId() {
        return R.layout.activity_test;
    }

    public void onClick(View view) {
        startActivity(FragmentActivity.class);
    }
}
