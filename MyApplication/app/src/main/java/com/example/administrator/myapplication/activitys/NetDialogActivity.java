package com.example.administrator.myapplication.activitys;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.dialog.MyDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * 一定要把这个标题<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
 *     替换掉
 */
public class NetDialogActivity extends XActivity {
    private QMUITipDialog loading;
    private static final String TAG = "NetDialogActivity";
    private Dialog dialog;
    @Override
    public void init() {
        CharSequence cs = "haha";
        loading = new QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord(cs).create();
        loading.setCancelable(true);
        loading.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                finish();
            }
        },5000);
        initDialog();
    }
    private void initDialog() {
        dialog = new MyDialog(this);
        dialog.show();
    }
    private void destroyDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            } else {
                finish();
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"-------------------onPause()");
    }

    /**
     * 想要按键 响应，先让Activity之前的对话框失去焦点，或者让Activity获取焦点
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            destroyDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }
    private void dismissDialog() {
        if (loading != null) {
            if (loading.isShowing()) {
                loading.dismiss();
                Log.e(TAG,"网络请求的对话框消失");
            }
        }
    }
    @Override
    public int getId() {
//        return 0;
        return R.layout.activity_net_dialog;
    }
}
