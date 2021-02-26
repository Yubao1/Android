package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.R;

import java.util.List;

/**
 * Created by 86188 on 2020/7/9.
 */

public class OutInterceptEventAdapter extends RecyclerView.Adapter<OutInterceptEventAdapter.OutInterceptEventViewHolder> {
    private List<String> list;
    private Context mContext;
    public OutInterceptEventAdapter(List<String> list,Context context) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public OutInterceptEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_out_intercept_event,parent,false);
        OutInterceptEventViewHolder holder = new OutInterceptEventViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OutInterceptEventViewHolder holder, int position) {
         holder.mTv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OutInterceptEventViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        public OutInterceptEventViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}
