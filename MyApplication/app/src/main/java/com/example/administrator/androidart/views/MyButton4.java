package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.administrator.androidart.activites.ViewEventDistribution4Activity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/9.
 */

public class MyButton4 extends AppCompatButton {
    public MyButton4(Context context) {
        super(context);
    }

    public MyButton4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        new MyLogcat().d(MyButton4.class,"-------------------onTouchEvent事件");
        return super.onTouchEvent(event);
    }
}
