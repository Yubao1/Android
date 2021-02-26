package com.example.administrator.myapplication.other;

/**
 * Created by 86188 on 2020/8/10.
 */

import android.text.TextUtils;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<String> queue;
    String uuid = null;
    private String produce;
    public Producer(BlockingQueue<String> queue, String produce) {
        this.queue = queue;
        if (null != produce)
            this.produce = produce;
        else this.produce = "null ";
    }
    public void putData(String data) {
        uuid = UUID.randomUUID().toString() + ":" + data;
    }
    @Override
    public void run() {
        try {
            uuid = UUID.randomUUID().toString();
            Thread.sleep(200);//生产需要时间
            if (!TextUtils.isEmpty(uuid)) {
                queue.put(produce + " : " + uuid);  //当队列里满的话，会阻塞
            }
            System.out.println("生产者 \t" + produce + " : " + uuid + " " + Thread.currentThread());
            uuid = null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
