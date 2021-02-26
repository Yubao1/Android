package com.example.administrator.androidart.activites;

import android.graphics.drawable.ScaleDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * scaleHeight属性的百分比越大，缩放的比例就越高
 */
public class ScaleDrawableActivity extends XActivity {
    private TextView mTv;
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
        ScaleDrawable drawable = (ScaleDrawable)mTv.getBackground();
        drawable.setLevel(1);
    }

    @Override
    public int getId() {
        return R.layout.activity_scale_drawable;
    }
}
