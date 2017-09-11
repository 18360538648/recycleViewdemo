package com.example.lsw.recycleviewdemo.header;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */

public class WrapRecyclerView extends RecyclerView {
    private Adapter mAdapter;
    private List<View> mHeaderViewInfos = new ArrayList<>();
    private List<View> mFooterViewInfos = new ArrayList<>();

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 增加头部
     *
     * @param v
     */
    public void addHeaderView(View v) {
        mHeaderViewInfos.add(v);
    }

    /**
     * 增加尾部
     *
     * @param v
     */
    public void addFooterView(View v) {
        mFooterViewInfos.add(v);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0 || mFooterViewInfos.size() > 0) {
            // 如果有头和尾部，在adapter外面封装一个HeaderViewListAdapter
            adapter = new HeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        }
        super.setAdapter(adapter);
    }
}
