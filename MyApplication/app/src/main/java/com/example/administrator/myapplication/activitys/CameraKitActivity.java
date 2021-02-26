package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.DisplayUtils;
import com.example.administrator.myapplication.tool.ImageUtil;
import com.flurgle.camerakit.CameraKit;
import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;

import org.greenrobot.eventbus.EventBus;

import static android.graphics.BitmapFactory.decodeByteArray;

public class CameraKitActivity extends XActivity {
    CameraView mCameraView;
    Button mButton;
    private int mCameraFacing = 0;
    private Handler mHandler = new Handler();
    @Override
    public void init() {
        Intent intent = getIntent();
        if (intent == null) {
            setResult(0);
            finish();
            return;
        }
        mCameraView = findViewById(R.id.camera_kit_view);
        mButton = findViewById(R.id.bt_take_photo_kit);
        mCameraFacing = intent.getIntExtra("facing", 0);
        hideBottomUIMenu();
    }
    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mCameraView.setFacing(mCameraFacing == 0 ? CameraKit.Constants.FACING_BACK : CameraKit.Constants.FACING_FRONT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mHandler.postDelayed(mRunnable, 500);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
    }
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                mCameraView.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onPause() {
        try {
            mCameraView.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_take_photo_kit:
                mButton.setClickable(false);
                mCameraView.setCameraListener(new CameraListener() {
                    @Override
                    public void onPictureTaken(byte[] picture) {
                        super.onPictureTaken(picture);
                        Bitmap result = decodeByteArray(picture, 0, picture.length);
                        Point point = DisplayUtils.getScreenMetrics(getApplicationContext());
                        Bitmap smallBitmap = ImageUtil.compressBySize(result, point.x, point.y);
                        EventBus.getDefault().post(smallBitmap);
                                setResult(1);
                                finish();

                    }
                });
                try {
                    mCameraView.captureImage();
                } catch (Exception e) {
                    mButton.setClickable(true);
                }
                break;
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_camera_kit;
    }
}
