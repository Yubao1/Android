package com.example.administrator.androidart.activites;

import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * 当drawable.setLevel(n); n越大时，剪切的图片的比例就越少
 */
public class ClipDrawableActivity extends XActivity {
    private TextView mTv;
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
        ClipDrawable drawable = (ClipDrawable)mTv.getBackground();
        drawable.setLevel(8000);
    }

    @Override
    public int getId() {
        return R.layout.activity_clip_drawable;
    }
}
