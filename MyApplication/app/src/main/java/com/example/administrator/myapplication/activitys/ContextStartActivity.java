package com.example.administrator.myapplication.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.TestActivity;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.fragment.ContextStartFragment;
import com.example.administrator.myapplication.fragment.FragmentA;

public class ContextStartActivity extends XActivity {
    @Override
    public void init() {
//        startFragment();


    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                startNewActivity(false);
                break;
            case R.id.tv_2:
                startNewActivity(true);
                break;
        }
    }
    private void startNewActivity(boolean f) {
        Context c = getApplicationContext();
        Intent intent = new Intent(c, TestActivity.class);
        if (f) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        c.startActivity(intent);
    }
    public void startFragment() {
        ContextStartFragment mainFragment = new ContextStartFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_content, mainFragment)
                .commit();
    }
    @Override
    public int getId() {
        return R.layout.activity_context_start;
    }
}
