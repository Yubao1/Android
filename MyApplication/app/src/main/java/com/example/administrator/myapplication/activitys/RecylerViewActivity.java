package com.example.administrator.myapplication.activitys;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.RecyclerViewAdpter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、RecyclerView.Adapter使用notifyDataSetChanged()方法不会打乱显示视图item的位置，
 * 即将RecyclerView滑动到第35个item，再调用notifyDataSetChanged()方法，眼前看到的不是第一个item，而是
 * 第35个item
 */
public class RecylerViewActivity extends XActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdpter mAdpter;
    private int position = 0;
    @Override
    public void init() {
        initView();
        initAdapter();
    }

    private void initAdapter() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("item---" + i);
        }
        boolean[] isSelect = new boolean[list.size()];
        for (int i = 0; i < isSelect.length; i++) {
            isSelect[i] = false;
        }
        mAdpter = new RecyclerViewAdpter(this,list,isSelect);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdpter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public int getId() {
        return R.layout.activity_recyler_view;
    }
    public void onClick(View view) {
       switch (view.getId()) {
           case R.id.btn_add:
               mAdpter.addList(2,"你好" + position);
               position++;
               break;
           case R.id.btn_delete:
               mAdpter.deleteList(2);
               break;
           case R.id.btn_grid:
               mRecyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
               break;
       }
    }
}
