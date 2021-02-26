package com.example.administrator.androidart.activites;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.activitys.OtherKnowledgeActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1、串行执行AsyncTask时，排列的顺序号不会是交差的
 * 2、并行执行AsyncTask时，排列的顺序号会有交差的
 * 3、ThreadLocal 在线程中只保留一个值，后面存储的值会覆盖前面的值
 */
public class ThreadAndThreadPooActivity extends XActivity {
    @Override
    public void init() {
    }

    @Override
    public int getId() {
        return R.layout.activity_thread_and_thread_poo;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                performSerial();
                break;
            case R.id.btn_2:
                perform2();
                break;
            case R.id.btn_3:
                Runnable runnable = new MyRunnable();
                ExecutorService executorService = Executors.newFixedThreadPool(4);
//                ExecutorService executorService2 = Executors.newCachedThreadPool();
//                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
//                scheduledExecutorService.schedule(runnable,2000, TimeUnit.MILLISECONDS);
//                ExecutorService executorService3 = Executors.newSingleThreadExecutor();

//                scheduledExecutorService.scheduleAtFixedRate(runnable,2000,1000,TimeUnit.MILLISECONDS);
                new MyLogcat().d(getClass(),"----------MyRunnable---startTime = " + SystemClock.currentThreadTimeMillis());
//                for (int i = 0; i < 9; i++) {
//                    Runnable runnable2 = new MyRunnable();
                    executorService.execute(runnable);
////                    scheduledExecutorService.schedule(runnable2,2000, TimeUnit.MILLISECONDS);
////                    scheduledExecutorService.scheduleAtFixedRate(runnable2,2000,1000,TimeUnit.MILLISECONDS);
//                }

                ExecutorService executorService2 = Executors.newCachedThreadPool();
                executorService2.execute(runnable);

                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
                scheduledExecutorService.schedule(runnable,2000, TimeUnit.MILLISECONDS);

                scheduledExecutorService.scheduleAtFixedRate(runnable,10,1000,TimeUnit.MICROSECONDS);

                ExecutorService executorService3 = Executors.newSingleThreadExecutor();
                executorService3.execute(runnable);
                break;
            case R.id.btn_4:
                testThreadLocal();
                break;
            case R.id.btn_5:
                MyHandlerThread mT = new MyHandlerThread("MyHandlerThread");
                mT.start();
                mH = new MyH(mT.getLooper());
                mH.sendEmptyMessageDelayed(0,400);
                break;
        }
    }
    Handler mH;
    class MyHandlerThread extends HandlerThread {
        public MyHandlerThread(String name) {
            super(name);
        }
    }
    class MyH extends Handler {
        public MyH(Looper l) {
            super(l);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            new MyLogcat().d(getClass(),"ThreadName = " + Thread.currentThread().getName());
        }
    }

    private void testThreadLocal() {
        final ThreadLocal<Boolean> threadLocal = new ThreadLocal<Boolean>();
        threadLocal.set(true);
        final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<Integer>();
        threadLocal2.set(1);
        threadLocal2.set(2);
        threadLocal2.set(3);
        new MyLogcat().d(ThreadAndThreadPooActivity.this.getClass(),"---" + Thread.currentThread().getName() + "----" + threadLocal2.get());
        new MyLogcat().d(ThreadAndThreadPooActivity.this.getClass(),"---" + Thread.currentThread().getName() + "----" + threadLocal.get());
        new Thread("thread#1"){
            @Override
            public void run() {
                super.run();
                threadLocal.set(false);
                new MyLogcat().d(ThreadAndThreadPooActivity.this.getClass(),"---" + Thread.currentThread().getName() + "----" + threadLocal.get());
            }
        }.start();
        new Thread("thread#2"){
            @Override
            public void run() {
                super.run();
                new MyLogcat().d(ThreadAndThreadPooActivity.this.getClass(),"---" + Thread.currentThread().getName() + "----" + threadLocal.get());
            }
        }.start();
    }

    class MyRunnable implements Runnable {
        int number = 1;
        @Override
        public void run() {
//            SystemClock.sleep(2000);
            new MyLogcat().d(getClass(),"--------MyRunnable--------" + number + "----------ThreadName = " + Thread.currentThread().getName());
            number++;
        }
    }
    private void perform2() {
        for(int i = 1;i < 16;i++){
            MyAsyncTask task = new MyAsyncTask("MyAsyncTask------"+i);
            //task.execute(new String[0]);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        }
    }
    private void performSerial() {
         for (int i = 0; i < 15;i++) {
             MyAsyncTask myAsyncTask = new MyAsyncTask("MyAsyncTask[" + (i+1) + "]");
             myAsyncTask.execute(new String[0]);
        }
    }

    class MyAsyncTask extends AsyncTask<String,Void,String>{
        private String name;
        public MyAsyncTask(String name) {
            this.name = name;
        }

        @Override
        protected String doInBackground(String... strings) {
            new MyLogcat().d(getClass(),"--------------doInBackground" + name + " is run "+System.currentTimeMillis()+" thread id "+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
