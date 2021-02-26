package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * 当父类的dispatchTouchEvent(MotionEvent event) 的 MotionEvent.ACTION_UP序列事件返回 true时，子类的onClick方法不会被调用
 */
public class ViewEventDistributionActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(ViewEventDistribution1Activity.class);
                break;
            case R.id.btn_2:
                startActivity(ViewEventDistribution2Activity.class);
                break;
            case R.id.btn_3:
                startActivity(ViewEventDistribution3Activity.class);
                break;
            case R.id.btn_4:
                startActivity(ViewEventDistribution4Activity.class);
                break;
            case R.id.btn_5:
                startActivity(ViewEventDistribution5Activity.class);
                break;
            case R.id.btn_6:
                startActivity(ViewEventDistribution6Activity.class);
                break;
            case R.id.btn_7:
                startActivity(ViewEventDistribution7Activity.class);
                break;
            case R.id.btn_8:
                startActivity(ViewEventDistribution8Activity.class);
                break;
        }
    }
}
