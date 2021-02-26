package com.example.administrator.myapplication.activitys;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.fragment.FragmentA;

public class FragmentActivity extends XActivity {
    @Override
    public void init() {
        startFragment();
    }
    public void startFragment() {
        FragmentA mainFragment = new FragmentA();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_content, mainFragment)
                .commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getId() {
        return R.layout.activity_fragment;
    }
}
