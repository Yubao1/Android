package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.androidart.views.MyButton4;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * 1、同时设置了 onTouch、onTouchEvent、onClick方法时，不管onTouch、onTouchEvent的方法返回值false还是true都不会执行onClick方法
 * 除非onTouchEvent方法返回的是父类的onTouchEvent方法
 *
 * 2、同时设置了onTouch、onTouchEvent方法时，如果onTouch返回true，则onTouchEvent不会执行，否则onTouchEvent会执行
 */
public class ViewEventDistribution4Activity extends XActivity {
    MyButton4 btn;
    @Override
    public void init() {
        btn = findViewById(R.id.btn);
        btn.setOnTouchListener(new A());
    }
    class A implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            new MyLogcat().d(ViewEventDistribution4Activity.class,"-------------------onTouch事件");
            return false;
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_view_event_distribution4;
    }

    public void onClick(View view) {
        new MyLogcat().d(ViewEventDistribution4Activity.class,"-------------------onClick事件");
    }
}
