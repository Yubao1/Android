package com.example.administrator.myapplication.other;

import com.example.administrator.myapplication.tool.MyLogcat;

/**
 * Created by 86188 on 2020/7/31.
 * 1、先执行静态代码块，再执行构造方法
 */

public class Student {
    public Student() {
        new MyLogcat().d(Student.class,"执行Student无参数的构造方法");
    }
    static {
        new MyLogcat().d(Student.class,"载入了 Student.class 文档");
    }

    public void method() {
        Class cl= null;
        try {
            cl = Class.forName("com.example.administrator.myapplication.other.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new MyLogcat().d(Student.class,"类名称:"+cl.getName());
        new MyLogcat().d(Student.class,"简单类名称:"+cl.getSimpleName());
        new MyLogcat().d(Student.class,"包名:"+cl.getPackage());
        new MyLogcat().d(Student.class,"是否为接口:"+cl.isInterface());
        new MyLogcat().d(Student.class,"是否为基本类型:"+cl.isPrimitive());
        new MyLogcat().d(Student.class,"是否为数组对象:"+cl.isArray());
        new MyLogcat().d(Student.class,"父类名称:"+cl.getSuperclass().getName());
    }

    enum Gender{
        male,female
    }

    private String name;

    public int age;

    protected Gender gender;

    public Student(String name,int age){

    }

    public Student(String name,int age,Gender gender){

    }


    public String getName() {
        return name;
    }

    private int getAge(){
        return age;
    }



    private void setName(String name) {
        this.name = name;
        new MyLogcat().d(Student.class,"调用了setName方法,name:" + name);
    }

    private void setAge(int age) {
        this.age = age;
        new MyLogcat().d(Student.class,"调用了setAge方法，age:" + age);
    }
}
