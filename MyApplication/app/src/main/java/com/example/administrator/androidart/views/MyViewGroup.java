package com.example.administrator.androidart.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by 86188 on 2020/6/29.
 */

public class MyViewGroup extends LinearLayout {

    private Scroller scroller;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    public void startScroll() {
         /*
           调用startScroll()方法来初始化滚动数据并刷新界面
           startScroll()第一个参数是滚动开始时X的坐标，第二个参数是滚动开始时Y的坐标，
           第三个参数是横向滚动的距离，正值表示向左滚动，第四个参数是纵向滚动的距离，正值表示向上滚动
          */
        scroller.startScroll(0, getScrollY(), 0, -200);
        invalidate();
    }

    @Override
    public void computeScroll() {
        //判断是否滚动完成
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }
    }
}
