package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class AnimationJavaActivity extends XActivity {
    @Override
    public void init() {
        ImageView imageView = findViewById(R.id.iv);
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(5000);
        imageView.startAnimation(animation);
        ImageView imageView2 = findViewById(R.id.iv_2);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,50,0, 50);
        scaleAnimation.setDuration(5000);
        imageView2.startAnimation(scaleAnimation);
        ImageView imageView3 = findViewById(R.id.iv_3);
        TranslateAnimation translateAnimation = new TranslateAnimation(imageView2.getX(),300,imageView2.getY(),imageView2.getY());
        translateAnimation.setDuration(5000);
        imageView3.startAnimation(translateAnimation);
        ImageView imageView4 = findViewById(R.id.iv_4);
        RotateAnimation rotateAnimation = new RotateAnimation(0f,360f,
                0.5f,0.5f);
        rotateAnimation.setDuration(5000);
        imageView4.startAnimation(rotateAnimation);

    }

    @Override
    public int getId() {
        return R.layout.activity_animation_java;
    }
}
