package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/8.
 */

public class MyButton2 extends AppCompatButton {
    public MyButton2(Context context) {
        super(context);
    }

    public MyButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyButton2.class,"-----------------------ACTION_DOWN");
//                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                new MyLogcat().d(MyButton2.class,"-----------------------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                new MyLogcat().d(MyButton2.class,"-----------------------ACTION_UP");
                break;
        }
        return true;
    }
}
