package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/9.
 */

public class MyButton5 extends AppCompatButton {
    public MyButton5(Context context) {
        super(context);
    }

    public MyButton5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        new MyLogcat().d(MyButton5.class,"onTouchEvent事件");
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new MyLogcat().d(MyButton5.class,"onClick事件");
//            }
//        });
//        return true;
//    }
}
