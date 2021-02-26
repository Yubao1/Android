package com.example.administrator.androidart.activites;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewGeneralSlideActivity extends XActivity {
    private TextView mTv;
    private MyHandler mHandler = null;
    private boolean isRunThread = true;
    private int mSlideY = 20;
    private int mSlideX = 20;
    ObjectAnimator animator;
    private MyThread thread = null;
    @Override
    public void init() {
        mTv = findViewById(R.id.tv_mobile);
        mHandler = new MyHandler();
    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTv.scrollBy(mSlideX,mSlideY);
//            Toast.makeText(ViewGeneralSlideActivity.this,"被点击了",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunThread = false;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    class MyThread extends Thread {
        protected boolean isJump = false;
        @Override
        public void run() {
            int j = 0;
            super.run();
            while (isRunThread) {
                try {
                    Thread.sleep(500);
                    sendMessage();
                    if (isJump) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void sendMessage() {
        Message message = Message.obtain();
        mHandler.sendMessage(message);
    }
    @Override
    public int getId() {
        return R.layout.activity_view_general_slide;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_scroll:
//                mSlideX += 20;
//                mSlideY += 20;
//                mTv.scrollBy(mSlideX,mSlideY);
                performScroll();
                break;
            case R.id.tv_object_animation:
                startObjectAnimation();
                break;
            case R.id.tv_change_layout:
                changeLayoutParam();
                break;
            case R.id.tv_mobile:
                Toast.makeText(this,"点击了可移动的View",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_scroll:
                startActivity(MoreScrollActivity.class);
                break;
            case R.id.tv_random_slide:
                startActivity(ViewRandomSlideActivity.class);
                break;
        }
    }

    /**
     * 改变布局参数时，View的位置会发生改变，而不是View内容的位置
     */
    private void changeLayoutParam() {
        LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams)mTv.getLayoutParams();
        params.topMargin = 400;
        mTv.setLayoutParams(params);
    }

    /**
     * 使用属性动画时，发生改变View的位置，而不是View内容的位置
     */
    private void startObjectAnimation() {
         animator = ObjectAnimator.ofFloat(mTv,"translationY",0,200);
        animator.setDuration(6000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 是对View内容的滑动，并不是View的滑动
     */
    private void performScroll() {
        if (thread != null) {
            thread.isJump = true;
        }
        thread = new MyThread();
        isRunThread = true;
        thread.start();
    }
}
