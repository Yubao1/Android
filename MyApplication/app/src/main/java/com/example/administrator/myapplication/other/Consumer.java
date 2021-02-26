package com.example.administrator.myapplication.other;

/**
 * Created by 86188 on 2020/8/10.
 */

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<String> queue;
    private String consumer;

    public Consumer(BlockingQueue<String> queue, String consumer) {
        this.queue = queue;
        if (null != consumer)
            this.consumer = consumer;
        else
            this.consumer = "null ";
    }

    @Override
    public void run() {
        try {
            String uuid = queue.take();     //当队列里是空的话，会阻塞
            System.out.println(consumer + "\t 喝掉了 " + uuid  + " " + Thread.currentThread());
            sendEvent(uuid);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    private void sendEvent(String s) {
        EventBusCall eventBusCall = new EventBusCall(Constant.THREAD_BLOCK_ACTIVITY_RECEIVE_ID,s);
        EventBus.getDefault().post(eventBusCall);
    }
}
