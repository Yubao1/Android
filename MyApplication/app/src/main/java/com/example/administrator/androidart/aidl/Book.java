package com.example.administrator.androidart.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2020/5/24.
 */

/**
 * 注意这个文件的包名 和aidl文件的包名要一样
 */
public class Book implements Parcelable {
    private int mId;
    private String mName;
    public Book() {

    }
    public Book(int mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    protected Book(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
