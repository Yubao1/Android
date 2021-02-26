package com.example.administrator.myapplication.activitys;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpActivity extends XActivity {
    private TextView tv;
    private static MyHandler mHandler = null;
    private OkHttpClient mClient = null;
    private ProgressBar mProgressBar;
    @Override
    public void init() {
        initView();
        initHandler();
        initOkHttpClient();
    }

    private void initView() {
        tv = findViewById(R.id.tv);
        mProgressBar = findViewById(R.id.progressBar);
    }

    private void initOkHttpClient() {
        mClient = new OkHttpClient();
    }

    private void initHandler() {
        mHandler = new MyHandler(this);
    }

    @Override
    public int getId() {
        return R.layout.activity_ok_http;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                startThread("get请求");
                break;
            case R.id.btn_post:
                startThread("post请求");
                break;
            case R.id.btn_okhttp_utils:
                okHttpUtilsGetRequest();
                break;
        }
    }

    private void okHttpUtilsGetRequest() {
        String fileRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        MyFileCallBack callBack = new MyFileCallBack(fileRoot, Constant.FILE_NAME);
        OkHttpUtils
                .get()
                .url(Constant.VIDEO_URL)
                .build()
                .execute(callBack);
    }

    class MyFileCallBack extends FileCallBack {

        public MyFileCallBack(String destFileDir, String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            super.inProgress(progress, total, id);
            mProgressBar.setProgress((int) (100 * progress));
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            String errorMessage = "onError :" + e.getMessage();
            sendMessage(errorMessage);
        }

        @Override
        public void onResponse(File file, int id) {
            String responseMessage = "onResponse :" + file.getAbsolutePath();
            sendMessage(responseMessage);
        }
    }

    static class MyHandler extends Handler {
        private WeakReference<OkHttpActivity> wr;

        public MyHandler(OkHttpActivity activity) {
            wr = new WeakReference<OkHttpActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            wr.get().tv.setText(str);
        }
    }

    class MyThread extends Thread {
        private String option;

        public void setOption(String option) {
            this.option = option;
        }

        @Override
        public void run() {
            super.run();
            if (option.equals("get请求")) {
                getRequest();
            }
            if (option.equals("post请求")) {
                postRequest();
            }
        }
    }

    /**
     * 可以上传数据和下载
     */
    private void postRequest() {
        String message = null;
        RequestBody body = RequestBody.create(Constant.JSON, "");
        Request request = new Request.Builder()
                .url(Constant.URL)
                .post(body)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            message = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(message)) {
            sendMessage(message);
        }
    }

    private void getRequest() {
        Request request = new Request.Builder().url(Constant.URL).build();
        Response response = null;
        String str = null;
        try {
            response = mClient.newCall(request).execute();
            str = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(str)) {
            sendMessage(str);
        }
    }

    private void sendMessage(String str) {
        Message message = Message.obtain();
        message.obj = str;
        mHandler.sendMessage(message);
    }

    private void startThread(String option) {
        MyThread thread = new MyThread();
        thread.setOption(option);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
