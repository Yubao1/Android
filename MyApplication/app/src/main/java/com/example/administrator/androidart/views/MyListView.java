package com.example.administrator.androidart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/13.
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private int lastX;

    private int lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //横向位移增量
                int deltaX = x - lastX;
                //竖向位移增量
                int deltaY = y - lastY;
                //如果横向滑动距离大于竖向滑动距离，则认为使用者是想要左右滑动
                //此时就通知父容器 ViewPager 处理此事件
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    new MyLogcat().d(getClass(),"-------------------requestDisallowInterceptTouchEvent(false)");
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
