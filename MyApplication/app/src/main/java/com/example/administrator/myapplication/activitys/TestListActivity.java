package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends XActivity {
    TextView mTv;
    @Override
    public void init() {
         mTv = findViewById(R.id.tv);
        List<String> list = new ArrayList<>();
         addList(list);
         String s = "";
         for (int i = 0; i < 10; i++) {
             s += list.get(i) + "\n";
         }
         mTv.setText(s);
    }

    private void addList(List<String> list) {
        for (int i = 0; i < 10; i++) {
            list.add("item---" + i);
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_test_list;
    }
}
