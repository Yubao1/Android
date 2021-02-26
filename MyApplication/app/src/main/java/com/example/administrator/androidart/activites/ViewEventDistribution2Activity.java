package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 当xml布局中所有的View的onTouchEvent()方法返回的是false时，表示只消耗
 * DOWN序列事件，其他事件不消耗，并把其他序列事件返回给Activity的onTouchEvent()方法处理
 *
 * 1、一旦子元素的down序列事件或其他序列事件请求不拦截，子元素的序列将接收到序列事件
 * 2、可以单独拦截某一事件的序列（除了down序列事件），不拦截的序列事件将会在
 * 子元素中对应的序列事件被调用，子元素都可以在onTouchEvent()方法中请求不拦截
 */
public class ViewEventDistribution2Activity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new MyLogcat().d(ViewEventDistribution2Activity.class,"---------------------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                new MyLogcat().d(ViewEventDistribution2Activity.class,"---------------------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                new MyLogcat().d(ViewEventDistribution2Activity.class,"---------------------ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
