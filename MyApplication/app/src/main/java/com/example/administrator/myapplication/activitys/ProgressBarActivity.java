package com.example.administrator.myapplication.activitys;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ProgressBarActivity extends XActivity {
    ProgressBar mPb;
    @Override
    public void init() {
        mPb = findViewById(R.id.progressBar);
    }

    @Override
    public int getId() {
        return R.layout.activity_progress_bar;
    }
    public void onClick(View view) {
        mPb.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
    }
}
