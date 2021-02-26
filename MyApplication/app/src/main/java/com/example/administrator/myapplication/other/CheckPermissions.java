package com.example.administrator.myapplication.other;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

import com.example.administrator.myapplication.activitys.CameraActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/22.
 */

public class CheckPermissions {
    private WeakReference<CameraActivity> wr;
    public CheckPermissions(CameraActivity activity) {
        wr = new WeakReference<CameraActivity>(activity);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkPermissions(int code) {
        String[] permissions = {Manifest.permission.CAMERA,};
        List<String> uncheckedPermissions = new ArrayList<String>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(wr.get(), permission) != PackageManager.PERMISSION_GRANTED) {
                uncheckedPermissions.add(permission);
            }
        }
        String[] uncheckedPermission = new String[uncheckedPermissions.size()];
        for (int i = 0; i < uncheckedPermissions.size(); i++) {
            uncheckedPermission[i] = uncheckedPermissions.get(i);
        }
        if (uncheckedPermissions.size() > 0) {
            wr.get().requestPermissions(uncheckedPermission, code);
            return false;
        } else {
            return true;
        }
    }
}
