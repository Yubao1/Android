package com.example.administrator.myapplication.activitys;


import android.view.View;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassDynamicActivity extends XActivity {
    private TextView tv;
    private String str = "str",str2 = "str2";
    @Override
    public void init() {
        tv = findViewById(R.id.tv);
        test();
    }
    private void test()  {
        Class classType = Test.class;
        Object invokertester = null;
        try {
            invokertester = classType.newInstance();
            Method addMethod = classType.getMethod("add", new Class[] { int.class,
                    int.class });

            //Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，
            //如果参数为基本类型数据，必须转换为相应的包装类型的对象。invoke()方法的返回值总是对象，
            //如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，
            //再将其返回
            Object result = addMethod.invoke(invokertester, new Object[] {
                    new Integer(100), new Integer(200) });
            Integer i = (Integer) result;
            str = i + "";

            //在jdk5.0中有了装箱 拆箱机制 new Integer(100)可以用100来代替，系统会自动在int 和integer之间转换
            System.out.println(result);

            Method echoMethod = classType.getMethod("echo",
                    new Class[] { String.class });
            result = echoMethod.invoke(invokertester, new Object[] { "hello" });
            str2 = result.toString();
            System.out.println(result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                tv.setText(str);
                break;
            case R.id.btn_echo:
                tv.setText(str2);
                break;
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_class_dynamic;
    }

}

