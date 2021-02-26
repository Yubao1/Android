package com.example.administrator.androidart.activites;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class AnimationDrawableActivity extends XActivity {
    @Override
    public void init() {
        ImageView iv = findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.animation_drawable);
        AnimationDrawable drawable = (AnimationDrawable)iv.getBackground();
        drawable.start();
    }

    @Override
    public int getId() {
        return R.layout.activity_animation_drawable;
    }
}
