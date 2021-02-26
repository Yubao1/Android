package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/8.
 */

public class MyLinearLayout2 extends LinearLayout {
    public MyLinearLayout2(Context context) {
        super(context);
    }

    public MyLinearLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyLinearLayout2.class,"-------------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                new MyLogcat().d(MyLinearLayout2.class,"-------------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                new MyLogcat().d(MyLinearLayout2.class,"-------------ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return false;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return true;
    }
}
