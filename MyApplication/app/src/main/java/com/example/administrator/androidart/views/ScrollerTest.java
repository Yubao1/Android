package com.example.administrator.androidart.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by 86188 on 2020/7/6.
 */

public class ScrollerTest extends ViewGroup {
    /**定义Scroller对象*/
    private Scroller mScroller;
    /**定义系统默认滑动系数*/
    private int mTapSlop;
    /**记录按下时的x方向坐标*/
    private int mDownX;
    /**控件左边界*/
    private int mLeft;
    /**控件右边界*/
    private int mRight;
    /**屏幕宽度*/
    private int mScreenWidth;
    /**当前显示的页面角标*/
    private int mIndex = 0;

    public ScrollerTest(Context context) {
        this(context, null);
    }

    public ScrollerTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取屏幕宽度,为了简单将所有的直接孩子控件的宽度设置成屏幕宽度
        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        systemService.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        // 获取系统默认滑动系数
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTapSlop = viewConfiguration.getScaledDoubleTapSlop();
        // 1、初始化Scroller对象
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 测量每一个孩子控件的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = this.getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
        // getDefaultSize()：作用是返回一个默认的值，如果MeasureSpec没有强制限制的话则使用提供的大小.否则在允许范围内可任意指定大小
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }

    // 继承至ViewGroup，重写onLayout()方法确定每一个孩子控件的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) { // 判断是否需要重新布局
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = this.getChildAt(i);
                int childViewMeasuredWidth = childView.getMeasuredWidth();
                // 指定每一个孩子控件的位置，这里就是直接水平排列每一个孩子控件
                childView.layout(i * childViewMeasuredWidth, 0, (i + 1) * childViewMeasuredWidth, childView.getMeasuredHeight());
            }
            mLeft = this.getChildAt(0).getLeft();
            mRight = this.getChildAt(childCount - 1).getRight();
        }
    }

    //    @Override // 判断是否需要拦截事件的方法
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = (int) ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int moveX = (int) ev.getX();
//                int diffX = Math.abs(moveX - mDownX);
//                mDownX = moveX;
//                if (diffX > mTapSlop) {
//                    // 如果移动的距离大于默认滑动系数就拦截事件
//                    return true;
//                }
//                break;
//            default:
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

//    @Override // 处理事件的方法
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                if(mScroller != null && !mScroller.isFinished()){
//                    // 如果当前还没有完成滑动，就强制结束滑动状态
//                    mScroller.abortAnimation();
//                }
//                mDownX = (int) event.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int moveX = (int) event.getX();
//                int dX = mDownX - moveX;
//                // 边界处理
//                if (getScrollX() + dX < mLeft) {
//                    scrollTo(mLeft, 0);
//                } else if (getScrollX() + mScreenWidth + dX > mRight) {
//                    scrollTo(mRight - mScreenWidth, 0);
//                } else {
//                    // 非边界，直接移动
//                    scrollBy(dX, 0);
//                    mDownX = moveX;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                // 计算应该显示的是第几页
//                mIndex = (getScrollX() + mScreenWidth / 2) / mScreenWidth;
//                // 计算需要滑动的距离
//                int scrollX = mIndex * mScreenWidth - getScrollX();
//                ///2、开始滑动
//                mScroller.startScroll(getScrollX(), 0, scrollX, 0, scrollX);
//                invalidate();
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    // 3、维持滑动状态
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) { // 判断是否已经完成滑动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();// 不断通过postInvalidate()方法调用draw()方法重绘界面
        }
    }

    // 滑动到指定位置
    public void setPosition(int position) {
        if (position > getChildCount() - 1 || position < 0) {
            throw new ArrayIndexOutOfBoundsException("页面位置角标错误");
        }
        int dX = position * mScreenWidth - getScrollX();
        mScroller.startScroll(getScrollX(), 0, dX, 0, dX);
        postInvalidate();
    }

}
