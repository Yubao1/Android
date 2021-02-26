package com.example.administrator.androidart.activites;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class MoreScrollActivity extends ListActivity {
    String[] mDatas = new String[]{
            "demo1",
            "demo2",
            "demo3"
    };

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
                startActivity(new Intent(this, Demo1Activity.class));
                break;
            case 1:
                startActivity(new Intent(this, Demo2Activity.class));
                break;
            case 2:
                startActivity(new Intent(this, Demo3Activity.class));
                break;
        }
    }
//    @Override
//    public int getId() {
//        return R.layout.activity_more_scroll;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, mDatas);

        getListView().setAdapter(arrayAdapter);
    }
}
