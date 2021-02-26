package com.example.administrator.androidart.activites;


import android.icu.text.LocaleDisplayNames;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.administrator.R;
import com.example.administrator.androidart.aidl.ICompute;
import com.example.administrator.androidart.aidl.ISecurityCenter;
import com.example.administrator.androidart.other.BinderPool;
import com.example.administrator.androidart.other.ComputeImpl;
import com.example.administrator.androidart.other.SecurityCenterImpl;
import com.example.administrator.myapplication.XActivity;

public class BinderActivity extends XActivity {
    private static final String TAG = "BinderActivity";
    private ISecurityCenter mSecurityCenter = null;
    private ICompute mCompute = null;
    @Override
    public void init() {
        new MyThread().start();
    }
    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            doWork();
        }
    }
    private void doWork() {
        Log.d(TAG,"--------------------doWork");
        BinderPool binderPool = BinderPool.getInstance(BinderActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        Log.d(TAG,"visit ISecurityBinder");
        String msg = "hellow world - 安卓";
        Log.d(TAG,"content:" + msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            Log.d(TAG,"encrypt:" + password);
            Log.d(TAG,"decrypt:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);
        try {
            Log.d(TAG,"3 + 5 = " + mCompute.add(3,5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_binder;
    }
}
