package com.example.administrator.myapplication.activitys;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.jni.JNIC;
import com.example.administrator.myapplication.XActivity;

/**
 * JNI开发步骤
 * 1、编写一个JNIC类，并用静态代码块加载一个库System.loadLibrary(库名);
 *
 * 2、将JNIC类复制到javac程序所在的目录，然后打开cmd终端进入javac程序所在的目录
 * ，再执行javac JNIC.java，最后得到一个JNIC.class文件并将它复制放在JNIC类所在的目录
 *
 * 3、将com.example.administrator文件夹复制到javah所在的目录，然后打开终端cmd进入
 * javah所在的目录，最后执行javah com.example.administrator.JNIC，得到com_example_administrator_jni_JNIC.h
 * 文件并将其复制到E:\androidStudioProject\Android\MyApplication\app\build\intermediates\classes\debug
 * 目录下
 *
 * 4、编写C文件的native方法，然后打开cmd终端进入jni目录执行
 * gcc HelloWorld.c -fPIC -shared -o libjni-HelloWorld.so命令
 *
 * 5、得到libjni-HelloWorld.so文件，将其放入jniLibs子目录下的armeabi目录和
 * libs子目录下的armeabi目录
 *
 * 6、将左侧的项目Project视图切换到Android视图，点击项目右键鼠标，选择“Linked C++ Project”
 * 然后再选择“ndk-build”找到Android.mk文件
 *
 * 7、运行项目
 */
public class JNIActivity extends XActivity {
    TextView mTv;
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);
    }


    public void onClick(View view) {
        String result = new JNIC().callC();
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        mTv.setText(result);
    }
    @Override
    public int getId() {
        return R.layout.activity_jni;
    }
}
