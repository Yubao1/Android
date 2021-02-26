package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class BitmapLoadAndCacheActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_bitmap_load_and_cache;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_image_loader:
                startActivity(ImageLoaderActivity.class);
                break;
            case R.id.btn_bitmap_load:
                startActivity(BitmapLoadActivity.class);
                break;
        }
    }
}
