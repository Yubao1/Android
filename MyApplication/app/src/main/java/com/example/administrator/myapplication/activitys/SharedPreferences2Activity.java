package com.example.administrator.myapplication.activitys;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class SharedPreferences2Activity extends XActivity {
    private TextView mTv;
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
    }
    public void onClick(View view) {

        //在不同的Activity中，获取的都是同一个SharedPreferences对象数据
        SharedPreferences sp = getSharedPreferences("my_file", MODE_PRIVATE);
        String s = sp.getString("key","空的");
        mTv.setText(s);
    }
    @Override
    public int getId() {
        return R.layout.activity_shared_preferences2;
    }
}
