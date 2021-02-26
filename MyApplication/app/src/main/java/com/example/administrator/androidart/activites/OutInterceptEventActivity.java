package com.example.administrator.androidart.activites;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.other.FullyLinearLayoutManager2;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.adapter.OutInterceptEventAdapter;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class OutInterceptEventActivity extends XActivity {
    private List<View> viewList;
    @Override
    public void init() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewList = new ArrayList<>();
        initData(true);
        viewPager.setAdapter(new ViewPagerAdapter(viewList));
    }
    private void initData(boolean flag) {
        for (int j = 0; j < 4; j++) {
            View view;
            if (flag) {
                ListView listView = new ListView(this);
                List<String> dataList = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    dataList.add("leavesC " + i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                view = listView;
            } else {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView.setText("leavesC " + j);
                view = textView;
            }
            viewList.add(view);
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_out_intercept_event;
    }
}
