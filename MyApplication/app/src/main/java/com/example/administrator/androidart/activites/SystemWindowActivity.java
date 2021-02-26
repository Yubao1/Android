package com.example.administrator.androidart.activites;

import android.app.Dialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class SystemWindowActivity extends XActivity {
    @Override
    public void init() {
        initView();
    }
    private void initView() {
        Dialog dialog = new Dialog(this.getApplicationContext());
        TextView textView = new TextView(this);
        textView.setText("this is toast!");
        dialog.setContentView(textView);
        /**
         * 8.0以上只能用这个权限
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        } else {
//            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);
        }

        dialog.show();
    }
    @Override
    public int getId() {
        return R.layout.activity_system_window;
    }
}
