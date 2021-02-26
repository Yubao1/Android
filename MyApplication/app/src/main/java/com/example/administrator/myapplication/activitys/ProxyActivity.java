package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.interfaces.Interface2;
import com.example.administrator.myapplication.tool.MyLogcat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyActivity extends XActivity {
    @Override
    public void init() {
        Interface2 interface2 = create(Interface2.class);
        interface2.method();
    }

    @Override
    public int getId() {
        return R.layout.activity_proxy;
    }
    public <T> T create(final Class<T> service) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class<?>[] { service }, new InvocationHandler() {

                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        // If the method is a method from Object then defer to
                        // normal invocation.
                        System.out.println("调用method()方法");
                        new MyLogcat().d(getClass(),"调用method()方法");
                        return proxy;
                    }
                });
    }
}
