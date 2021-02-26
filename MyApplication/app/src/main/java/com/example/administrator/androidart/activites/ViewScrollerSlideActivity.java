package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.administrator.R;
import com.example.administrator.androidart.views.ScrollerTest;
import com.example.administrator.myapplication.XActivity;

public class ViewScrollerSlideActivity extends XActivity {

    private ScrollerTest scrollTest;
    private Button button;
    @Override
    public void init() {
        button = (Button) findViewById(R.id.button);
        scrollTest = (ScrollerTest) findViewById(R.id.scroll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 当前滑动为View内容滑动
                 */
                scrollTest.setPosition(2);
            }
        });

    }

    @Override
    public int getId() {
        return R.layout.activity_view_scroller_slide;
    }
}
