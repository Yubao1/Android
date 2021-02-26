package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class AnimationXmlActivity extends XActivity {

    @Override
    public void init() {
        ImageView imageView = findViewById(R.id.iv);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.view_animation);
        animation.setDuration(5000);
        imageView.startAnimation(animation);
    }

    @Override
    public int getId() {
        return R.layout.activity_animation_xml;
    }
}
