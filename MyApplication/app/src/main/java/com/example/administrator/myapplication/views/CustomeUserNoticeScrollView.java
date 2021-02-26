package com.example.administrator.myapplication.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.example.administrator.myapplication.views.CustomeUserNoticeCursor;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2020/3/10.
 */

public class CustomeUserNoticeScrollView extends ScrollView {
    CustomeUserNoticeCursor cursor;
    public float noticeScrollViewTotleHeight;
    private float noticeScrollViewVisibleHeight;
    private Context context;
    boolean flag = true;
    float h = 0;
    float h2 = 0;
    float h3 = 0;

    /**
     * 是否要判断滑动条滚动到顶部还是底部，true表示是
     */
    boolean isJudgeRolling = false;

    /**
     * 是否滑动到顶部，true表示是
     */
    boolean isRollingTop = false;

    /**
     * 是否滑动到底部，true表示是
     */
    boolean isRollingBottom = false;
    public CustomeUserNoticeScrollView(Context context) {
        this(context, null);
    }

    public CustomeUserNoticeScrollView(Context context, AttributeSet attrs,
                                       int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

    }
    public float getH() {
        return h;
    }
    public CustomeUserNoticeScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        noticeScrollViewTotleHeight = this.getChildAt(0).getMeasuredHeight();
        noticeScrollViewVisibleHeight = this.getHeight();


    }
    public boolean canScroll() {
        View child = this.getChildAt(0);
        if (child != null) {
            int childHeight = child.getHeight();
            return this.getHeight() < childHeight;
        }
        return false;
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        scrollChangeMeasure();
        update();
    }

    private void scrollChangeMeasure() {
        if (isJudgeRolling) {
            if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
                if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                    isRollingTop = true;
                    isRollingBottom = false;
                } else if (getScrollY() + getHeight() - getPaddingTop()-getPaddingBottom() == getChildAt(0).getHeight()) {
                    isRollingBottom = true;
                    isRollingTop = false;
                } else {
                    isRollingTop = false;
                    isRollingBottom = false;
                }
//                notifyScrollChangedListeners();
            }
        }
    }

    public void update() {
        float currentScrollY = this.getScrollY();
        float proportionTotalHeight = currentScrollY
                / (noticeScrollViewTotleHeight - noticeScrollViewVisibleHeight);
        float cursorMoveY = proportionTotalHeight
                * (noticeScrollViewVisibleHeight/*h2*/ - dp2px(context, 160));
        float proportion = cursorMoveY / h;
        float cursorMoveY2 = proportion * h2;
        if (flag) {
            flag = false;
            h3 = cursorMoveY + h2;
        }

        float scroll = currentScrollY + h + 3*h2;
//        if (h-h2 > cursorMoveY) {
        cursor.SetOffSet(cursorMoveY);
//        }
    }

    /**
     * 判断滑动条滑动到顶部和底部的方法
     * @param scrollX
     * @param scrollY
     * @param clampedX
     * @param clampedY
     */
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
//            notifyScrollChangedListeners();
        }
    }
    public void setJudgeRolling(boolean isJudgeRolling) {
        this.isJudgeRolling = isJudgeRolling;
    }
    private void notifyScrollChangedListeners() {
        String strDescribe = null;
        if (isRollingTop) {
            strDescribe = "滑动到顶部";
        } else if (isRollingBottom) {
            strDescribe = "滑动到底部";
        }
        if (!TextUtils.isEmpty(strDescribe)) {
            EventBus.getDefault().post(strDescribe);
        }
    }
    public void setCursor(CustomeUserNoticeCursor user_notice_cursor) {
        this.cursor = user_notice_cursor;
        h = this.getHeight();
        h2 =dp2px(context, 160);
        float h3 = h - h2;
        user_notice_cursor.setCustome(this);
        user_notice_cursor.h = h3;
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
