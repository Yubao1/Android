// IBinderPool.aidl
package com.example.administrator.androidart.aidl;
import android.os.IBinder;

// Declare any non-default types here with import statements

interface IBinderPool {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            IBinder queryBinder(in int binderCode);
}
