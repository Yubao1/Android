package com.example.administrator.androidart.bean;

import java.io.Serializable;

/**
 * Created by 86188 on 2020/5/22.
 */

public class User implements Serializable {
    public static final long serialVersionUID = 1L;
    private int mId;
    private String mName;
    private boolean mIsShow;
    public User() {

    }
    public User(int mId, String mName, boolean mIsShow) {
        this.mId = mId;
        this.mName = mName;
        this.mIsShow = mIsShow;
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

    public boolean isIsShow() {
        return mIsShow;
    }

    public void setIsShow(boolean mIsShow) {
        this.mIsShow = mIsShow;
    }

    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mIsShow=" + mIsShow +
                '}';
    }
}
