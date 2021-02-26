package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.VolatileActivity;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Test;
import com.example.administrator.myapplication.other.Test2;
import com.example.administrator.myapplication.other.Test3;
import com.example.administrator.myapplication.tool.Generic2Util;
import com.example.administrator.myapplication.tool.Generic3Util;
import com.example.administrator.myapplication.tool.Generic4Util;
import com.example.administrator.myapplication.tool.GenericUtil;

public class JavaBaseActivity extends XActivity {
    private TextView mTv;
    String s = "";
    @Override
    public void init() {
        mTv = findViewById(R.id.tv);

        /**
         * 测试注解
         */
        performGeneric();

        /**
         * 测试泛型
         */
        performGeneric2();

        /**
         * 测试反射机制
         */
        performGeneric3();

        /**
         *当直接实例化接口的实现类时，不管外部类的内部接口 是不是静态的，可以直接 new 外部类.接口；而其他
         * 外部类的属性就不可以这么做
         */
        new Test.IInterface() {
            @Override
            public void add2() {

            }
        };

    }
    private void performGeneric3() {
        Generic4Util util = new Generic4Util();
        util.main();
    }
    private void performGeneric2() {
        Test2 test2 = new Test2();
        Test3 test3 = new Test3();

        /**
         * 如果要为通配符建立上边界，可以使用如下所示的通配符表达式,T可以是父类
         */
        Generic2Util<Test3> util = new Generic2Util<Test3>(test3);
        s = s + "泛型测试-----------------------------------" + "\n";
        s = s + util.toString() + "\n";

        /**
         * 泛型 6条限制总结
         */
        Generic3Util<Test2> util12 = new Generic3Util<Test2>();
        mTv.setText(s);
    }
    class MyClassA extends Test{

    }
    private void performGeneric() {

        /**
         * SOURCE 表示注解只在源文件中保留，在编译期间会被抛弃
           CLASSS 表示注解在编译时会被存储到 .class 文件中，但在运行时通过 JVM 不能得到注解
           RUNTIME 表示注解在编译时会被存储到 .class 文件中，并且在运行时可以通过 JVM 获取到注解，提供了最久远的注解
         */

        /**
         * 获取单个注解对象的内容
         */
        GenericUtil util = new GenericUtil();
        s = util.performGeneric() + "\n";
//        if (!TextUtils.isEmpty(s)) {
//            mTv.setText(s + "\n");
//        }

        /**
         * 获取多个注解对象
         */
        s = s + util.performAnnotations() +"\n";
//        if (!TextUtils.isEmpty(s)) {
//            mTv.setText(s + "\n");
//        }
        /**
         * 可以通过 isAnnotationPresent 判断指定注解是否与调用对象相关联，即判断是否有使用该注解声明调用对象
         */
        s = s + util.performField() + "\n";

        /**
         * 获取注解的默认值
         */
        s = s + util.performVocationDefault() + "\n";
        if (!TextUtils.isEmpty(s)) {
            mTv.setText(s + "\n");
        }

    }

    @Override
    public int getId() {
        return R.layout.activity_java_base;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_2:
                startActivity(ThreadBlockActivity.class);
                break;
            case R.id.tv_3:
                startActivity(VolatileActivity.class);
                break;
            case R.id.tv_4:
                
                break;
            case R.id.tv_5:
                startActivity(ThreadLocalActivity.class);
                break;
        }
    }
}
