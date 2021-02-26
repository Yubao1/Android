package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2020/7/5.
 */

public class ViewRandomSlideTextView extends AppCompatTextView {
    private int mLastX = 0;
    private int mLastY = 0;
    public ViewRandomSlideTextView(Context context) {
        super(context);
    }

    public ViewRandomSlideTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewRandomSlideTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getRawX();
        int y = (int)event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                /**
                 * 要导入第三方动画开源库
                 */
                int translationX = ((int)ViewHelper.getTranslationX(this) + deltaX);
                int translationY = ((int)ViewHelper.getTranslationY(this) + deltaY);
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);
//                this.setText("translationX = " + translationX + "\n" + "translationY = " + translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastX = y;
        return true;
    }
}
