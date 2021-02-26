package com.example.administrator.myapplication.tool;

import com.example.administrator.myapplication.other.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by 86188 on 2020/7/31.
 */

public class Generic4Util {
     public void main() {
         Student s = new Student();

         s.method();

         /**
          * 从Class获得信息
          */
         getStudentInfo();

         /**
          * 操作成员方法
          */
         invokeStudentMethod();
     }

    private void invokeStudentMethod() {
        Class cl = null;
        try {
            cl = Class.forName("com.example.administrator.myapplication.other.Student");

            // 指定构造函数
            Constructor constructor = cl.getConstructor(String.class, Integer.TYPE);

            // 根据指定的构造函数来获取对象
            Object object = constructor.newInstance("叶", 22);

            // 指定方法名称来获取对应的私有的Method实例
            Method setName = cl.getDeclaredMethod("setName", String.class);
            setName.setAccessible(true);
            setName.invoke(object, "新的名字");

            // 指定方法名称来获取对应的私有的Method实例
            Method setAge = cl.getDeclaredMethod("setAge", Integer.TYPE);
            setAge.setAccessible(true);
            setAge.invoke(object, 23);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void getStudentInfo() {
        try {
            Class cls = Class.forName("com.example.administrator.myapplication.other.Student");
            Package p = cls.getPackage();
            new MyLogcat().d(getClass(),"包名---" + p.getName());
            int modi = cls.getModifiers();
            new MyLogcat().d(getClass(),"修饰符--" + Modifier.toString(modi));
            Constructor[] cs = cls.getConstructors();
            for (int i = 0; i < cs.length; i++) {
                new MyLogcat().d(getClass(),"构造方法的修饰符--" + Modifier.toString(cs[i].getModifiers()));
                new MyLogcat().d(getClass(),"方法的名字--" + cs[i].getName());
            }
            //取得声明的数据成员
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                new MyLogcat().d(getClass(),"访问修饰符：" + Modifier.toString(field.getModifiers()));
                new MyLogcat().d(getClass(),"   类型："+field.getType().getName());
                new MyLogcat().d(getClass(),"   成员名："+field.getName());
                System.out.println();
            }

            System.out.println();

            //取得成员方法息
            Method[] methods=cls.getDeclaredMethods();
            for(Method method:methods){
                new MyLogcat().d(getClass(),"访问修饰符：" + Modifier.toString(method.getModifiers()));
                new MyLogcat().d(getClass(),"   返回值类型："+method.getReturnType().getName());
                new MyLogcat().d(getClass(),"   方法名："+method.getName());
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
