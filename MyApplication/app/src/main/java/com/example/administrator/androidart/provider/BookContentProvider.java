package com.example.administrator.androidart.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.androidart.other.DbOpenHelper;
import com.example.administrator.myapplication.other.Constant;

import java.net.PortUnreachableException;

/**
 * Created by 86188 on 2020/6/10.
 */

public class BookContentProvider extends ContentProvider {
    private static final String TAG = "BookContentProvider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse(Constant.URI + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse(Constant.URI + "/book");
    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private SQLiteDatabase mDb = null;
    private Context mContext = null;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI("com.zyb.provider","book",BOOK_URI_CODE);
        sUriMatcher.addURI("com.zyb.provider","user",USER_URI_CODE);
    }
    @Override
    public boolean onCreate() {
        Log.d(TAG,"----------currentThread------" + Thread.currentThread().getName());
        mContext = getContext();
        initProviderData();
        return false;
    }
    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        mDb.execSQL("insert into book values(3,'Android');");
        mDb.execSQL("insert into book values(1,'Ios');");
        mDb.execSQL("insert into book values(2,'html');");
        mDb.execSQL("insert into user values(5,'jake',1);");
        mDb.execSQL("insert into user values(4,'jasmine',0);");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG,"query currentThread-----" + Thread.currentThread().getName());
        String table = getTableName(uri);
        return mDb.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    public String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME ;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
        }
        return tableName;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG,"getType-----");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG,"insert-----");
        String table = getTableName(uri);
        mDb.insert(table,null,values);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG,"delete-----");
        String table = getTableName(uri);
        int count = mDb.delete(table,selection,selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG,"update-----");
        String table = getTableName(uri);
        int row = mDb.update(table,values,selection,selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return row;
    }
}
