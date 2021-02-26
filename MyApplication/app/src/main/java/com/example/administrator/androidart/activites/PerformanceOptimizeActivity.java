package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class PerformanceOptimizeActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_performance_optimize;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(MergeActivity.class);
                break;
            case R.id.btn_2:
                startActivity(ViewStubActivity.class);
                break;
        }
    }
}
