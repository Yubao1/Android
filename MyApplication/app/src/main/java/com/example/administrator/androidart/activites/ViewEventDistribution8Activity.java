package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

/**
 * 1、一旦父元素拦截了down序列事件，子元素收不到任何序列事件。
 * 2、一旦父元素拦截了A序列事件，子元素不请求重置拦截标志位的话，子元素位于A序列事件处理的序列事件都会被拦截。
 * 3、假设有A(down)、B、C 3个序列事件，它们的序列事件执行顺序是A(down) B C，父元素拦截了C序列事件，子元素的onTouchEvent方法第一次返回true时
 * 没有被拦截的序列事件或者已经请求不拦截的序列事件都会被消耗，如果第一次返回的是false，即只消耗A(down)事件。
 *
 * 4、如果子元素onTouchEvent方法第一次返回false，父元素调用onTouchEvent方法时，如果down序列事件返回true，则消耗所有序列事件；
 * 如果down序列事件返回false，则其他事件不消耗。
 *
 * 5、假设有A(down)、B、C、3个序列事件，它们的执行顺序是 A、B、C ，如果父元素拦截了B序列事件，C也会默认被拦截。
 */
public class ViewEventDistribution8Activity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution8;
    }
}
