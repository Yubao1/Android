package com.example.administrator.androidart.activites;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;


public class ObjectAnimationXmlActivity extends XActivity {
    private Button btn;
    @Override
    public void init() {
        btn = findViewById(R.id.btn);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.property_animation);
        set.setTarget(btn);
        set.start();
    }

    @Override
    public int getId() {
        return R.layout.activity_object_animation_xml;
    }
    public void onClick(View view) {

    }
}
