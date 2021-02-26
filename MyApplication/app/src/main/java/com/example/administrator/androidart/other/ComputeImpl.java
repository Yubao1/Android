package com.example.administrator.androidart.other;

import android.os.RemoteException;

import com.example.administrator.androidart.aidl.ICompute;

/**
 * Created by 86188 on 2020/6/22.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
