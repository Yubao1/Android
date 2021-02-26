package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimationJavaActivity extends XActivity {
    private ListView mLv;
    private List<String> mList;
    @Override
    public void init() {
        initView();
        initList();
        setAdapter();
    }
    private void setAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.view_animation);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mLv.setLayoutAnimation(controller);
        mLv.setAdapter(arrayAdapter);
    }

    private void initList() {
        mList = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            mList.add("item---" + i);
        }
    }

    private void initView() {
        mLv = findViewById(R.id.lv);
    }
    @Override
    public int getId() {
        return R.layout.activity_layout_animation_java;
    }
}
