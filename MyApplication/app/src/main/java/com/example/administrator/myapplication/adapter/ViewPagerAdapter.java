package com.example.administrator.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 86188 on 2020/7/1.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> imageViews;
    private int mChildCount = 0;
    public ViewPagerAdapter(List<View> list) {
        this.imageViews = list;
    }
    @Override
    public int getCount(){
        return imageViews == null ? 0 : imageViews.size();
    }

    /**
     * 这个方法相当于 ListView 的 getView() 方法
     * @param container 就是ViewPager自身
     * @param position 当前实例化页面的位置
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        final View view = imageViews.get(position);   //  获取对于位置的图片
        container.addView(view);   //添加到ViewPager中
        Log.d("ViewPagerAdapter","childNumber-----------" + container.getChildCount());
        return view;
    }

    /**
     * 比较 view 和 object 是否是同一个实例
     * @param view 页面
     * @param object 上述方法instantiateItem()返回的结果
     * @return
     */
    @Override
    public boolean isViewFromObject(View view,Object object){
        boolean f = view == object;
//        Log.d("ViewPagerAdapter","*************ffff = " + f);
        return f;
    }

    /**
     * 释放资源
     * @param container 就是ViewPager自身
     * @param position 准备要释放页面的位置
     * @param object 要释放的页面
     */
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        //super.destroyItem(container, position, object);
        container.removeView((View)object); //需要进行一下强转
    }
}


