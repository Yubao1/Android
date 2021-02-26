package com.example.administrator.myapplication.activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.interfaces.OnServerChangeListener;
import com.example.administrator.myapplication.other.Constants;
import com.example.administrator.myapplication.other.ServerPresenter;

import java.util.ArrayList;
import java.util.List;

public class AndserverActivity extends XActivity implements OnServerChangeListener {
    private ServerPresenter serverPresenter;

    private Button btn_startServer;

    private Button btn_stopServer;

    private TextView tv_message;

    private final int REQUEST_WRITE_EXTERNAL_STORAGE = 10;
    @Override
    public void init() {
        initView();
        serverPresenter = new ServerPresenter(this, this);
        btn_startServer.performClick();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_andserver;
    }

    private void initView() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_startServer: {
                        serverPresenter.startServer(AndserverActivity.this);
                        break;
                    }
                    case R.id.btn_stopServer: {
                        serverPresenter.stopServer(AndserverActivity.this);
                        break;
                    }
                }
            }
        };
        btn_startServer = findViewById(R.id.btn_startServer);
        btn_stopServer = findViewById(R.id.btn_stopServer);
        tv_message = findViewById(R.id.tv_message);
        btn_startServer.setOnClickListener(clickListener);
        btn_stopServer.setOnClickListener(clickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        serverPresenter.unregister(this);
        serverPresenter = null;
    }

    @Override
    public void onServerStarted(String ipAddress) {
        btn_startServer.setVisibility(View.GONE);
        btn_stopServer.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(ipAddress)) {
            String s = "请在这个App的手机浏览器上输入网址";
            List<String> addressList = new ArrayList<>();
            addressList.add("http://" + ipAddress + ":" + Constants.PORT_SERVER + Constants.GET_FILE);
            addressList.add("http://" + ipAddress + ":" + Constants.PORT_SERVER + Constants.GET_IMAGE);
            addressList.add("http://" + ipAddress + ":" + Constants.PORT_SERVER + Constants.POST_JSON);
            tv_message.setText(TextUtils.join("\n", addressList) + "\n" + s);
        } else {
            tv_message.setText("error");
        }
    }

    @Override
    public void onServerStopped() {
        btn_startServer.setVisibility(View.VISIBLE);
        btn_stopServer.setVisibility(View.GONE);
        tv_message.setText("服务器停止了");
    }

    @Override
    public void onServerError(String errorMessage) {
        btn_startServer.setVisibility(View.VISIBLE);
        btn_stopServer.setVisibility(View.GONE);
        tv_message.setText(errorMessage);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"请开放文件写入权限",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
