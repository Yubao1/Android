package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.views.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ClipImageActivity extends XActivity implements View.OnClickListener{
    private ClipViewLayout clipViewLayout;
    @Override
    public void init() {
        initUI();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //设置图片资源
        clipViewLayout.setImageSrc(getIntent().getStringExtra("FilePath"));
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            finish();
            return;
        }
        if (v.getId() == R.id.tv_reselect) {
            reselectPhoto();
            return;
        }
        if (v.getId() == R.id.tv_ok) {
            generateUriAndReturn();
            return;
        }
    }
    private void reselectPhoto() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap = clipViewLayout.clip();
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    private void initUI() {
        clipViewLayout = findViewById(R.id.clipViewLayout);

        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_reselect).setOnClickListener(this);
        findViewById(R.id.tv_ok).setOnClickListener(this);
    }

    @Override
    public int getId() {
        return R.layout.activity_clip_image;
    }
}
