package com.example.lsw.recycleviewdemo.header;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lsw.recycleviewdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MyHeaderAdapter extends Adapter<MyHeaderAdapter.MyViewHolder> {
    private List<String> mList;

    public MyHeaderAdapter(List<String> list) {
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
        MyViewHolder myViewAdapter = new MyViewHolder(view);
        return myViewAdapter;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
