package com.example.administrator.androidart.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by 86188 on 2020/6/17.
 */

public class TCPServerService extends Service {
    private static final String TAG = "TCPServerService";
    private boolean mIsServiceDestoryed = false;
    private String[] mDefinedMessages = new String[]{"你好！","请问你叫什么名字","今天天气不错","给你讲个笑话吧","这个可以多人聊天"};
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TcpServer()).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestoryed = true;
    }

    class TcpServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8088);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!mIsServiceDestoryed) {
                try {
                    final Socket client = serverSocket.accept();
//                    new MyThread(client).start();
                    recevi(client);
                    Log.d(TAG,"client connect-----");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private void recevi(Socket client) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
            out.println("欢迎来到聊天室");
            while (true) {
//                String str = in.readLine();
//                Log.d(TAG,"msg from client:" + str);
                Thread.sleep(500);
                int i = new Random().nextInt(mDefinedMessages.length);
                String message = mDefinedMessages[i];
                out.println(message);
                Log.d(TAG,"send：_____________________________________________________________________________");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class MyThread extends Thread {
        private Socket client;
        public MyThread(Socket client) {
            this.client = client;
        }
        @Override
        public void run() {
            super.run();
        }
    }
}
