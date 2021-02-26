package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/9.
 */

public class MyButton6 extends AppCompatButton {
    public MyButton6(Context context) {
        super(context);
        setClickable2();
    }

    public MyButton6(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable2();
    }

    public MyButton6(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable2();
    }
    private void setClickable2() {
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        new MyLogcat().d(MyButton6.class,"onTouchEvent方法的返回值：" + b);
        return b;
    }
}
