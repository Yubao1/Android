package com.example.administrator.myapplication.activitys;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;

import com.example.administrator.databinding.ActivityMvvpBinding;

import com.example.administrator.myapplication.other.User;


/**
 * ActivityMvvpBinding类要与 XML布局文件命名要对应好
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvpBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvp);
        final User user = new User("小四");
        activityMainBinding.setUser(user);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setName("小二");

            }
        },3000);
    }
}
