package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.androidart.views.MyViewGroup2;
import com.example.administrator.myapplication.XActivity;

public class Demo3Activity extends XActivity {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    private MyViewGroup2 mMyViewGroup;
    @Override
    public void init() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        SCREEN_WIDTH = displayMetrics.widthPixels;
        SCREEN_HEIGHT = displayMetrics.heightPixels;


        mMyViewGroup = (MyViewGroup2) findViewById(R.id.viewgroup);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mMyViewGroup.scrollBy(100, 0);
                break;
            case R.id.btn2:
                mMyViewGroup.scrollTo(0, 0);
                break;
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_demo3;
    }
}
