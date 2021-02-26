package com.example.administrator.androidart.activites;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.activites2.FilePublic2Activity;
import com.example.administrator.androidart.bean.User;
//import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.XActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;

public class FilePublicActivity extends XActivity {
    private File mSdDir = null;
    private String mFileName = "my_file.txt";
    private TextView mTv;
    private static final String TAG = "FilePublicActivity";
    private MyHandler mHandler = null;
    @Override
    public void init() {
        mHandler = new MyHandler(this);
        mTv = findViewById(R.id.tv);
        mSdDir = Environment.getExternalStorageDirectory();//获取跟目录
        new MyThread().start();
        File file = getSDPath();
        if (file != null) {
            Log.d(TAG,"file != null");
        } else {
            Log.d(TAG,"file == null");
        }
    }
    static class MyHandler extends Handler {
        private WeakReference<FilePublicActivity> wr;
        public MyHandler(FilePublicActivity activity) {
            wr = new WeakReference<FilePublicActivity>(activity);
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
        return R.layout.activity_file_public;
    }

    /**
     * 在手机上，一定要打开此app的  读写手机存储
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            User user = new User(1,"hello world",false);
            if (!mSdDir.exists()) {
                mSdDir.mkdirs();
            }
            File file = new File(mSdDir, mFileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ObjectOutputStream oos = null;
            try {

                Log.d("小周-FilePublicActivity", "---------------2222");
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(user);
                Log.d("小周-FilePublicActivity",user + "");
                sendMessage(user + "");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
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
    public File getSDPath(){
        File sdDir = null;
        boolean sdCardExist =Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        } else {
        }
        return sdDir;

    }
    public void onClick(View view) {
        startActivity(FilePublic2Activity.class);
    }
}
