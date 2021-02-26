package com.example.administrator.myapplication.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.service.MyService;

import static android.content.ContentValues.TAG;

public class RecordActivity extends XActivity {
    private static final int REQUEST_CODE = 1;
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_record;
    }
    public void onClick(View view) {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
//                != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "无录音权限");
//            if (Build.VERSION.SDK_INT >= 23) {
//                Log.i(TAG, "系统版本不低于android6.0 ，需要动态申请权限");
//                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
//                return;
//            }
//            Toast.makeText(this,"录音不正常",Toast.LENGTH_LONG).show();
//        } else {
//            Log.i(TAG, "有录音权限");
//            Toast.makeText(this,"录音正常",Toast.LENGTH_LONG).show();
//        }
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int result = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                result++;
            }
        }
        if (result != permissions.length) {
            //没有权限
            Log.i(TAG, "onRequestPermissionsResult: 申请权限完毕,当前录音权限:false");
            Toast.makeText(this,"申请权限完毕,录音不正常",Toast.LENGTH_LONG).show();
            return;
        }
        //有权限
        Log.i(TAG, "onRequestPermissionsResult: 申请后，是否有权限:true");
        Toast.makeText(this,"申请权限完毕,录音正常",Toast.LENGTH_LONG).show();
    }
}
