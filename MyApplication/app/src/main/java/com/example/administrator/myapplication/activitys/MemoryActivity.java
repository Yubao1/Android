package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * https://www.jianshu.com/p/e75680772375
 */
public class MemoryActivity extends XActivity {
    @Override
    public void init() {
        for (int i = 0; i < 1000;i++) {
            new Thread(new InnerClass()).start();
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_memory;
    }
    class InnerClass implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
