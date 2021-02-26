package com.example.administrator.myapplication.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.service.StartCommand2Service;
import com.example.administrator.myapplication.service.StartCommandService;

public class StartCommandActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_start_command;
    }
    public void onClick(View view) {
        Intent intent = new Intent(this,StartCommand2Service.class);
        startService(intent);
    }
}
