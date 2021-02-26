package com.example.administrator.myapplication.activitys;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.other.RecyclerViewAdapter;
import com.example.administrator.myapplication.tool.LayoutManagerTool;
import com.example.administrator.myapplication.views.NoSlidingRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterActivity extends XActivity{
    ViewPager mVp;
    private RecyclerViewAdapter mAdapter;
    private List<String> list;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<View> viewList;
    private ArrayList<View> imageViews;
    private final int[] imageIds = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background
    };

    @Override
    public void init() {
        mVp = findViewById(R.id.vp);
        initList();
        initData();
        initAdapter();
//        setRecyclerView();
    }
    private void initData() {
        //设置图片
        imageViews = new ArrayList<View>();
        int len = imageIds.length;
        for (int i = 0; i < len; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);   //设置具体图片
            imageViews.add(imageView);  //添加到集合中

            //添加指示页面的圆点
            ImageView point = new ImageView(this);  //指示圆点我们使用图片展示
//            point.setBackgroundResource(R.drawable.point_selector); //使用自定义的 StateListDrawable
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);  //设置圆点的大小
            if (i == 0) {
                point.setEnabled(true); //将第一个圆点设置为可用，则会显示红色
            } else {
                point.setEnabled(false);    //其它圆点设置为不可用，则会显示为灰色
            }
            params.leftMargin = 18;
            point.setLayoutParams(params);  //设置布局参数
//            pagerIndicator.addView(point);  //添加指示的圆点x
        }
    }

    private void setRecyclerView(NoSlidingRecyclerView rv) {
        LinearLayout.LayoutParams baseInfoParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        rv.setLayoutManager(linearLayoutManager);
        rv.setLayoutParams(baseInfoParams);
        rv.setAdapter(mAdapter);

//        final float scale = this.getResources().getDisplayMetrics().density;
//        int distance = (int) (10 * scale + 0.5f);
//        int distance2 = distance * (-1);
//        new LayoutManagerTool.Builder(this, rv)
//                .canScroll(false)
//                .size(1)
//                .build()
//                .linearLayoutMgr();
//        rv.setAdapter(mAdapter);
    }

    private void initList() {
        viewList = new ArrayList<View>();
        list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            list.add("item---" + i);
            Log.d("RecyclerViewAdapter","-----------item---" + i);
        }
    }

    private void initAdapter() {
        mAdapter = new RecyclerViewAdapter(this,list);
//        View view = LayoutInflater.from(this).inflate(R.layout.item_recycler_view,null);
////        View view2 = LayoutInflater.from(this).inflate(R.layout.item_recycler_view,null);
//        NoSlidingRecyclerView rv = view.findViewById(R.id.rv);
        NoSlidingRecyclerView rv = new NoSlidingRecyclerView(this);
        setRecyclerView(rv);
        viewList.add(rv);
//        viewList.add(view2);
        mViewPagerAdapter = new ViewPagerAdapter(imageViews);
        mVp.setAdapter(mViewPagerAdapter);
//        mVp.setCurrentItem(0);
//        mViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getId() {
        return R.layout.activity_recycler_view_adapter;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recycler_view_load:
                break;
            case R.id.tv_new_recycler_view:
                break;
        }
    }
}
