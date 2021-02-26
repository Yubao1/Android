package com.example.administrator.androidart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.androidart.activites.Demo3Activity;

/**
 * Created by Administrator on 2020/7/5.
 */

public class MyViewGroup2 extends ViewGroup {
    private Context mContext;

    public MyViewGroup2(Context context) {
        this(context, null);
    }

    public MyViewGroup2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view1 = new View(mContext);
        view1.setBackgroundColor(0xFF1DADB5);

        View view2 = new View(mContext);
        view2.setBackgroundColor(0xFFBB1E89);

        View view3 = new View(mContext);
        view3.setBackgroundColor(0xFFD96022);

        addView(view1);
        addView(view2);
        addView(view3);
    }

    /**
     * ViewGroup的onLayout是用來確定子view的位置的,并不是確定當前ViewGroup的位置,
     * 如果沒有子view可以不做處理
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int right = Demo3Activity.SCREEN_WIDTH;
        int bottom = Demo3Activity.SCREEN_HEIGHT-600;

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);

            childView.layout(left, top, right, bottom);

            left = left + Demo3Activity.SCREEN_WIDTH;
            right = right + Demo3Activity.SCREEN_WIDTH;
        }
    }
}

