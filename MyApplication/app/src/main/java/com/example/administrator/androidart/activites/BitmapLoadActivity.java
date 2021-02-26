package com.example.administrator.androidart.activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.io.IOException;
import java.io.InputStream;

public class BitmapLoadActivity extends XActivity {
    private ImageView mIv;

    @Override
    public void init() {
        mIv = findViewById(R.id.iv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
        tmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.timg_2, tmpOptions);
        tmpOptions.inSampleSize = calculateInSampleSize(tmpOptions,mIv.getMeasuredWidth(),mIv.getMaxHeight());
        tmpOptions.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg_2, tmpOptions);
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.timg_2);
        mIv.setImageBitmap(bitmap);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / height) >= reqHeight && (halfWidth / width) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Override
    public int getId() {
        return R.layout.activity_bitmap_load;
    }
}
