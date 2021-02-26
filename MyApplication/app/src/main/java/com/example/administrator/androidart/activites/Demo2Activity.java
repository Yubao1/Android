package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.util.Arrays;
import java.util.List;

public class Demo2Activity extends XActivity {
    private ListView mListView;

    private List<String> mDatas = Arrays.asList(
            "item",
            "item",
            "item",
            "item",
            "item",
            "item",
            "item",
            "item",
            "item",
            "item",
            "item",
            "item"
    );

    @Override
    public void init() {
        mListView = (ListView) findViewById(R.id.listview);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, mDatas);

        mListView.setAdapter(arrayAdapter);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mListView.scrollBy(-20, -20);
                break;
            case R.id.btn2:
                mListView.scrollTo(-300, -50);
                break;
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_demo2;
    }
}
