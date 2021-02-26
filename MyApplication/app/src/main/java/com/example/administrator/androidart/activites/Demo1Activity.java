package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class Demo1Activity extends XActivity {

    private TextView mTextView;

    @Override
    public void init() {
        mTextView = (TextView) findViewById(R.id.textview);
    }

    @Override
    public int getId() {
        return R.layout.activity_demo1;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mTextView.scrollBy(20, 20);
                break;
            case R.id.btn2:
                mTextView.scrollTo(-300, -300);
                break;
        }
    }
}
