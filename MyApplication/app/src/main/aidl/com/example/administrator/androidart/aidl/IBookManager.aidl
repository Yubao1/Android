// IBookManager.aidl
package com.example.administrator.androidart.aidl;
import com.example.administrator.androidart.aidl.Book;
import java.util.List;
import com.example.administrator.androidart.aidl.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            List<Book> getBookList();
            void addBook(in Book book);
            void registerListener(in IOnNewBookArrivedListener listener);
            void unregisterListener(in IOnNewBookArrivedListener listener);
}
