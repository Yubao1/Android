package com.example.administrator.androidart.activites;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewEventActivity extends XActivity {

    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_event;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_view_speed_detect_activity:
                startActivity(ViewSpeedDetectActivity.class);
                break;
            case R.id.tv_view_general_slide_activity:
                startActivity(ViewGeneralSlideActivity.class);
                break;
            case R.id.tv_view_elastic_slide_activity:
                startActivity(ViewElasticSlideActivity.class);
                break;
        }
    }
}
