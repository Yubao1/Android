package com.example.administrator.myapplication.activitys;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.views.LayoutInflaterTestView;

public class LayoutInflaterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = R.layout.activity_layout_inflater;
        View mainView = View.inflate(this,layoutId, null);
        LayoutInflaterTestView ll_v = (LayoutInflaterTestView) mainView.findViewById(R.id.ll);
        View mView = ll_v.getLayoutView();		//在外面再嵌套一层，因为如果不嵌套的话mView的Params将失去作用。待会我们把上一篇总结的都验证完之后，我在为大家分析。
        Log.d("wld______", mView.getLayoutParams().height + "height");
         setContentView(mainView);

        Log.d("wld______", mView.getLayoutParams().width + "width");
    }
}
