package com.example.administrator.myapplication.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2020/3/10.
 */

public class CustomScrollView extends ScrollView{
    private boolean isRollingTop = false;
    private boolean isRollingBottom = false;
    private boolean isJudgeRolling = false;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setJudgeRolling (boolean c) {
        isJudgeRolling = c;
    }
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (isJudgeRolling) {
            if (scrollY == 0) {
                isRollingTop = clampedY;
                isRollingBottom = false;
            } else {
                isRollingTop = false;
                isRollingBottom = clampedY;
            }
            notifyScrollChangedListeners();
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void notifyScrollChangedListeners() {
        String strDescribe = null;
        if (isRollingTop) {
            strDescribe = "滑动到顶部";
        } else if (isRollingBottom) {
            strDescribe = "滑动到底部";
        }
//        if (isRollingTop) {
//            EventBus.getDefault().post("isRollingTop---------------true");
////            Log.e("TAG","isRollingTop---------------true");
//        } else if (isRollingBottom) {
        if (!TextUtils.isEmpty(strDescribe)) {
            EventBus.getDefault().post(strDescribe);
        }
//            Log.e("TAG","isRollingBottom---------------true");
//        }
    }
}
