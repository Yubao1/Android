package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class ViewSlideConflictActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_view_slide_conflict;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_out_intercapt_event:
                startActivity(OutInterceptEventActivity.class);
                break;
            case R.id.btn_manufacuring_slide_conflict:
                startActivity(ManufacuringSlideConflictActivity.class);
                break;
            case R.id.btn_in_intercapt_event:
                startActivity(InIntercaptEventActivity.class);
                break;
//            case R.id.btn_scroll_and_button_intercapt_event:
//                startActivity(ScrollAndButtonInterceptEventActivity.class);
//                break;
            case R.id.btn_scroll_and_list_intercapt_event:
                startActivity(ScrollAndListIntercaptEventActivity.class);
                break;
            case R.id.button2:
                startActivity(OutInterceptEvent2Activity.class);
                break;
            case R.id.button3:
                startActivity(InIntercaptEvent2Activity.class);
                break;
            case R.id.btn_custom_view:
                startActivity(CustomViewActivity.class);
                break;
        }
    }
}
