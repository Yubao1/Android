package com.example.administrator.androidart.activites;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.service.TCPServerService;
import com.example.administrator.myapplication.XActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SocketActivity extends XActivity {
    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private TextView mTv;
    private EditText mEt;
    private Button mBtn;
    private PrintWriter mPrintWriter = null;
    private Socket mClientSocket = null;
    private MyHandler mHanlder = null;
    private static final String TAG = "SocketActivity";
    private String[] mDefinedMessages = new String[]{"你好！","请问你叫什么名字","今天天气不错","给你讲个笑话吧","这个可以多人聊天"};
    @Override
    public void init() {
        initView();
        initHanlder();
        startService();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startThread();
            }
        },3000);
    }

    private void startService() {
        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);
    }
    private void startThread() {
        new MyThread().start();
    }
    private void initHanlder() {
        mHanlder = new MyHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Socket socket = null;
            while (socket == null) {
                try {
                    socket = new Socket("127.0.0.1",8088);
                    mClientSocket = socket;
                    mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                    mHanlder.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                    Log.d(TAG,"connect server success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            BufferedReader br = null;
            try {
                 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String msg = br.readLine();
                    Thread.sleep(500);
                    int i = new Random().nextInt(mDefinedMessages.length);
//                    String msg = mDefinedMessages[i];
                    Log.d(TAG,"receive：" + msg);
                    if (msg != null) {
                        String time = formatDateTime(System.currentTimeMillis());
                        String showedMsg = "server " + time + ":" + msg + "\n";
//                        mHanlder.obtainMessage(MESSAGE_RECEIVE_NEW_MSG,showedMsg).sendToTarget();
                    }
                    mPrintWriter.print(msg);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mTv.setText(mTv.getText() + (String)msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    mBtn.setEnabled(true);
                    break;
            }
        }
    }
    private void initView() {
        mTv = findViewById(R.id.tv);
        mEt = findViewById(R.id.et);
        mBtn = findViewById(R.id.btn);
    }

    public void onClick(View view) {
        String msg = mEt.getText().toString().trim();
        if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
            mPrintWriter.print(msg);
            mEt.setText("");
            String time = formatDateTime(System.currentTimeMillis());
            String showMsg = "self " + time + ":" + msg + "\n";
            mTv.setText(mTv.getText() + showMsg);
        }
    }
    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }
    @Override
    public int getId() {
        return R.layout.activity_socket;
    }
}
