package com.example.administrator.myapplication.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by 86188 on 2020/11/16.
 */

public class CustomScrollView3 extends ScrollView {
    public CustomScrollView3(Context context) {
        super(context);
    }

    public CustomScrollView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 因为ACTION_DOWN始终无法进入ScrollView的onTouchEvent，
            // 但是ScrollView的滚动需要在ACTION_DOWN时做一些准备
            onTouchEvent(ev);
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            //can do something
        }
        return super.onTouchEvent(ev);
    }
}
