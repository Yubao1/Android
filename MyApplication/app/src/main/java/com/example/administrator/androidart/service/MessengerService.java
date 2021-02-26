package com.example.administrator.androidart.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by Administrator on 2020/5/23.
 */

public class MessengerService extends Service {
    private MessengerServiceHandler mHandler = null;
    private static final String TAG  = "MessengerService";
    private Messenger mServiceMesstenger = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServiceMesstenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int pid = android.os.Process.myPid();
        new MyLogcat().d(getClass(),TAG + "------------------------" + pid);
        mHandler = new MessengerServiceHandler();
        mServiceMesstenger = new Messenger(mHandler);
    }

    static class MessengerServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.MSG_FORM_CLIENT:
                    String content = msg.getData().getString("msg");
                    Log.d(TAG,content);
                    Messenger clientMessenger = msg.replyTo;
                    Message message = Message.obtain(null,Constant.MSG_FROM_SERVICE);
                    String replyContent = "你的信息我收到了，稍后回复你";
                    Bundle bundle = new Bundle();
                    bundle.putString("replyContent",replyContent);
                    message.setData(bundle);
                    try {
                        clientMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
