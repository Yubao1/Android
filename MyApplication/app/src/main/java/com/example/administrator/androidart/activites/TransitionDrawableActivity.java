package com.example.administrator.androidart.activites;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class TransitionDrawableActivity extends XActivity {
    Button btn;
    @Override
    public void init() {
        btn = findViewById(R.id.btn);
        TransitionDrawable drawable = (TransitionDrawable)btn.getBackground();
        drawable.startTransition(2000);
    }

    @Override
    public int getId() {
        return R.layout.activity_transition_drawable;
    }
}
