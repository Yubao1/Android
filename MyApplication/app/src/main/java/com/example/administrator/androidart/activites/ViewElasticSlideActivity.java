package com.example.administrator.androidart.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewElasticSlideActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_elastic_slide;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_view_scroller_slide_activity:
                Intent intent = new Intent(this,ViewScrollerSlideActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_value_animator_activity:
                startActivity(ValueAnimatorActivity.class);
                break;
        }
    }
}
