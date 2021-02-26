package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.CameraInterface;
import com.example.administrator.myapplication.tool.DisplayUtils;
import com.example.administrator.myapplication.tool.ImageUtil;
import com.example.administrator.myapplication.views.CameraSurfaceView;

import org.greenrobot.eventbus.EventBus;

public class CameraDetectionActivity extends XActivity {
    CameraSurfaceView mSurfaceView;
    Button mButton;
    private float mPreviewRate = -1f;
    private int mCameraFacing = 0;
    @Override
    public void init() {
        getIntentValue();
        initView();
        hideBottomUIMenu();
        initViewParams();
    }
    private void initViewParams() {
        mPreviewRate = DisplayUtils.getScreenRate(this); //默认全屏的比例预览
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_take_photo:
                mButton.setClickable(false);
                CameraInterface.getInstance().doTakePicture();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread openCameraThread = new Thread() {
            @Override
            public void run() {
                CameraInterface.getInstance().setCameraOpenCallBack(mCameraOpenCallback)
                        .doOpenCamera(mCameraFacing);
            }
        };
        openCameraThread.start();
    }

    CameraInterface.CameraOpenCallback mCameraOpenCallback = new CameraInterface.CameraOpenCallback() {
        @Override
        public void cameraHasOpened(boolean hasOpened) {
            if (hasOpened) {
                SurfaceHolder holder = mSurfaceView.getSurfaceHolder();
                CameraInterface.getInstance()
                        .setPreviewRate(mPreviewRate)
                        .setSurfaceHolder(holder)
                        .setCameraCaptureCallback(mCameraCaptureCallback)
                        .doStartPreview();
            }
        }
    };

    CameraInterface.CameraCaptureCallback mCameraCaptureCallback = new CameraInterface.CameraCaptureCallback() {
        @Override
        public void onCameraCaptured(Bitmap bitmap) {
            if (bitmap != null) {
                Point point = DisplayUtils.getScreenMetrics(getApplicationContext());
                Bitmap smallBitmap = ImageUtil.compressBySize(bitmap, point.x, point.y);
                EventBus.getDefault().post(smallBitmap);
                        setResult(1);
                        finish();
            } else {
                setResult(0);
                finish();
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        CameraInterface.getInstance().doStopCamera();
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
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    private void getIntentValue() {
        Intent intent = getIntent();
        if (intent == null) {
            setResult(0);
            finish();
            return;
        }
        mCameraFacing = intent.getIntExtra("facing", 0);
    }

    private void initView() {
        mButton = findViewById(R.id.bt_take_photo);
        mSurfaceView = findViewById(R.id.sv_camera_preview);
    }

    @Override
    public int getId() {
        return R.layout.activity_camera_detection;
    }
}
