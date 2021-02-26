package com.example.administrator.androidart.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.R;

/**
 * Created by 86188 on 2020/7/25.
 */

public class CircleView extends View {
    private int mColor = Color.RED;
    private static final int WRAP_CONTENT = 300;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    public CircleView(Context context) {
//        super(context);
//        init(context);
//    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,@Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color,Color.RED);
        a.recycle();
        setColor();
    }
    private void setColor() {
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WRAP_CONTENT,WRAP_CONTENT);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WRAP_CONTENT,heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize,WRAP_CONTENT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft -paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;
        int radius = Math.min(width,height) / 2;
        canvas.drawCircle(paddingLeft + width / 2,paddingTop + height / 2,radius,mPaint);
    }
}
