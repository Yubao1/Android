package com.example.administrator.androidart.activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.io.InputStream;

import butterknife.BindView;

public class BitmapDrawableActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_bitmap_drawable;
    }
}
