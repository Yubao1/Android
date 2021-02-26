package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.dialog.SelectPhotoDialog;
import com.example.administrator.myapplication.tool.PhotoUtil;
import com.example.administrator.myapplication.views.CircleImageView;

import java.io.File;

public class ReplaceQQHeadPortraitActivity extends XActivity {
    CircleImageView ivAvatar;
    TextView tvNick;
    private PhotoUtil photoUtil;
    private SelectPhotoDialog selectPhotoDialog;
    @Override
    public void init() {
        ivAvatar = findViewById(R.id.iv_avatar);
        tvNick = findViewById(R.id.tv_nick);
        photoUtil = new PhotoUtil(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                showSelectPhotoDialog();
                break;
        }
    }
    private void showSelectPhotoDialog() {
        if (selectPhotoDialog == null) {
            selectPhotoDialog = new SelectPhotoDialog(this);
            selectPhotoDialog.setSelectPhotoListener(new SelectPhotoDialog.SelectPhotoListener() {
                @Override
                public void takePhoto() {

                    if (photoUtil != null) {
                        photoUtil.takeCamera(true);
                    }
                }

                @Override
                public void selectPhoto() {

                    if (photoUtil != null) {
                        photoUtil.choosePhoto(true);
                    }
                }
            });
        }
        selectPhotoDialog.show();
    }
    @Override
    public int getId() {
        return R.layout.activity_replace_qqhead_portrait;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (photoUtil != null) {
            photoUtil.onActivityResult(requestCode, resultCode, data, new PhotoUtil.OnPhotoBackListener() {
                @Override
                public void onSuccess(Bitmap bitmap, File file) {
                    Glide.clear(ivAvatar);
                    Glide.with(ReplaceQQHeadPortraitActivity.this).load(file).dontAnimate().into(ivAvatar);
                }
            });
        }
    }
}
