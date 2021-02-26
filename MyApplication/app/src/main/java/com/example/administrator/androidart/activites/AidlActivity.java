package com.example.administrator.androidart.activites;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.activites2.Aidl2Activity;
import com.example.administrator.androidart.aidl.Book;
import com.example.administrator.androidart.aidl.IBookManager;
import com.example.administrator.androidart.aidl.IOnNewBookArrivedListener;
import com.example.administrator.androidart.service.BookManagerService;
import com.example.administrator.myapplication.XActivity;

import java.lang.ref.WeakReference;
import java.util.List;

public class AidlActivity extends XActivity {
    private static final String TAG = "AidlActivity";
    private TextView mTv;
    private MyServiceConnection mConnect = null;
    private MyHandler mHandler = null;
    IBookManager mManager = null;
    IBinder mBinder = null;

    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
        mConnect = new MyServiceConnection();
        mHandler = new MyHandler(this);
        final Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnect, Context.BIND_AUTO_CREATE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent1 = new Intent(AidlActivity.this, Aidl2Activity.class);
//                startActivity(intent1);
            }
        },10000);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                Intent intent1 = new Intent(AidlActivity.this, Aidl2Activity.class);
                startActivity(intent1);
                break;
            case R.id.tv_2:
                if (mManager != null) {
                    try {
                        mManager.getBookList();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_aidl;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (mManager != null) {
            try {
                mManager.unregisterListener(iOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
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

    static class MyHandler extends Handler {
        private WeakReference<AidlActivity> wr = null;

        public MyHandler(AidlActivity activity) {
            wr = new WeakReference<AidlActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Book book = (Book) msg.obj;
                    Log.d(TAG, book + ""
                    );
                    break;
                default:
                    String s = (String) msg.obj;
                    wr.get().mTv.setText(s);
                    break;
            }
        }
    }

    class MyThread extends Thread {
        private IBookManager manager;

        public MyThread(IBookManager manager) {
            this.manager = manager;
        }

        @Override
        public void run() {
            super.run();
            try {
                List<Book> list = manager.getBookList();
                String s = "query book list,list type" + list.getClass().getCanonicalName();
                s = s + "\n" + "query book list" + list.toString();
                Message message = Message.obtain();
                message.obj = s;
                mHandler.sendMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /*mManager = IBookManager.Stub.asInterface(service);
            mBinder = service;
            try {
                mManager.registerListener(iOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            new MyThread(mManager).start();
            Log.d(TAG,"onServiceConnected---------" + Thread.currentThread().getName());*/
            if (service == null) {
                Log.d(TAG,"service----------------null");
            } else {
                Log.d(TAG,"service----------------不等于null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.d(TAG,"onBindingDied---------" + Thread.currentThread().getName());
        }
    }
}
