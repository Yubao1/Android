package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewEventDistribution6Activity extends XActivity {
    @Override
    public void init() {
        Button btn = findViewById(R.id.btn);
        TextView tv = findViewById(R.id.tv);
        TextView tvShowContent = findViewById(R.id.tv_show_content);
        boolean btnb = btn.isClickable();
        boolean tvb = tv.isClickable();
        tvShowContent.setText("Button的clickable为：" + btnb + "\n" + "TextView的clickable为：" + tvb);
    }

    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution6;
    }
}
