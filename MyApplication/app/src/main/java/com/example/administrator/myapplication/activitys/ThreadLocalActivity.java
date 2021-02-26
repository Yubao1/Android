package com.example.administrator.myapplication.activitys;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.other.MyThread;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.MyThreadLocal;
import com.example.administrator.myapplication.tool.BindEventBus;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 1、每一个线程都会有一个ThreadLocal.ThreadLocalMap类型的变量，ThreadLocal在每个线程存入的值都会保留
 * 在这个 ThreadLocal.ThreadLocalMap类型的变量里；第一次进行set方法时，会调用createMap方法；第
 * 二次set方法时会调用map.set方法，再者就执行if (k == key)语句。
 *
 * 2、如果ThreadLocal.ThreadLocalMap不为空，每一次get方法都会调用map.getEntry方法，下一步就是执行if (e != null && e.get() == key)
 * 语句
 */
@BindEventBus
public class ThreadLocalActivity extends XActivity {
    private TextView mTv;
    private TextView mTv2;
    MyThreadLocal<Integer> threadLocal = new MyThreadLocal<>();
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
        mTv2 = findViewById(R.id.tv2);
        Thread t = new MyThread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(2);
                SystemClock.sleep(500);
                threadLocal.get();
                SystemClock.sleep(500);
                threadLocal.set(3);
                SystemClock.sleep(500);
                threadLocal.get();
            }
        });
        t.start();
//        SystemClock.sleep(500);
//        Thread t2 = new MyThread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocal.set(3);
//                new MyLogcat().d(getClass(),"---------------------threadLocal---value==" + threadLocal.get());
//            }
//        });
//        t2.start();
    }

    @Override
    public void onEventBusCall(EventBusCall event) {
        super.onEventBusCall(event);
        if (event.getId() == Constant.THREAD_LOCAL_ACTIVITY_RECEIVE_ID) {
            String msg = (String) event.getMessage()[0];
            mTv.setText("set方法的数据-----" + msg);
        } else if (event.getId() == Constant.THREAD_LOCAL_ACTIVITY_RECEIVE_ID_2) {
            String msg = (String) event.getMessage()[0];
            mTv2.setText("get方法的数据-----" + msg);
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_thread_local;
    }
}
