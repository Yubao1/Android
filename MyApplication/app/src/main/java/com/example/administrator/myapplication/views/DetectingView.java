package com.example.administrator.myapplication.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 86188 on 2020/7/6.
 */

public class DetectingView extends View {
    private int mWidth;
    private int mHeight;

    private float mBaseLong;

    private Paint mScaleRoundPaint;
    private float mScaleRoundStrokeWidth = 1.6f;
    private float mScaleRoundRadius;

    private Paint mBubblePaint;
    private float mBubbleStrokeWidth = 0.9f;
    private float mBubbleLineLength;
    private float mBubbleRoundRadius;

    private Paint mScalePaint;
    private Paint mLongScalePaint;
    private float mScaleStrokeWidth = 1.2f;
    private float mLongScaleStrokeWidth = 0.8f;
    private float mScaleLineLong;
    private float mScaleLineShort;

    private Paint mScanPaint;
    private Paint mScanPaint3;
    private Shader mScanShader;
    private Shader mScanShader2;
    private float mScanRadius;

    private Paint mArcPaint1;
    private Paint mArcPaint2;
    private Paint mArcPaint3;
    private Paint mArcPaint4;
    private Paint mArcPaint5;
    private float mArcWidth1 = 48.0f;
    private float mArcWidth2 = 46.0f;
    private float mArcWidth3 = 32.0f;
    private float mArcWidth4 = 30.0f;
    private float mArcWidth5 = 28.0f;
    private float mArcLAngle1 = 80.0f;
    private float mArcLAngle2 = 30.0f;
    private float mArcLAngle3 = 35.0f;
    private float mArcLAngle4 = 85.0f;
    private float mArcLAngle5 = 80.0f;
    private float mArcSpeed1 = 1.0f;
    private float mArcSpeed2 = -1.0f;
    private float mArcSpeed3 = 3.0f;
    private float mArcSpeed4 = -2.0f;
    private float mArcSpeed5 = 2.0f;
    private float mCurrentAngle1 = 0.0f;
    private float mCurrentAngle2 = 0.0f;
    private float mCurrentAngle3 = 0.0f;
    private float mCurrentAngle4 = 0.0f;
    private float mCurrentAngle5 = 0.0f;
    private float mArcRadius;

    private float scanYEnd = 0;
    private float scanYStart = 0;
    private boolean scanDown = true;

    private static final float everyDegrees = (float) (2.0f * Math.PI / 180);

    private List<Bubble> mBubbles = new ArrayList<>();
    private static final int maxBubbleCount = 10;
    private Random mRandom = new Random();

    private Paint mPhonePaint;
    private Bitmap mBmPhone;
    private float mid = 0;
    private Paint paint;
    private Paint mScanPaint2;
    private final int C = 3990722;
    private int startC = 165231;
    private Paint[] paints;
    private int startColor = 0;
    int color1 = 0xff00C3A1;
    int color2 = 0xff02856F;
//    android:startColor="#02856F"
//    android:endColor="#3CE4C2"
    /**
     * num数组用于存储颜色值,num[0] = a,num[1] = r,num[2] = g;
     */
    private int[] num;

    /**
     * num2数组用于存储结束时颜色值,num2[0] = a,num2[1] = r,num2[2] = g;
     */
    private int[] num2;

    /**
     * 画白线的起始位置
     */
    private float lineStart = 0;

    public DetectingView(Context context) {
        super(context);
//        this(context, null);
    }

    public DetectingView(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
        super(context,attrs);
        initPaint();
        mBmPhone = BitmapFactory.decodeResource(getResources(), R.drawable.phone_structure);
    }

//    public DetectingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
////        this(context, attrs, defStyleAttr, 0);
//        initPaint();
//        mBmPhone = BitmapFactory.decodeResource(getResources(), R.drawable.phone_structure);
//    }

//    public DetectingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initPaint();
//        mBmPhone = BitmapFactory.decodeResource(getResources(), R.drawable.phone_structure);
//    }

    public void initPaint1000() {
        num = new int[3];
        num2 = new int[3];
        paints = new Paint[100];
//        String s = Integer.toHexString(startC);
//        String s2 = Integer.toHexString(C);
//        onCalculateColor(s,num);
//        onCalculateColor(s2,num2);
//        int[] values = new int[3];
//        for (int i = 0; i < 3; i++) {
//            values[i] = (num2[i] -num[i]) / 100;
//        }
        int a = 0;
        for (int i = 0; i < 100; i++) {
            paints[i] = new Paint();
            float fraction = a/100f;
            int color = blendColors(color1,color2,fraction);
            paints[i].setColor(color);
//            paints[i].setAlpha(125);
//            paints[i].setAlpha(110);
//            num[0] += values[0];
//            num[1] += values[1];
//            num[2] += values[2];
        }
    }

    /**
     * 颜色渐变
     *
     * @param color1 起始颜色
     * @param color2 终止颜色
     * @param ratio 颜色变化频率 从0-1
     * @return 颜色值
     */
    private int blendColors(int color1, int color2, float ratio) {
        float inverseRatio = 1.0F - ratio;
        float a = (float) Color.alpha(color1) * inverseRatio + (float) Color.alpha(color2) * ratio;
        float r = (float) Color.red(color1) * inverseRatio + (float) Color.red(color2) * ratio;
        float g = (float) Color.green(color1) * inverseRatio + (float) Color.green(color2) * ratio;
        float b = (float) Color.blue(color1) * inverseRatio + (float) Color.blue(color2) * ratio;
        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }
    private void onCalculateColor(String s,int[] num) {
        if (s.length() == 1) {
            s += "00000";
        }
        if (s.length() == 2) {
            s += "0000";
        }
        if (s.length() == 3) {
            s += "000";
        }
        if (s.length() == 4) {
            s += "00";
        }
        if (s.length() == 5) {
            s += "0";
        }
        char[] chars = s.toCharArray();
        for (int i = 0, j = 0; i < 3; i++, j += 2) {
            num[i] = colorValues(chars[j], chars[j + 1]);
        }
    }

    private int colorValues(char c1, char c2) {
        int num1 = getNum(c1);
        int num2 = getNum(c2);
        return num1 * 16 + num2;
    }

    private int getNum(char c) {
        String s = c + "";
        int num = 0;
        if (s.equals("f")) {
            num = 15;
        } else if (s.equals("e")) {
            num = 14;
        } else if (s.equals("d")) {
            num = 13;
        } else if (s.equals("c")) {
            num = 12;
        } else if (s.equals("b")) {
            num = 11;
        } else if (s.equals("a")) {
            num = 10;
        } else {
            num = Integer.valueOf(s);
        }
        return num;
    }

    private void initPaint() {
        initPaint1000();
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.white));
        mScanPaint2 = new Paint();
        mScanPaint2.setColor(getResources().getColor(R.color.red));

        mScaleRoundPaint = new Paint();
        mScaleRoundPaint.setColor(getResources().getColor(R.color.white_transport_0));
        mScaleRoundPaint.setStrokeWidth(mScaleRoundStrokeWidth);
        mScaleRoundPaint.setAntiAlias(true);
        mScaleRoundPaint.setStyle(Paint.Style.STROKE);

        mBubblePaint = new Paint();
        mBubblePaint.setAntiAlias(true);
        mBubblePaint.setStrokeWidth(mBubbleStrokeWidth);
        mBubblePaint.setColor(getResources().getColor(R.color.white_transport_0));
        mBubblePaint.setStyle(Paint.Style.STROKE);
        mBubblePaint.setStrokeCap(Paint.Cap.ROUND);

        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setStrokeWidth(mScaleStrokeWidth);
        mScalePaint.setColor(getResources().getColor(R.color.white_transport_0));
        mScalePaint.setStyle(Paint.Style.STROKE);
        mScalePaint.setStrokeCap(Paint.Cap.ROUND);

        mLongScalePaint = new Paint();
        mLongScalePaint.setAntiAlias(true);
        mLongScalePaint.setStrokeWidth(mLongScaleStrokeWidth);
        mLongScalePaint.setColor(getResources().getColor(R.color.white_transport_0));
        mLongScalePaint.setStyle(Paint.Style.STROKE);
        mLongScalePaint.setStrokeCap(Paint.Cap.ROUND);

        mScanPaint = new Paint();
        mScanPaint.setAntiAlias(true);
        mScanPaint3 = new Paint();
        mScanPaint3.setAntiAlias(true);
        mScanPaint.setColor(getResources().getColor(R.color.green));
        mScanPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mArcPaint1 = new Paint();
        mArcPaint1.setAntiAlias(true);
        mArcPaint1.setStrokeWidth(mArcWidth1);
        mArcPaint1.setColor(getResources().getColor(R.color.white_transport_1));
        mArcPaint1.setStyle(Paint.Style.STROKE);

        mArcPaint2 = new Paint();
        mArcPaint2.setAntiAlias(true);
        mArcPaint2.setStrokeWidth(mArcWidth2);
        mArcPaint2.setColor(getResources().getColor(R.color.white_transport_2));
        mArcPaint2.setStyle(Paint.Style.STROKE);

        mArcPaint3 = new Paint();
        mArcPaint3.setAntiAlias(true);
        mArcPaint3.setStrokeWidth(mArcWidth3);
        mArcPaint3.setColor(getResources().getColor(R.color.white_transport_3));
        mArcPaint3.setStyle(Paint.Style.STROKE);

        mArcPaint4 = new Paint();
        mArcPaint4.setAntiAlias(true);
        mArcPaint4.setStrokeWidth(mArcWidth4);
        mArcPaint4.setColor(getResources().getColor(R.color.white_transport_3));
        mArcPaint4.setStyle(Paint.Style.STROKE);

        mArcPaint5 = new Paint();
        mArcPaint5.setAntiAlias(true);
        mArcPaint5.setStrokeWidth(mArcWidth5);
        mArcPaint5.setColor(getResources().getColor(R.color.white_transport_2));
        mArcPaint5.setStyle(Paint.Style.STROKE);

        mPhonePaint = new Paint();
        mPhonePaint.setAntiAlias(true);
        mPhonePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mBaseLong = mWidth / 48;
        mScaleRoundRadius = mWidth / 2 - mBaseLong;
        mScaleLineLong = (mWidth / 2 - mScaleRoundRadius) * 2;
        mScaleLineShort = mScaleLineLong / 8;
        mArcRadius = mWidth / 2 - mBaseLong * 4;
        mScanRadius = mWidth / 2 - mBaseLong * 6;

        mArcWidth1 = mBaseLong;
        mArcWidth2 = mBaseLong;
        mArcWidth3 = mBaseLong / 2;
        mArcWidth4 = mBaseLong / 2;
        mArcWidth5 = mBaseLong / 2;

        mBubbleRoundRadius = mWidth / 2 - mBaseLong * 6;
        mBubbleLineLength = mBaseLong / 8;

        while (mBubbles.size() < maxBubbleCount) {
            Bubble bubble = new Bubble();
            bubble.height = mRandom.nextFloat() * mBaseLong;
            bubble.angle = mRandom.nextFloat() * 360;
            mBubbles.add(bubble);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth / 2, mHeight / 2, mScaleRoundRadius, mScaleRoundPaint);

        drawPhone(canvas);

        drawBubbles(canvas);

        drawLines(canvas);

        drawArc(canvas);

        drawScan(canvas);

        postInvalidateDelayed(20);
    }

    private void drawPhone(Canvas canvas) {
        canvas.save();

        float le = (float) Math.sqrt(mBmPhone.getWidth() * mBmPhone.getWidth() + mBmPhone.getHeight() * mBmPhone.getHeight()) / 2;
        float scale = mScanRadius / le;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        canvas.translate(mWidth / 2 - mBmPhone.getWidth() * scale / 2, mHeight / 2 - mBmPhone.getHeight() * scale / 2);
        canvas.drawBitmap(mBmPhone, matrix, mPhonePaint);

        canvas.restore();
    }

    private void drawBubbles(Canvas canvas) {
        canvas.save();
        for (int i = 0; i < 360; i++) {
            float degrees = i * everyDegrees / 2;
            float startX;
            float startY;
            float stopX;
            float stopY;
            startX = mWidth / 2 + (float) Math.sin(degrees) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius - mBubbleLineLength));
            startY = mHeight / 2 - (float) Math.cos(degrees) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius - mBubbleLineLength));
            stopX = mWidth / 2 + (float) Math.sin(degrees) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius + mBubbleLineLength));
            stopY = mHeight / 2 - (float) Math.cos(degrees) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius + mBubbleLineLength));
            canvas.drawLine(startX, startY, stopX, stopY, mBubblePaint);
        }
        for (int i = 0; i < mBubbles.size(); i++) {
            float fl = mBubbles.get(i).height;
            float ag = mBubbles.get(i).angle;
            float step = -1.0f;
            boolean left = false;
            while (fl > 0.1) {
                float startX;
                float startY;
                float stopX;
                float stopY;
                startX = mWidth / 2 + (float) Math.sin(ag) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius - mBubbleLineLength - fl));
                startY = mHeight / 2 - (float) Math.cos(ag) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius - mBubbleLineLength - fl));
                stopX = mWidth / 2 + (float) Math.sin(ag) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius + mBubbleLineLength + fl));
                stopY = mHeight / 2 - (float) Math.cos(ag) * (Math.min(mWidth / 2, mHeight / 2) - (mWidth / 2 - mBubbleRoundRadius + mBubbleLineLength + fl));
                canvas.drawLine(startX, startY, stopX, stopY, mBubblePaint);

                if (!left) {
                    if (fl + step < 0.2) {
                        left = true;
                        fl = mBubbles.get(i).height + step;
                        ag = mBubbles.get(i).angle;
                        continue;
                    }
                }
                if (left) {
                    ag = (ag - everyDegrees / 2) % 360;
                } else {
                    ag = (ag + everyDegrees / 2) % 360;
                }
                if (left && fl < 0.2) {
                    break;
                }
                fl += step;
            }
            if (mBubbles.get(i).growing) {
                mBubbles.get(i).height += mBubbles.get(i).growSpeed;
                if (mBubbles.get(i).height > mBaseLong) {
                    mBubbles.get(i).growing = false;
                }
            } else {
                mBubbles.get(i).height -= mBubbles.get(i).growSpeed;
                if (mBubbles.get(i).height < 0.2) {
                    mBubbles.get(i).angle = mRandom.nextFloat() * 360;
                    mBubbles.get(i).height = 0.2f;
                    mBubbles.get(i).growSpeed = mRandom.nextFloat() * 0.6f + 0.2f;
                    mBubbles.get(i).growing = true;
                }
            }
        }
        canvas.restore();
    }

    private void drawLines(Canvas canvas) {
        canvas.save();

        for (int i = 0; i < 180; i++) {
            float degrees = i * everyDegrees;
            float startX;
            float startY;
            float stopX;
            float stopY;
            if (i % 5 == 0) {
                startX = mWidth / 2 + (float) Math.sin(degrees) * (Math.min(mWidth / 2, mHeight / 2));
                startY = mHeight / 2 - (float) Math.cos(degrees) * (Math.min(mWidth / 2, mHeight / 2));
                stopX = mWidth / 2 + (float) Math.sin(degrees) * (Math.min(mWidth / 2, mHeight / 2) - mScaleLineLong);
                stopY = mHeight / 2 - (float) Math.cos(degrees) * (Math.min(mWidth / 2, mHeight / 2) - mScaleLineLong);
                canvas.drawLine(startX, startY, stopX, stopY, mLongScalePaint);
            }
            startX = mWidth / 2 + (float) Math.sin(degrees) * (Math.min(mWidth / 2, mHeight / 2) - mScaleLineLong / 2 + mScaleLineShort);
            startY = mHeight / 2 - (float) Math.cos(degrees) * (Math.min(mWidth / 2, mHeight / 2) - mScaleLineLong / 2 + mScaleLineShort);
            stopX = mWidth / 2 + (float) Math.sin(degrees) * (Math.min(mWidth / 2, mHeight / 2) - mScaleLineLong / 2 - mScaleLineShort);
            stopY = mHeight / 2 - (float) Math.cos(degrees) * (Math.min(mWidth / 2, mHeight / 2) - mScaleLineLong / 2 - mScaleLineShort);
            canvas.drawLine(startX, startY, stopX, stopY, mScalePaint);
        }

        canvas.restore();
    }

    private void drawArc(Canvas canvas) {
        canvas.save();

        canvas.drawArc(mWidth / 2 - mArcRadius, mWidth / 2 - mArcRadius, mWidth / 2 + mArcRadius, mWidth / 2 + mArcRadius, mCurrentAngle1, mArcLAngle1, false, mArcPaint1);
        mCurrentAngle1 += mArcSpeed1;
        mCurrentAngle1 %= 360;

        canvas.drawArc(mWidth / 2 - mArcRadius, mWidth / 2 - mArcRadius, mWidth / 2 + mArcRadius, mWidth / 2 + mArcRadius, mCurrentAngle2, mArcLAngle2, false, mArcPaint2);
        mCurrentAngle2 += mArcSpeed2;
        mCurrentAngle2 %= 360;

        canvas.drawArc(mWidth / 2 - mArcRadius, mWidth / 2 - mArcRadius, mWidth / 2 + mArcRadius, mWidth / 2 + mArcRadius, mCurrentAngle3, mArcLAngle3, false, mArcPaint3);
        mCurrentAngle3 += mArcSpeed3;
        mCurrentAngle3 %= 360;

        canvas.drawArc(mWidth / 2 - mArcRadius, mWidth / 2 - mArcRadius, mWidth / 2 + mArcRadius, mWidth / 2 + mArcRadius, mCurrentAngle4, mArcLAngle4, false, mArcPaint4);
        mCurrentAngle4 += mArcSpeed4;
        mCurrentAngle4 %= 360;

        canvas.drawArc(mWidth / 2 - mArcRadius, mWidth / 2 - mArcRadius, mWidth / 2 + mArcRadius, mWidth / 2 + mArcRadius, mCurrentAngle5, mArcLAngle5, false, mArcPaint5);
        mCurrentAngle5 += mArcSpeed5;
        mCurrentAngle5 %= 360;

        canvas.restore();
    }

    private void drawScan(Canvas canvas) {
        canvas.save();

        if (scanDown) {
            if (scanYEnd > mWidth / 2 + mScanRadius) {
                scanDown = false;
            }
        } else {
            if (scanYEnd < mWidth / 2 - mScanRadius) {
                scanDown = true;
            }
        }
        if (scanDown) {
            scanYStart += 16;
            scanYEnd = scanYStart - mScanRadius;
            startColor = getResources().getColor(R.color.startColor);
            mScanShader = new LinearGradient(mWidth / 2, scanYEnd, mWidth / 2, scanYStart, Color.TRANSPARENT, getResources().getColor(R.color.white_transport_2), Shader.TileMode.CLAMP);
            mScanShader2 = new LinearGradient(mWidth / 2, scanYEnd, mWidth / 2, scanYStart, Color.TRANSPARENT, startColor, Shader.TileMode.CLAMP);

        } else {
            scanYStart -= 16;
            scanYEnd = scanYStart + mScanRadius;
            startColor = getResources().getColor(R.color.startColor);
            mScanShader = new LinearGradient(mWidth / 2, scanYEnd, mWidth / 2, scanYStart, Color.TRANSPARENT, getResources().getColor(R.color.white_transport_2), Shader.TileMode.CLAMP);
            mScanShader2 = new LinearGradient(mWidth / 2, scanYEnd, mWidth / 2, scanYStart, Color.TRANSPARENT, startColor, Shader.TileMode.CLAMP);
        }
        mScanPaint.setShader(mScanShader);
        mScanPaint.setAlpha(110);
        Path path = new Path();
        path.addCircle(mWidth / 2, mHeight / 2, mScanRadius, Path.Direction.CW);
        canvas.clipPath(path);

        mScanPaint3.setShader(mScanShader2);
        mScanPaint3.setAlpha(110);
        canvas.drawRect(0, scanYEnd, mWidth, scanYStart, mScanPaint3);
        canvas.drawRect(0, scanYEnd, mWidth, scanYStart, mScanPaint);

//        drawMoreRect(canvas);
        if (scanYStart > scanYEnd) {
            /**
             * 扫描时的一条白线
             */
            canvas.drawRect(0, scanYStart, mWidth,scanYStart + 6, paint);
        }
        canvas.restore();
    }

    public void drawMoreRect(Canvas canvas) {
        float startY = scanYEnd;
        float c = (scanYStart - startY) / 100;
        float startE = startY + c;
        for (int i = 0; i < 100; i++) {
            canvas.drawRect(0, startY, mWidth, startE, paints[i]);
            startY = startE;
            startE += c;

        }
        lineStart = startY;
    }

    public class Bubble {
        public float height;
        public float angle;
        public boolean growing = false;
        public float growSpeed = 0.4f;
    }
}
