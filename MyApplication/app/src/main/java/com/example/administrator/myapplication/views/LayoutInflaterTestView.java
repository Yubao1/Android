package com.example.administrator.myapplication.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.R;

/**
 * Created by Administrator on 2020/6/14.
 */

public class LayoutInflaterTestView extends LinearLayout {
    private View layoutView;
    public LayoutInflaterTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    		init(context);
    }
    public LayoutInflaterTestView(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    		init(context);
    }
    private void init(Context mContext) {
        if (isInEditMode()) {
            return;
        }
    		LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		layoutView = (View) mInflater.inflate(R.layout.layout_inflater_child, this, false);
    		//打印layoutView类型，
        Log.d("wld____________", layoutView + "");
    }

    public View getLayoutView() {
        return layoutView;
    }
}
