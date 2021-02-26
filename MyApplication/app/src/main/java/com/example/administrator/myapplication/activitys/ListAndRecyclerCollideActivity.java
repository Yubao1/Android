package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.adapter.MyListAdapter;
import com.example.administrator.myapplication.adapter.OutInterceptEventAdapter;
import com.example.administrator.myapplication.views.CustomScrollView3;
import com.example.administrator.myapplication.views.MyListView;

import java.util.ArrayList;
import java.util.List;

public class ListAndRecyclerCollideActivity extends XActivity {
    private MyListView listView;
    private CustomScrollView3 scrollView;
    @Override
    public void init() {
        scrollView = (CustomScrollView3) findViewById(R.id.sv);
        listView = (MyListView) findViewById(R.id.lv);
        listView.setAdapter(new MyListAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        scrollView.smoothScrollTo(0, 0);
    }


    @Override
    public int getId() {
        return R.layout.activity_scroll_and_recycler_collide;
    }
}
