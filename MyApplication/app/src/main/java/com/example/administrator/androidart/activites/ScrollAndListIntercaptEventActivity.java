package com.example.administrator.androidart.activites;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.fragment.MyFragment;
import com.example.administrator.myapplication.XActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 当ScrollView内有 ListView和具体的View共存时，且ScrollView的直觉子元素超出屏幕时
 * 就会产生滑动冲突
 */
public class ScrollAndListIntercaptEventActivity extends XActivity {
    @Override
    public void init() {
        initData(true);
//        Fragment fragment = new MyFragment();
//        getFragmentManager().beginTransaction()
//                .add(R.id.fl, fragment)
//                .commit();
    }
    private void initData(boolean flag) {
        for (int j = 0; j < 1; j++) {
            if (flag) {
                ListView listView = findViewById(R.id.lv);
                List<String> dataList = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    dataList.add("leavesC " + i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
            } else {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView.setText("leavesC " + j);

                /**
                 * TextView的Clickable属性默认为false，设置为true时，它令ViewPager是无法滑动的
                 */
                textView.setClickable(true);
            }
//            ll.addView(view);
//            viewList.add(view);
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_scroll_and_list_intercapt_event;
    }
}
