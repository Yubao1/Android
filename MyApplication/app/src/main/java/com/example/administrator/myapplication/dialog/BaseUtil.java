package com.example.administrator.myapplication.dialog;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2020/6/25.
 */

public class BaseUtil {
    private Context context;

    public BaseUtil(Context context) {
        this.context = context;
    }

    protected void showToast(String strMsg) {
    }

    protected void finish() {
        ((Activity) context).finish();
    }

    public void showDataErrorToast() {
        showToast("数据异常");
    }

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return (Activity) context;
    }
}
