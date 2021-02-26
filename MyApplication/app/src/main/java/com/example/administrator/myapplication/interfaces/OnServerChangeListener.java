package com.example.administrator.myapplication.interfaces;

/**
 * Created by Administrator on 2020/8/16.
 */

public interface OnServerChangeListener {
    void onServerStarted(String ipAddress);

    void onServerStopped();

    void onServerError(String errorMessage);
}
