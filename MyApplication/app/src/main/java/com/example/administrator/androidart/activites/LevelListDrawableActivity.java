package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * 1、当所有的item 用minLevel这个属性时，这个属性值谁最大，就显示谁的drawable
 * 2、当所有的item 用maxLevel这个属性时，哪个item排列在第一位，就显示哪一个item的drawable，与maxLevel属性值大小无关
 */
public class LevelListDrawableActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_level_list_drawable;
    }
    public void onClick(View view) {

    }
}
