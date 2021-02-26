package com.example.administrator.myapplication.kotlinclass.activate

import com.example.administrator.R
import com.example.administrator.myapplication.XActivity

/**
 * 如何打包不同分辨率的界面，请查看app模块下的build.gradle文件
 */
public class ResolutionActivity: XActivity() {
    override fun init() {
    }

    override fun getId(): Int {
        return R.layout.activity_resolution
    }

}