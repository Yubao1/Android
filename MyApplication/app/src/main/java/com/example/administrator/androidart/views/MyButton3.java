package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/9.
 */

public class MyButton3 extends AppCompatButton {
    public MyButton3(Context context) {
        super(context);
    }

    public MyButton3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(MyButton3.class,"只消耗down序列事件");
                break;
        }
        return false;
    }
}
