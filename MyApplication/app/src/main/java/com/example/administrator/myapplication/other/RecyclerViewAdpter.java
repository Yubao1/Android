package com.example.administrator.myapplication.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.activitys.JsonActivity;
import com.example.administrator.myapplication.activitys.RecylerViewActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/3/14.
 */

public class RecyclerViewAdpter extends RecyclerView.Adapter<RecyclerViewAdpter.ViewHolder> {

    private final Context context;
    private List<String> list;
    private boolean[] isSelect;
    public RecyclerViewAdpter(Activity context, List<String> list,boolean[] isSelect) {
        this.context = context;
        this.list = list;
        this.isSelect = isSelect;
    }
    public void addList(int position,String s) {
        list.add(position,s);
        notifyItemInserted(position);
    }
    public void deleteList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recler_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String s = list.get(position);
        holder.tv.setText(s);
        if (isSelect[position]) {
            holder.tv.setBackgroundResource(R.drawable.ic_launcher_background);
        } else {
            holder.tv.setBackgroundResource(0);
        }
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tv.setEnabled(false);
//                holder.tv.setSelected(true);
                Intent intent = new Intent(context, JsonActivity.class);
                context.startActivity(intent);
//                if (isSelect[position]) {
//                    isSelect[position] = false;
//                } else {
//                    isSelect[position] = true;
//                }
                RecyclerViewAdpter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
