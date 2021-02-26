package com.example.administrator.myapplication.activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.views.CircleProgress;

public class ProgressBar2Activity extends XActivity {
    private CircleProgress mCircleProgress;
    private final static int[] COLORS = new int[]{Color.RED, Color.RED, Color.RED,Color.RED};
    int i = 1;
    float f = 0.04f;
    @Override
    public void init() {
        mCircleProgress = (CircleProgress) findViewById(R.id.circle_progress_bar);
        mCircleProgress.reset();
    }
    public void onClick(View view) {
        mCircleProgress.setGradientColors(COLORS);
        mCircleProgress.setValue(i * f * mCircleProgress.getMaxValue());
        i++;
        if (i == 25) {
            i = 1;
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_progress_bar2;
    }
}
