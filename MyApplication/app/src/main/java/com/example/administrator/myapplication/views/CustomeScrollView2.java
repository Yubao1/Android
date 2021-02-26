package com.example.administrator.myapplication.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by 86188 on 2020/11/12.
 */

public class CustomeScrollView2 extends ScrollView {
    public CustomeScrollView2(Context context) {
        super(context);
    }

    public CustomeScrollView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeScrollView2(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
}
