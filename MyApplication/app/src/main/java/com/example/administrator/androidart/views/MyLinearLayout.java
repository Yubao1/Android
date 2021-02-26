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

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyLinearLayout.class,"ACTION_DOWN事件消耗--------------");
                break;
            case MotionEvent.ACTION_MOVE:
                this.getChildAt(0).onTouchEvent(event);
                new MyLogcat().d(MyLinearLayout.class,"MotionEvent.ACTION_MOVE事件消耗--------------");
                break;
            case MotionEvent.ACTION_UP:
                this.getChildAt(0).onTouchEvent(event);
                new MyLogcat().d(MyLinearLayout.class,"MotionEvent.ACTION_UP事件消耗--------------");
                break;
        }
        return true;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
