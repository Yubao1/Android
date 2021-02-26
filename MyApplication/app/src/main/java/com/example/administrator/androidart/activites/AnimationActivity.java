package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class AnimationActivity extends XActivity {
    @Override
    public void init() {
//        ImageView imageView = findViewById(R.id.iv);
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.view_animation);
//        imageView.startAnimation(animation);
    }

    @Override
    public int getId() {
        return R.layout.activity_animation;
    }
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                startActivity(AnimationXmlActivity.class);
                break;
            case R.id.tv_2:
                startActivity(AnimationJavaActivity.class);
                break;
            case R.id.tv_3:
                startActivity(CustomAnimationActivity.class);
                break;
            case R.id.tv_4:
                startActivity(AnimationDrawableActivity.class);
                break;
            case R.id.tv_5:
                startActivity(LayoutAnimationActivity.class);
                break;
            case R.id.tv_6:
                startActivity(LayoutAnimationJavaActivity.class);
                break;
            case R.id.tv_7:
                startActivity(ActivityAnimationEffectActivity.class);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.tv_8:
                startActivity(ObjectAnimationXmlActivity.class);
                break;
            case R.id.tv_9:
                startActivity(AnyAttributeAnnimationActivity.class);
                break;
            case R.id.tv_10:
                startActivity(ValueAnimationActivity.class);
                break;
        }
    }
}
