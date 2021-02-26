package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 调用了onTouchEvent方法后，不管消不消费move和up序列事件
 * onClick方法不会再被调用
 */
public class ViewEventDistribution3Activity extends XActivity {

    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution3;
    }
    public void onClick(View view) {
        new MyLogcat().d(ViewEventDistribution3Activity.class,"进行点击事件");
    }
}
