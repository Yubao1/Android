package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class DemoActivity extends XActivity {
    RelativeLayout mRl;
    TextView mTvAliPay;
    TextView mTvWechatPay;
    @Override
    public void init() {
        mRl = findViewById(R.id.rl_qrcode);
        mTvAliPay = findViewById(R.id.tv_ali_pay);
        mTvWechatPay = findViewById(R.id.tv_wechat_pay);

    }
    private void changeStyle(int payId) {
        int tvBg = R.drawable.img_activity_insurance_joint;
        int tvColor0091FF = getResources().getColor(R.color.color_0091FF);
        int tvColorFFFFFF = getResources().getColor(R.color.white);
        mTvAliPay.setBackgroundResource(payId == 1 ? 0 : tvBg);
        mTvAliPay.setTextColor(payId == 1 ? tvColorFFFFFF : tvColor0091FF);
        mTvWechatPay.setBackgroundResource(payId == 2 ? 0 : tvBg);
        mTvWechatPay.setTextColor(payId == 2 ? tvColorFFFFFF : tvColor0091FF);
        mRl.setBackgroundResource(payId == 2 ? R.drawable.img_scan_pay : R.drawable.img_scan_pay_2);
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public int getId() {

        return R.layout.activity_demo;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wechat_pay:
                changeStyle(2);
                break;
            case R.id.tv_ali_pay:
                changeStyle(1);
                break;
        }
    }
}
