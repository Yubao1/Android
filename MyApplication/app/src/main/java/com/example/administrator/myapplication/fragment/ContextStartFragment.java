package com.example.administrator.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.TestActivity;

/**
 * Created by 86188 on 2020/6/1.
 */

public class ContextStartFragment extends XFragment implements View.OnClickListener{
    private TextView mTvContextNotFlagStartActivity;
    private TextView mTvContextFlagStartActivity;
    private TextView mTvActivityStartActivity;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_context_start;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_context_not_flag_start_activity:
                contextNotFlagStartActivity();
                break;
            case R.id.tv_context_flag_start_activity:
                contextFlagStartActivity();
                break;
            case R.id.tv_activtiy_start_activity:
                activtiyStartActivity();
                break;
        }
    }
    private void contextFlagStartActivity() {
        Context context = getContext();
        Intent intent = new Intent(context, TestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    private void activtiyStartActivity() {
        Context activity = getActivity();
        Intent intent = new Intent(activity, TestActivity.class);
        activity.startActivity(intent);
    }

    private void contextNotFlagStartActivity() {
        Context context = getContext();
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void init(View view) {
        mTvActivityStartActivity = view.findViewById(R.id.tv_activtiy_start_activity);
        mTvContextFlagStartActivity = view.findViewById(R.id.tv_context_flag_start_activity);
        mTvContextNotFlagStartActivity = view.findViewById(R.id.tv_context_not_flag_start_activity);
        mTvContextNotFlagStartActivity.setOnClickListener(this);
        mTvContextFlagStartActivity.setOnClickListener(this);
        mTvActivityStartActivity.setOnClickListener(this);

    }
}
