package com.example.administrator.androidart.activites2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.AidlActivity;
import com.example.administrator.androidart.aidl.Book;
import com.example.administrator.androidart.aidl.IBookManager;
import com.example.administrator.androidart.aidl.IOnNewBookArrivedListener;
import com.example.administrator.androidart.service.BookManagerService;
import com.example.administrator.myapplication.XActivity;

import java.lang.ref.WeakReference;

public class Aidl2Activity extends XActivity{
    private static final String TAG = "Aidl2Activty";
    private MyServiceConnection mConnect = null;
    private MyHandler mHandler = null;
    IBookManager mManager = null;
    IBinder mBinder = null;
    @Override
    public void init() {
        mConnect = new MyServiceConnection();
        mHandler = new MyHandler(this);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent,mConnect, Context.BIND_AUTO_CREATE);
    }
    private IOnNewBookArrivedListener iOnNewBookArrivedListener = new IOnNewBookArrivedListener() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = book;
            mHandler.sendMessage(message);
        }

        @Override
        public IBinder asBinder() {
//            if (mBinder != null) {
//                return mBinder;
//            }
            return null;
        }
    };
    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mManager = IBookManager.Stub.asInterface(service);
            mBinder = service;
            try {
                mManager.registerListener(iOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            try {
                mManager.unregisterListener(iOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyHandler extends Handler {
        private WeakReference<Aidl2Activity> wr = null;
        public MyHandler(Aidl2Activity activity) {
            wr = new WeakReference<Aidl2Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Book book = (Book)msg.obj;
                    Log.d(TAG,book + ""
                    );
                    break;
                default:
                   break;
            }
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_aidl2;
    }
}
