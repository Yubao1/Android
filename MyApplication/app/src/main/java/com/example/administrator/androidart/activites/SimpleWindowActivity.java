package com.example.administrator.androidart.activites;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * 如果没有弹出 View，就查看该APP是否打开相应权限
 */
public class SimpleWindowActivity extends XActivity implements View.OnTouchListener{
    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    @Override
    public void init() {
        initView();
    }

    private void initView() {
        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public int getId() {
        return R.layout.activity_simple_window;
    }
    public void onClick(View view) {
//        mFloatingButton = new Button(this);
//        mFloatingButton.setText("Click me");
//        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,0,
//                0, PixelFormat.TRANSPARENT);
//        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
//        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//        mLayoutParams.x = 100;
//        mLayoutParams.y = 300;
//        mFloatingButton.setOnTouchListener(this);
//        mWindowManager.addView(mFloatingButton,mLayoutParams);

        mFloatingButton = new Button(this);
        mFloatingButton.setText("click me");
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;

        /**
         * 8.0以上只能用这个权限
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        mFloatingButton.setOnTouchListener(this);
        mWindowManager.addView(mFloatingButton, mLayoutParams);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int)event.getRawX();
        int rawY = (int)event.getRawY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mLayoutParams.x = rawX;
            mLayoutParams.y = rawY;
            mWindowManager.updateViewLayout(mFloatingButton,mLayoutParams);
        }
        return false;
    }
}
