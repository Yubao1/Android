package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.R;

//import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2020/4/4.
 */

public class FragmentA extends XFragment {
    private Button mBtn;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_x;
    }

    @Override
    public void init(View view) {
        mBtn = view.findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startFragment();
            }
        });
    }

    private void startFragment() {
        FragmentB fragment = new FragmentB();
        Bundle bundle = new Bundle();
        bundle.putString("mode", "把数据传给FragmentB");
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }
}
