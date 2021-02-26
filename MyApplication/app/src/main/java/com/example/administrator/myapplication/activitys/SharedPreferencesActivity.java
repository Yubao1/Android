package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class SharedPreferencesActivity extends XActivity {
    private TextView mTv;

    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:

                //在不同的Activity中，获取的都是同一个SharedPreferences对象数据
                SharedPreferences sp = getSharedPreferences("my_file", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String s = "数据";
                editor.putString("key", s);
                editor.apply();
                mTv.setText(s);
                break;
            case R.id.btn2:
                Intent intent = new Intent(this,SharedPreferences2Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_shared_preferences;
    }
}
