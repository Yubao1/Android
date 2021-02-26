package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.service.StartCommandService;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 1、每次回调onStartCommand()方法时，
 * 参数“startId”的值都是递增的，
 * startId用于唯一标识每次对Service发起的处理请求 如果服务同时处理多个 onStartCommand() 请求
 * ，则不应在处理完一个启动请求之后立即销毁服务，
 * 因为此时可能已经收到了新的启动请求，
 * 在第一个请求结束时停止服务会导致第二个请求被终止。
 * 为了避免这一问题，可以使用 stopSelf(int) 确保服务停止请求始终基于最新一次的启动请求。
 * 也就是说，如果调用 stopSelf(int) 方法的参数值与onStartCommand()接受到的最新的startId值不相符的话，
 * stopSelf()方法就会失效，从而避免终止尚未处理的请求
 *
 *
 * 2、Service的onStartCommand的方法是逐一处理所有启动请求的，是串行执行的，不是并行执行的
 *
 * 3、service的onStartCommand中的参数intent和Context的startService中的intent是同一个对象
 */
public class MoreStartCommandActivity extends XActivity {
    private int num = 1;
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_more_start_command;
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, StartCommandService.class);
        intent.putExtra("key","开启第" + num + "个服务-------------");
        new MyLogcat().d(getClass(),"------------ intent = " + intent);
        startService(intent);
        num++;
    }
}
