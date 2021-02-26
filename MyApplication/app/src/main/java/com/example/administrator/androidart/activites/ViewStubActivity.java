package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewStubActivity extends XActivity {
    ViewStub vs;
    View importPanel;//
    @Override
    public void init() {
        vs = findViewById(R.id.vs);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                vs.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_2:
                importPanel = vs.inflate();
                break;
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_view_stub;
    }
}
