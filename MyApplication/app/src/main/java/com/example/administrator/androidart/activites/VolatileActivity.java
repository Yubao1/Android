package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

import java.util.Random;

public class VolatileActivity extends XActivity {
    private boolean isRunThread = true;
    private TextView mTv;
    private String s = "text";
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
        Thread threadA = new MyThreadA("MyThreadA");
        Thread threadB = new MyThreadB("MyThreadB");
        Thread threadC = new MyThreadC("MyThreadC");
        threadA.start();
        threadB.start();
        threadC.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunThread = false;
    }

    @Override
    public int getId() {
        return R.layout.activity_volatile;
    }
    class MyThreadA extends Thread {
        private String name;
        public MyThreadA(String name) {
            super(name);
            this.name = name;
        }
        @Override
        public void run() {
            super.run();
            while (isRunThread) {
//                new MyLogcat().d(VolatileActivity.this.getClass(),s );
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                s = name + "---------" + new Random().nextInt(100) + "-------------";
            }
        }
    }
    class MyThreadB extends Thread {
        private String name;
        public MyThreadB(String name) {
            super(name);
            this.name = name;
        }
        @Override
        public void run() {
            super.run();
            while (isRunThread) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new MyLogcat().d(VolatileActivity.this.getClass(),s + Thread.currentThread().getName());
//                s = name + "---------" + new Random().nextInt(100) + "-------------" + Thread.currentThread().getName();

            }
        }
    }
    class MyThreadC extends Thread {
        private String name;
        public MyThreadC(String name) {
            super(name);
            this.name = name;
        }
        @Override
        public void run() {
            super.run();
            while (isRunThread) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new MyLogcat().d(VolatileActivity.this.getClass(),s + Thread.currentThread().getName());
//                s = name + "---------" + new Random().nextInt(100) + "-------------" + Thread.currentThread().getName();

            }
        }
    }
}
