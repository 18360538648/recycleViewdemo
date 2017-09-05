package com.example.lsw.recycleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Luosiwei on 2017/9/5.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int mOrientaion;
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    private DividerItemDecoration(Context context, int orientation) {
        this.mContext = context;
        this.mOrientaion = orientation;
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        // 维护TypedArray池，typedArray对象从TypedArray池中获取，在用完以后回收供其他模块复用
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 1. 调用此方法，outRect即为分割矩形
        if (mOrientaion == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }

    }
}
