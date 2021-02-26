package com.example.administrator.myapplication.kotlinclass.activate

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v7.app.AppCompatActivity
import com.example.administrator.R



import com.example.administrator.myapplication.other.User


/**
 * Created by 86188 on 2021/1/4.
 * 参考：https://blog.csdn.net/chun_long/article/details/52086565
 */
class MvvpActivity : AppCompatActivity() {
    var user:User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val activityMainBinding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvp)
//        user = User("小四")
//        var activityMainBinding2: ActivityMvvpBinding = activityMainBinding as ActivityMvvpBinding
//        activityMainBinding2.setUser(user)
//        Handler().postDelayed(Runnable {
//            user!!.setName("小二")
////            user!!.setPassword("654551")
//        }, 2000)
    }

}