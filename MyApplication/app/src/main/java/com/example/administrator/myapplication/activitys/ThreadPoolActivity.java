package com.example.administrator.myapplication.activitys;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolActivity extends XActivity {
    private TextView tv;
    MyHandler handler;
    ExecutorService pool = null;
    private EditText et;
    MyRunnable[] runnables;
    @Override
    public void init() {
        tv = findViewById(R.id.tv);
        handler = new MyHandler(this);
        pool = Executors.newFixedThreadPool(2);
        et = findViewById(R.id.et);
        runnables = new MyRunnable[3];
        for (int i = 0; i < 3; i++) {
            runnables[i] = new MyRunnable("MyRunnable-----" + i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pool.shutdown();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void onClick(View view) {
//        String s = et.getText().toString();
//        int num = Integer.valueOf(s);

        for (int i = 0; i < 3; i++) {
            pool.execute(runnables[i]);
        }
//        pool.shutdown();
    }

    static class MyHandler extends Handler {
        WeakReference wr;
        StringBuffer sb;
        String s = null;
        ThreadPoolActivity activity;
        public MyHandler(ThreadPoolActivity activity) {
            wr = new WeakReference<ThreadPoolActivity>(activity);
            sb = new StringBuffer();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            s = (String) msg.obj;
            sb.append(s + "\n");
            activity = (ThreadPoolActivity)wr.get();
            activity.tv.setText(sb);
        }
    }

    class MyRunnable implements Runnable {
        private String name;
        public MyRunnable(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        @Override
        public void run() {
            for (int x = 0; x < 3; x++) {
                String s = Thread.currentThread().getName() + name + ":----" + x;
                Message message = Message.obtain();
                message.obj = s;
                handler.sendMessage(message);
            }
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_thread_pool;
    }
}
