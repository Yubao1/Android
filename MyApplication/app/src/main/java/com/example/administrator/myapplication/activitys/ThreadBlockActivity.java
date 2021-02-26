package com.example.administrator.myapplication.activitys;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.Consumer;
import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.other.Producer;
import com.example.administrator.myapplication.tool.BindEventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
@BindEventBus
public class ThreadBlockActivity extends XActivity {
    EditText mEt;
    Button mBtn;
    TextView mTv;
    static MyHandler mHandler;
    Producer producer;
    @Override
    public void init() {
        initHandler();
        initView();
        startThread();
    }

    @Override
    public void onEventBusCall(EventBusCall event) {
        super.onEventBusCall(event);
        if (event.getId() == Constant.THREAD_BLOCK_ACTIVITY_RECEIVE_ID) {
            String s = (String)event.getMessage()[0];
            mTv.setText(s);
        }
    }

    private void startThread() {
        //
        // 当队列是空的话，消费者就等待，生产者生产出商品后，等待的恢复，将商品消费掉
        // 当队列是满的情况下，生产者就不能往队列中放了，只有消费者消费掉部分商品后，队列有空位了，生产者才可以继续生产
        // 来看个小例子，生产者生成酒，酒鬼消费掉酒

        // 队列只能存放10瓶酒，
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);


        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 12; i++) {
            producer = new Producer(queue, "黄金酒" + i);
            // 12个生产线程，因为队列大小只有10，所以会有两个线程会阻塞
            service.submit(producer);
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //消费了一个，看看，生成线程会不会恢复生产
        Consumer consumer = new Consumer(queue, "酒鬼");
        service.submit(consumer);
        service.shutdown();
    }
    public void onClick(View view) {
        String s = mEt.getText().toString().trim();
        if (producer != null) {
            producer.putData(s);
        }
    }
    private void initHandler() {
        mHandler = new MyHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
    private void initView() {
        mEt = findViewById(R.id.et);
        mBtn = findViewById(R.id.btn);
        mTv = findViewById(R.id.tv);
    }

    @Override
    public int getId() {
        return R.layout.activity_thread_block;
    }
}
