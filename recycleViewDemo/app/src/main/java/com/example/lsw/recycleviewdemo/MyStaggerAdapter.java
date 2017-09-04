package com.example.lsw.recycleviewdemo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public class MyStaggerAdapter extends RecyclerView.Adapter<MyStaggerAdapter.MyViewHolder> {
    private List<String> list;
    private List<Integer> heights;

    public MyStaggerAdapter(List<String> list) {
        this.list = list;
        heights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            heights.add((int) Math.max(200, Math.random() * 550));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建ViewHolder
//        MyViewHolder myViewHolder = new MyViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null));
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.tv.getLayoutParams();
        params.height = heights.get(position);
        holder.tv.setLayoutParams(params);
        holder.tv.setBackgroundColor(Color.rgb(100, (int) (Math.random() * 255), (int) (Math.random() * 255)));
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

}
