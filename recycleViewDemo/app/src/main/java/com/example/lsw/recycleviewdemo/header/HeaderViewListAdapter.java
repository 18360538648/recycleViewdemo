package com.example.lsw.recycleviewdemo.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */

public class HeaderViewListAdapter extends RecyclerView.Adapter {
    private List<View> mHeaderViewInfos;
    private List<View> mFooterViewInfos;
    private RecyclerView.Adapter mAdapter;
    private final static int HEAD = 0;
    private final static int FOOT = 1;

    public HeaderViewListAdapter(List<View> mHeaderViewInfos, List<View> mFooterViewInfos, RecyclerView.Adapter adapter) {
        if (null == mHeaderViewInfos) {
            mHeaderViewInfos = new ArrayList<>();
        } else {
            this.mHeaderViewInfos = mHeaderViewInfos;
        }
        if (null == mFooterViewInfos) {
            mFooterViewInfos = new ArrayList<>();
        } else {
            this.mFooterViewInfos = mFooterViewInfos;
        }
        if (null != adapter) {
            this.mAdapter = adapter;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int headerNum = mHeaderViewInfos.size();
        if (position < headerNum) {
            // 头部
            return HEAD;
        }
        // 去除头的body实际位置
        int adjustPosition = position - headerNum;
        if (adjustPosition < mAdapter.getItemCount()) {
            //body
            // 切记使用除去头，以后的位置
            return mAdapter.getItemViewType(adjustPosition);

        }
        //尾部
        return FOOT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            return new MyHeaderViewHolder(mHeaderViewInfos.get(0));
        } else if (viewType == FOOT) {
            return new MyHeaderViewHolder(mFooterViewInfos.get(0));
        }
        // 调用内层真实的onCreateViewHolder方法
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int headerNum = mHeaderViewInfos.size();
        if (position < headerNum) {
            // 头部
            return;
        }
        int adjustPosition = position - headerNum;
        if (adjustPosition < mAdapter.getItemCount()) {
            //body
            // 切记使用除去头，以后的位置
            // 调用内层真实的onCreateViewHolder方法
            mAdapter.onBindViewHolder(holder, adjustPosition);
            return;
        }
        //尾部
        return;
    }

    @Override
    public int getItemCount() {
        if (null != mAdapter) {
            return mAdapter.getItemCount() + mHeaderViewInfos.size() + mFooterViewInfos.size();
        }
        return mAdapter.getItemCount() + mHeaderViewInfos.size();

    }

    private class MyHeaderViewHolder extends RecyclerView.ViewHolder {

        public MyHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
