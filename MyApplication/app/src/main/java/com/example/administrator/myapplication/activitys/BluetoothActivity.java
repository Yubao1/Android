package com.example.administrator.myapplication.activitys;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class BluetoothActivity extends XActivity {
    BluetoothAdapter mBluetoothAdapter = null;
    private TextView tv;
    @Override
    public void init() {
        tv = findViewById(R.id.tv);
    }

    @Override
    public int getId() {
        return R.layout.activity_bluetooth;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_bluetooth:
                openBluetooth();
                break;
            case R.id.btn_close_bluetooth:
                closeBluetooth();
                break;
        }
    }

    private void closeBluetooth() {
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.disable();
        }
    }

    private void openBluetooth() {
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
//        mBluetoothAdapter.enable();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
           tv.setText("连接蓝牙成功");
        }
        if (resultCode == RESULT_CANCELED) {
            tv.setText("连接蓝牙失败");
        }
    }
}
