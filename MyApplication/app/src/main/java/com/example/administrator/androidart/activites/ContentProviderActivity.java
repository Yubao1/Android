package com.example.administrator.androidart.activites;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.androidart.aidl.Book;
import com.example.administrator.androidart.bean.User;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Constant;

public class ContentProviderActivity extends XActivity {
    private String TAG = "ContentProviderActivity";
    private ContentObserver mContentObserver;
    @Override
    public void init() {
        mContentObserver = new MyContentObserver(new Handler());
//        resolver.query(uri,null,null,null,null);
//        resolver.query(uri,null,null,null,null);
//        resolver.query(uri,null,null,null,null);
        Uri bookUri = Uri.parse(Constant.URI + "/book");
        ContentValues cv = new ContentValues();
        cv.put("_id",6);
        cv.put("name","程序设计");
        getContentResolver().insert(bookUri,cv);
        Cursor bookCursor = getContentResolver().query(bookUri,new String[]{"_id","name"},null,null,null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.setId(bookCursor.getInt(0));
            book.setName(bookCursor.getString(1));
            Log.d(TAG,"query book:" + book.toString());
        }
        bookCursor.close();
        Uri userUri = Uri.parse(Constant.URI + "/user");
        Cursor userCursor = getContentResolver().query(userUri,new String[]{"_id","name","sex"},null,null,null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.setId(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setIsShow(userCursor.getInt(2) == 1);
            Log.d(TAG,"user query " + user);
        }
        userCursor.close();
        getContentResolver().registerContentObserver(bookUri,true,mContentObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mContentObserver);
    }

    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Uri bookUri = Uri.parse(Constant.URI + "/book");
            Cursor bookCursor = getContentResolver().query(bookUri,new String[]{"_id","name"},null,null,null);
            while (bookCursor.moveToNext()) {
                Book book = new Book();
                book.setId(bookCursor.getInt(0));
                book.setName(bookCursor.getString(1));
                Log.d(TAG,"query book-----------onChange---" + book.toString());
            }
            bookCursor.close();
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_content_provider;
    }
    public void onClick(View view) {
        Uri bookUri = Uri.parse(Constant.URI + "/book");
        ContentValues cv = new ContentValues();
        cv.put("_id",7);
        cv.put("name","Android设计大全-----");
        getContentResolver().insert(bookUri,cv);
        Toast.makeText(this,"插入数据成功",Toast.LENGTH_SHORT).show();
    }
}
