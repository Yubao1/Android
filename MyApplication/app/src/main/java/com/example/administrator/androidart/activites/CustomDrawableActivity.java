package com.example.administrator.androidart.activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.androidart.drawable.RoundImageDrawable;
import com.example.administrator.myapplication.XActivity;

public class CustomDrawableActivity extends XActivity {
    @Override
    public void init() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),

                R.drawable.img);

        ImageView iv = (ImageView) findViewById(R.id.iv);

        iv.setImageDrawable(new RoundImageDrawable(bitmap));
    }

    @Override
    public int getId() {
        return R.layout.activity_custom_drawable;
    }
}
