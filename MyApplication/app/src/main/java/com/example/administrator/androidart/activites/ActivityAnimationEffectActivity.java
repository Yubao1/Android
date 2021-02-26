package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ActivityAnimationEffectActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_animation_effect;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }
}
