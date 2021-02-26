package com.example.administrator.myapplication.other;

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
 * Created by 86188 on 2020/6/30.
 */

public class BaseInfoDataAdapter extends RecyclerView.Adapter<BaseInfoDataAdapter.BaseInfoDataViewHolder> {
    private Context mContent;
    private List<String> list;
    public BaseInfoDataAdapter(Context context,List<String> list) {
        this.mContent = context;
        this.list = list;
    }
    @NonNull
    @Override
    public BaseInfoDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContent).inflate(R.layout.item_base_info_data,parent,false);
        BaseInfoDataViewHolder holder = new BaseInfoDataViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseInfoDataViewHolder holder, int position) {
        holder.mTv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class BaseInfoDataViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        public BaseInfoDataViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}
