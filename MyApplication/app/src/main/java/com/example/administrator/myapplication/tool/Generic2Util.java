package com.example.administrator.myapplication.tool;

import com.example.administrator.myapplication.other.Test3;

/**
 * Created by 86188 on 2020/7/30.
 */

public class Generic2Util<T extends Test3> {
    private T t;
    public Generic2Util(T t) {
        this.t = t;
    }
    @Override
    public String toString() {
        return t.toString();
    }
}
