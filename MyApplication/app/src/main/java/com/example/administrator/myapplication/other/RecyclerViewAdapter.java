package com.example.administrator.myapplication.other;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.myapplication.tool.DimensionConvert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 86188 on 2020/6/30.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<String> list;
    private Context mContext;
    private List<String> list2;
    public RecyclerViewAdapter(Context context,List<String> list) {
         this.list = list;
         mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_adapter,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        initList2();
        final String content = list.get(position);
        holder.mTv.setText(content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3,GridLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Paint paint = new Paint();
                paint.setTextSize(DimensionConvert.sp2px(mContext, 25.0f));
                int width = (int) paint.measureText(content);
                return width / DimensionConvert.dip2px(mContext, 250) + 1;
            }
        });
        holder.mRv.setLayoutManager(gridLayoutManager);
        BaseInfoDataAdapter baseInfoDataAdapter = new BaseInfoDataAdapter(mContext,list2);
        holder.mRv.setAdapter(baseInfoDataAdapter);
    }

    private void initList2() {
        String content = "";
        int y = 4;
        int num = new Random().nextInt(15);
        num = num + y;
        num = 2;
        list2 = new ArrayList<String>();
        for (int i = 0; i < num; i++) {
            if (num < 3) {
                content = "呵呵";
            } else if (num < 9) {
                content = "呵呵呵呵呵";
            } else {
                content = "呵呵呵呵呵呵呵呵";
            }
            list2.add(content + i);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        RecyclerView mRv;
        public MyViewHolder(View itemView) {
            super(itemView);
            mRv = itemView.findViewById(R.id.rv);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}
