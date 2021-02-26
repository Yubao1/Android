package com.example.administrator.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.TestActivity;

//import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2020/4/4.
 */

public class FragmentB extends XFragment {
    private TextView tv;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_x;
    }

    @Override
    public void init(View view) {
        tv = view.findViewById(R.id.tv);
        String s = this.getArguments().getString("mode");
        tv.setText(s);
    }
    public void onClick(View view) {
    }
}
