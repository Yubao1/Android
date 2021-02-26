package com.example.administrator.myapplication.activitys;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import java.util.List;

public class LocationActivity extends XActivity {
    private TextView mTv,mTv2;
    private LocationManager locationManager;
    private String locationProvider;
    private double jingD = 0, weiD = 0;
    private LocationListener locationListener;
    private Handler mHandler;
    @Override
    public void init() {
        initView();
        initHandler();
        initLocationListener();
        initManager();
    }

    private void initHandler() {
        mHandler = new MyHandler();
    }

    private void initLocationListener() {
        locationListener = new MyLocationListener();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTv.setText("经度为：" + jingD);
            mTv2.setText("纬度为：" + weiD);
        }
    }

    /**
     * 显示地理位置经度和纬度信息
     *
     * @param location
     */
    private void showLocation(Location location) {
        jingD = location.getLongitude();
        weiD = location.getLatitude();
        mHandler.sendEmptyMessage(0);
    }

    private void initView() {
        mTv = findViewById(R.id.tv_1);
        mTv2 = findViewById(R.id.tv_2);
    }

    private void initManager() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            // 如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            // 如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // 获取Location
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            // 不为空,显示地理位置经纬度
            showLocation(location);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // 监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1,
                locationListener);

    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // 如果位置发生变化,重新显示
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            // 移除监听器
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_location;
    }
}
