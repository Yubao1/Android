package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.administrator.myapplication.tool.MyLogcat;

import java.io.BufferedReader;

/**
 * Created by 86188 on 2020/7/8.
 */

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyButton.class,"MotionEvent.ACTION_DOWN事件消耗");
                break;
            case MotionEvent.ACTION_MOVE:
                new MyLogcat().d(MyButton.class,"MotionEvent.ACTION_MOVE事件消耗");
                break;
            case MotionEvent.ACTION_UP:
                new MyLogcat().d(MyButton.class,"MotionEvent.ACTION_UP事件消耗");
                break;
        }
        return true;
    }
}
