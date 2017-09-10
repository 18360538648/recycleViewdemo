package com.example.lsw.recycleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * grid分割线
 * Created by Luosiwei on 2017/9/5.
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        // 维护TypedArray池，typedArray对象从TypedArray池中获取，在用完以后回收供其他模块复用
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizonal(c, parent);
        super.onDraw(c, parent, state);
    }

    /**
     * 画竖直分割线
     *
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getBottom();
        int childrenNum = parent.getChildCount();
        for (int i = 0; i < childrenNum; i++) {
            View childrenView = parent.getChildAt(i % parent.getChildCount());
            ViewGroup.LayoutParams rl = childrenView.getLayoutParams();
            int left = parent.getPaddingLeft() + childrenView.getRight();
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 画水平分割线
     *
     * @param c
     * @param parent
     */
    private void drawHorizonal(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() + left - parent.getPaddingRight();
        int childrenNum = parent.getChildCount();
        for (int i = 0; i < childrenNum; i++) {
            // 得到每一项View对象
            View childrenView = parent.getChildAt(i);
            RecyclerView.LayoutParams rl = (RecyclerView.LayoutParams) childrenView.getLayoutParams();
            int top = childrenView.getBottom() + rl.getMarginEnd();
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        // 1. 调用此方法，outRect即为分割矩形
        int right = mDivider.getIntrinsicHeight();
        int bottom = mDivider.getIntrinsicWidth();
        if (isLastColum(itemPosition, parent)) {
            // TODO: 2017/9/10 这里为什么要画一次
            outRect.set(0, 0, 0, bottom);
            right = 0;
        }
        if (isLastRow(itemPosition, parent)) {
            // TODO: 2017/9/10  这里为什么要画一次
            outRect.set(0, 0, right, 0);
            bottom = 0;
        }
        outRect.set(0, 0, right, bottom);
    }

    /**
     * 是否为最后一行
     *
     * @param itemPosition
     * @param parent
     * @return
     */
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int lastRowNum = parent.getChildCount() % spanCount;
        if (lastRowNum < spanCount || lastRowNum == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否为最后一列
     *
     * @param itemPosition
     * @param parent
     * @return
     */
    private boolean isLastColum(int itemPosition, RecyclerView parent) {
        if (((itemPosition + 1) % getSpanCount(parent)) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取每一行的item个数
     *
     * @param parent
     * @return
     */
    public int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManger = parent.getLayoutManager();
        if (layoutManger instanceof GridLayoutManager) {
            // 将RecyclerView.LayoutManager强转为GridLayoutManager，这样可以取到每一行的个数
            GridLayoutManager gm = (GridLayoutManager) layoutManger;
            return gm.getSpanCount();
        }
        return 0;
    }

}
