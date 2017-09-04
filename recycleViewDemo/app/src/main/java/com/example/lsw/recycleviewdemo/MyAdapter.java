package com.example.lsw.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> list;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建ViewHolder
        MyViewHolder myViewHolder = new MyViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_expandable_list_item_1, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        if (null != onItemClickListener) {
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });
        }
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

    public void addItem(int position) {
        list.add("add" + position);
//        效率低
//        notifyDataSetChanged();
        notifyItemInserted(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setItemClick(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


}
