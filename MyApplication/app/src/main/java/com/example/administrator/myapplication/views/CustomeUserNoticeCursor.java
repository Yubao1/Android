package com.example.administrator.myapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2020/3/10.
 */

public class CustomeUserNoticeCursor extends View {
    Paint mPaint;
    private CustomeUserNoticeScrollView custome;
    private Context context;
    public float h = 0;
    private float userNoticeCursorWidth;
    private boolean flag = false;
    boolean isFlag = true;
    float yMove = 0;
    int num = 0;
    float x1 = 0,x2 = 0;
    float y1 = 0, y2 = 0;
    private View view;

    /**
     * View的高
     */
    private int h2 = 0;

    /**
     * 是否要判断滑动条滚动到顶部还是底部，true表示是
     */
    boolean isJudgeRolling = false;
    public CustomeUserNoticeCursor(Context context, AttributeSet attrs,
                                   int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initRes();
        setOnTouchListener();
    }
    public void setH(int h) {
        this.h2 = h;
    }
    public void setJudgeRolling(boolean flag) {
        isJudgeRolling = flag;
    }
    public void setView(View view) {
        this.view = view;
    }

    public void setCustome(CustomeUserNoticeScrollView custome) {
        this.custome = custome;
    }
    private void setOnTouchListener() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (CustomeUserNoticeCursor.this.getVisibility() == View.VISIBLE) {
                    touch(v,event);
                }
                return true;
            }
        });
    }
    private void setLinearLayoutAlpha(float f) {
        if (view != null) {
            view.setAlpha(f);
        }
    }
    private void touch(View v, MotionEvent event) {
        int f = event.getAction();
        int y = (int)v.getScaleY();
        float x3 = event.getX();
        float y3 = event.getY();
//        Log.e("TAG","-----------this.getScaleY()" + y2);
        int h = y+dp2px(context, 80);
        if (f == MotionEvent.ACTION_DOWN){
//            Log.e("TAG","-----------getY()" + event.getY());
            if ((x1 <= x3 && x3 <= x2) && (y3 >= y1 && y3 <= y2)) {
                flag = true;
                setLinearLayoutAlpha(0.5f);
            }
            num = 1;
        } else if (f == MotionEvent.ACTION_UP) {
            num = 1;
            if (flag) {
                flag = false;
                setLinearLayoutAlpha(1f);
            }
        } else {
            if (flag) {
                float yMove2 = event.getY();
                float yMove3 = 0;
//                Log.e("TAG","-----------"+ yMove2+"触摸方向" + event.getY());

                if (num == 1) {
                    num++;
                    yMove = yMove2;
                } else {
                    yMove3 = yMove2 - yMove;
                    yMove = yMove2;
                }

//                Log.e("TAG","-----------move" + yMove);
                y2 = (int)((y2 + yMove3) * 1);
                float proportion = yMove3 / this.h;
                int move2 =0 /*(int)(proportion * custome.getH() + custome.getScaleY())*/;
                int j = -1;
                float f2 = ((proportion * custome.noticeScrollViewTotleHeight) * j + custome.getScaleY()) / 2;
                int i2 = (int)(f2 * 10);
                int i = i2 % 10;
                if (i > 4) {
                    move2 = (int) (f2 + 1);
                } else {
                    move2 = (int) f2;
                }
                custome.scrollBy(0, move2);
//                Log.e("Tag", "--------------Y" + yMove3);
                invalidate();
            }
        }
    }

    private void initRes() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        userNoticeCursorWidth = dp2px(context, 48);
        x1 = /*userNoticeCursorWidth / 4 */0;
        x2 = /*userNoticeCursorWidth / 4 */userNoticeCursorWidth / 2;
    }

    public CustomeUserNoticeCursor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomeUserNoticeCursor(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        y1 = cursorMoveY;
        y2 = cursorMoveY + dp2px(context, 160);
//        Log.e("TAG","--------------y1--------" + y1);
//        Log.e("TAG","--------------h2--------" + h2);
//        Log.e("TAG","--------------dp2px--------" + dp2px(context, 160));
        RectF rect3 = new RectF(x1, y1,
                x2, y2);
        canvas.drawRoundRect(rect3, 10, 10, mPaint);
//        if (isFlag) {
//            isFlag = false;
//            y = (int)this.getScaleY();
//            Log.e("TAG","-----------scaleY" + y);
//            y2 = h - dp2px(context, 80);
//        }
//        if (this.getScaleY() <= y) {
//            SetOffSet(y);
//        }
//        if (this.getScaleY() >= y2) {
//            SetOffSet(y2);
//        }
        onScrolledMeasure();
    }

    private void onScrolledMeasure() {
        if (isJudgeRolling) {
            String str = null;
            if (y1 <= 10) {
                str = "滑动到顶部";
            }
            if (h2 != 0) {
                if (y1 >= h2 - dp2px(context, 160) -10) {
                    str = "滑动到底部";
                }
            }
            if (!TextUtils.isEmpty(str)) {
                EventBus.getDefault().post(str);
            }
        }
    }

    private float cursorMoveY;

    public void SetOffSet(float cursorMoveY) {
        this.cursorMoveY = cursorMoveY;
//        Log.i("TAG", ",proportionVisibleHeight:" + cursorMoveY);
        invalidate();
    }

    //
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

