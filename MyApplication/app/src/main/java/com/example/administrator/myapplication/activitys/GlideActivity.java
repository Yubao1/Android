package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class GlideActivity extends XActivity {

    @Override
    public void init() {
        ImageView iv = findViewById(R.id.iv);
        String url = "http://img4.imgtn.bdimg.com/it/u=3971356495,3339395545&fm=26&gp=0.jpg";
        Glide.with(this)
                .load(url)
                .into(iv);
    }

    @Override
    public int getId() {
        return R.layout.activity_glid;
    }
}
