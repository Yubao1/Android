package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewRandomSlideActivity extends XActivity {

    @Override
    public void init() {

    }
    public void onClick(View view) {
        Toast.makeText(this, "TextView被点击了", Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getId() {
        return R.layout.activity_view_random_slide;
    }
}
