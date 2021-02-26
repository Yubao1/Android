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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.R;
import com.example.administrator.androidart.service.MessengerService;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.tool.MyLogcat;

public class MessengerActivity extends XActivity {
    private static final String TAG = "MessengerActivity";
    private Messenger mMessenger = null;
    private MyServiceConnection mConnect = null;
    private MessengerClientHandler mHandler = null;
    private Messenger mClientMesstenger = null;
    @Override
    public void init() {
        int pid = android.os.Process.myPid();
        new MyLogcat().d(getClass(),TAG + "------------------------" + pid);
        mHandler = new MessengerClientHandler();
        mClientMesstenger = new Messenger(mHandler);
         mConnect = new MyServiceConnection();
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,mConnect, Context.BIND_AUTO_CREATE);
    }
    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message message = Message.obtain(null, Constant.MSG_FORM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg","hello this is client");
            message.setData(bundle);
            message.replyTo = mClientMesstenger;
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    static class MessengerClientHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.MSG_FROM_SERVICE:
                    String fromServiceContent = msg.getData().getString("replyContent");
                    Log.d(TAG,fromServiceContent);
                    break;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        unbindService(mConnect);
    }

    @Override
    public int getId() {
        return R.layout.activity_messenger;
    }
}
