package com.example.administrator.androidart.activites;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ValueAnimatorActivity extends XActivity {
    int startX = 0;
    int deltaX = 200;
    private TextView mTvAnimatorObject;
    private TextView mTvShowProportion;
    @Override
    public void init() {
        mTvAnimatorObject = findViewById(R.id.tv_animator_object);
        mTvShowProportion = findViewById(R.id.tv_show_proportion);
    }
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int x = (startX + (int)(deltaX * fraction)) * (-1);
                mTvAnimatorObject.scrollTo(x,0);
                mTvShowProportion.setText("显示动画比例：" + fraction);
            }
        });
        animator.start();
    }
    @Override
    public int getId() {
        return R.layout.activity_value_animator;
    }
}
