package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.androidart.views.MyButton5;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 1、如果没有设置onTouch方法，在onTouchEvent中设置了setOnClickListener事件，onTouchEvent方法返回true时，
 * setOnClickListener不会起作用；onTouchEvent方法返回false时,setOnClickListener才会起作用。
 *
 *2、在onTouch中设置了setOnClickListener事件，onTouch方法返回true时，
 * setOnClickListener不会起作用；onTouch方法返回false时,setOnClickListener才会起作用。
 */
public class ViewEventDistribution5Activity extends XActivity {
    MyButton5 btn;
    @Override
    public void init() {
        btn = findViewById(R.id.btn);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MyLogcat().d(ViewEventDistribution5Activity.class,"---------------------onClick");
                    }
                });
                new MyLogcat().d(ViewEventDistribution5Activity.class,"---------------------onTouch");
                return true;
            }
        });
    }

    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution5;
    }
}
