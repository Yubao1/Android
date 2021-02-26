package com.example.administrator.myapplication.activitys;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.receiver.WifiReceiver;
import com.example.administrator.myapplication.tool.BindEventBus;

@BindEventBus
public class WifiActivity extends XActivity {
    private WifiReceiver mWifiReceiver;
    private int i = 0;
    private TextView tv;
    @Override
    public void init() {
        tv = findViewById(R.id.tv);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
       mWifiReceiver = new WifiReceiver();
        registerReceiver(mWifiReceiver, filter);
    }

    @Override
    public void onEventBusCall(EventBusCall event) {
        super.onEventBusCall(event);
        int id = event.getId();
        if (id == Constant.WIFI_ACTIVITY_ID) {
            String s = (String)event.getMessage()[0];
            setTextValue(s);
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_wifi;
    }
    private void cancelReceiver() {
        if (mWifiReceiver != null) {
            unregisterReceiver(mWifiReceiver);
            i++;
            setTextValue("取消监听" + i + "次");
        }
    }
    private void setTextValue(String s) {
         tv.setText(s);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelReceiver();
    }

    public void onClick(View view) {
         cancelReceiver();
    }
}
