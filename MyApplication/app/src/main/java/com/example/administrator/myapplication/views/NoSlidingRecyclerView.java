package com.example.administrator.myapplication.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 86188 on 2020/7/1.
 */

public class NoSlidingRecyclerView extends RecyclerView {
    public NoSlidingRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public NoSlidingRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NoSlidingRecyclerView(Context context) {
        super(context);
    }

    /**
     * 设置不滚动
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
