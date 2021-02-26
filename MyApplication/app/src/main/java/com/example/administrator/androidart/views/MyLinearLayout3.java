package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/10.
 */

public class MyLinearLayout3 extends LinearLayout {
    public MyLinearLayout3(Context context) {
        super(context);
    }

    public MyLinearLayout3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyLinearLayout3.class,"---------------ACTION_DOWN");
                getChildAt(0).onTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                new MyLogcat().d(MyLinearLayout3.class,"---------------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                new MyLogcat().d(MyLinearLayout3.class,"---------------ACTION_UP");
                break;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyButton7.class,"---------------正在拦截");
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
