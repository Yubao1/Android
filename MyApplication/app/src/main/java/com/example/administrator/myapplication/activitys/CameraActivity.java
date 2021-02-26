package com.example.administrator.myapplication.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.tool.BindEventBus;
//import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.CheckPermissions;
import com.example.administrator.myapplication.views.RoundImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


@BindEventBus
public class CameraActivity extends XActivity {
    private static final int REQUEST_FRONT_CAMERA_PERMISSION = 100;
    private static final int REQUEST_BACK_CAMERA_PERMISSION = 101;
    private static final int CODE_CAMERA_DETECTION = 2;
    private TextView tvPhoneVersion;
    private RelativeLayout rl;
    private Bitmap bitmap = null;
    @Override
    public void init() {
        tvPhoneVersion = findViewById(R.id.tv_phone_version);
        rl = findViewById(R.id.rl);
    }

    @Override
    public int getId() {
        return R.layout.activity_camera;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_front_take_photo:
                frontTakePhoto();
                break;
            case R.id.btn_behind_take_photo:
                behindTakePhoto();
                break;
            case R.id.btn_behind_test_take_photo:
                isOpenTakePhoto();
                break;
        }
    }
    private void isOpenTakePhoto() {
        boolean canUse = false;
        Camera mCamera = null;

        try {
            mCamera = Camera.open(0);
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }

        if (mCamera != null) {
            mCamera.release();
            canUse = true;
        }
        String s = null;
        if (canUse) {
            s = "是";
        } else {
            s = "否";
        }
        tvPhoneVersion.setText("是否可以打开后置摄像头？" + s);
    }
    private void behindTakePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CheckPermissions permissions = new CheckPermissions(this);
            if (permissions.checkPermissions(REQUEST_BACK_CAMERA_PERMISSION)) {
                Intent intent = new Intent();
                intent.setClass(this, CameraKitActivity.class);
                intent.putExtra("facing", 0);
                startActivityForResult(intent, CODE_CAMERA_DETECTION);
                tvPhoneVersion.setText("Android 6.0版本以上手机");
            }
        } else {
            try {
                Camera camera = Camera.open();
                camera.release();
                camera = null;
                Intent intent = new Intent();
                intent.setClass(this, CameraDetectionActivity.class);
                intent.putExtra("facing", Camera.CameraInfo.CAMERA_FACING_BACK);

                startActivityForResult(intent, CODE_CAMERA_DETECTION);
                tvPhoneVersion.setText("Android 6.0版本以下手机");
            } catch (Exception e) {
                e.printStackTrace();
                tvPhoneVersion.setText("无相机权限，请授权后使用");
            }
        }
    }


    private void frontTakePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CheckPermissions permissions = new CheckPermissions(this);
            if (permissions.checkPermissions(REQUEST_FRONT_CAMERA_PERMISSION)) {
                Intent intent = new Intent();
                intent.setClass(this, CameraKitActivity.class);
                intent.putExtra("facing", 1);
                startActivityForResult(intent, CODE_CAMERA_DETECTION);
                tvPhoneVersion.setText("Android 6.0版本以上手机");
            }

        } else {
            try {
                Camera camera = Camera.open();
                camera.release();
                camera = null;
                Intent intent = new Intent();
                intent.setClass(this, CameraDetectionActivity.class);
                intent.putExtra("facing", Camera.CameraInfo.CAMERA_FACING_FRONT);
                startActivityForResult(intent, CODE_CAMERA_DETECTION);
                tvPhoneVersion.setText("Android 6.0版本以下手机");
            } catch (Exception e) {
                e.printStackTrace();
                tvPhoneVersion.setText("无相机权限，请授权后使用");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_CAMERA_DETECTION:
                if (resultCode == 1) {
                    try {
                        if (bitmap != null) {
                            rl.removeAllViews();
                            int radius = Math.min(rl.getWidth(), rl.getHeight()) / 2;
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(radius, radius);
                            RoundImageView roundImageView = new RoundImageView(this, bitmap, radius / 2);
                            rl.addView(roundImageView, layoutParams);
                            roundImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showPhoto(bitmap);
                                }
                            });
                        } else {
                        }
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }

    private void showPhoto(Bitmap bitmap) {
        final ImageView imageView = new ImageView(CameraActivity.this);
        final PopupWindow mPopupWindow = new PopupWindow(imageView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x77777777));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = CameraActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                CameraActivity.this.getWindow().setAttributes(lp);
            }
        });
        mPopupWindow.showAtLocation(rl.getRootView(), Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams lp = CameraActivity.this.getWindow().getAttributes();
        lp.alpha = 0.6f;
        CameraActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        CameraActivity.this.getWindow().setAttributes(lp);
        imageView.setImageBitmap(bitmap);

    }
}
