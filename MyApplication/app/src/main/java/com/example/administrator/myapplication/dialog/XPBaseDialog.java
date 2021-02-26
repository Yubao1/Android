package com.example.administrator.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2020/6/25.
 */

public abstract class XPBaseDialog extends XPBaseUtil implements View.OnClickListener {

    protected Dialog dialog;
    protected View root;

    public Dialog getDialog() {
        return dialog;
    }

    public XPBaseDialog(Context context) {
        super(context);

        init();
    }

    public void init() {

        if (dialog == null) {
            initDialog();
        }

        initDialogView();
    }

    /**
     * 初始化Dialog
     */
    public abstract void initDialog();

    /**
     * 初始化Dialog的View
     */
    public abstract void initDialogView();

    /**
     * 显示对话框
     */
    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 外部不可关闭
     */
    public void setOutNoCanClose() {
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }
}

