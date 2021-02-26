package com.example.administrator.androidart.activites;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.androidart.views.MyViewGroup;
import com.example.administrator.myapplication.XActivity;

public class ViewSpeedDetectActivity extends XActivity {
    private TextView mTv;
    VelocityTracker mVt = null;
    private int mX = 0;
    private int mY = 0;
    private LinearLayout mLl;
    private MyHandler mHandler = null;
    private TextView mTvScrollBy;
    private MyViewGroup myViewGroup;
    @Override
    public void init() {
        mHandler = new MyHandler();
        mTv = findViewById(R.id.tv);
        mTvScrollBy = findViewById(R.id.tv_scroll_by);
        mLl = findViewById(R.id.ll);
//        new MyThread().start();
        mTvScrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLl.scrollTo(100,100);
                mLl.invalidate();
                Toast.makeText(ViewSpeedDetectActivity.this,"scrollTo",Toast.LENGTH_SHORT).show();
            }
        });
//        final MyViewGroup myView = findViewById(R.id.view);
////        final View tv = findViewById(R.id.tv);
//        mTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myView.startScroll();
//                myView.invalidate();
//            }
//        });
    }
    class MyThread extends Thread {
        @Override
        public void run() {
            int y = 0;
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                    y += 50;
                    sendMessage(y);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void sendMessage(int y) {
        Message message = Message.obtain();
        message.obj = y;
        mHandler.sendMessage(message);
    }
    class MyHandler extends Handler {
        int y = 0;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            y = (Integer)msg.obj;
            mTvScrollBy.setVisibility(View.VISIBLE);
            mTvScrollBy.scrollTo(-10,-60);
            mTvScrollBy.setText("测试scrollBy滑动" + y);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVt = VelocityTracker.obtain();
        mVt.addMovement(event);
        mVt.computeCurrentVelocity(1000);
        mX = (int) mVt.getXVelocity();
        mY = (int) mVt.getYVelocity();
        mTv.setText("当前速度为：x = " + mX + ",y = " + mY);
        return true;
    }

    @Override
    public int getId() {
        return R.layout.activity_view_speed_detect;
    }
}
