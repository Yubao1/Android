package com.example.administrator.myapplication.tool;

/**
 * Created by 86188 on 2020/7/30.
 */


public class Generic3Util<T> {

    private T t;

    /**
     * 静态成员不能使用在类中声明的类型参数,错误的
     * 在静态环境下不允许类的参数是泛型类型
     　　由于泛型类的所有实例都有相同的运行时类，所以泛型类的静态变量和方法是被它的所有实例所共享的。
     因此，在静态方法、数据域或者初始化语句中，为了类而引用泛型类型参数是非法的。例如，下面的代码是非法的：
     */
//    private static T t;
//    public static void performGeneric2(T t) {
//
//    }


    /**
     * 不能使用泛型类型参数创建实例
     */
//      T t = new T();


    /**
     * 不能使用泛型类型参数创建数组
     */
//    T[] ts = new T[];


    /**
     * 泛型类不能扩展java.lang.Throwable
     */
//    public class MyException<T> extends Exception {
//　　}
//　　JVM必须检查这个从try子句中抛出的异常以确定它是否与catch子句中指定的类型匹配。但这是不可能的，因为在运行时类型信息是不出现的。
//            　　try {
//　　...
//　　}catch (MyException<T> ex) {
//　　...
    /**
     * 这情况也不允许，编译也会报错
     */
//　　}catch (T ex) {
//　　...
//　　}


    /**
     * 不能用基本类型实例化类型参数
     */
//    Generic3Util<int> util = new Generic3Util<int>();


    /**
     * 运行时类型查询只适用于原始类型
     */
//    if(a instanceof Pair<String>) {
//
//    }

    /**
     *
     * 可以声明静态的泛型方法
     */
    public static <K> void performGeneric(K k) {

    }

    public void method() {
        if (t instanceof GenericUtil) {

        }

        /**
         * 不能做运行时类检测
         */
//        if (t instanceof Generic3Util<String>) {
//
//        }
    }

//

}
