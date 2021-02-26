package com.example.administrator.androidart.activites2;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.FilePublicActivity;
import com.example.administrator.androidart.bean.User;
//import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.XActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;

public class FilePublic2Activity extends XActivity {
    private File mSdDir = null;
    private String mFileName = "my_file.txt";
    private TextView mTv;
    private MyHandler mHandler = null;
    @Override
    public void init() {
        mHandler = new MyHandler(this);
        mTv = findViewById(R.id.tv);
        mSdDir = Environment.getExternalStorageDirectory();//获取跟目录
        new MyThread().start();
    }
    static class MyHandler extends Handler {
        private WeakReference<FilePublic2Activity> wr;
        public MyHandler(FilePublic2Activity activity) {
            wr = new WeakReference<FilePublic2Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            wr.get().mTv.setText(s);
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_file_public2;
    }

    /**
     * 在手机上，一定要打开此app的  读写手机存储
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
//            User user = new User(1,"hello world",false);
//            if (!mSdDir.exists()) {
//                mSdDir.mkdirs();
//            }
            File file = new File(mSdDir, mFileName);
            if (file.exists()) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    User user = (User)ois.readObject();
                    sendMessage(user + "");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (ois != null) {
                        try {
                            ois.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    private void sendMessage(String value) {
        Message message = Message.obtain();
        message.obj = value;
        mHandler.sendMessage(message);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
