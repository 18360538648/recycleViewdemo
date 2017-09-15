package com.example.lsw.recycleviewdemo.slide;


import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2017/9/12.
 */

public class MyItemTouchHelper extends ItemTouchHelper.Callback {
    private MoveChangeListener moveChangeListener;

    public MyItemTouchHelper(MoveChangeListener moveChangeListener) {
        this.moveChangeListener = moveChangeListener;
    }

    // 设置移动方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 可上下滑动标志
        int moveTag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 可左右滑动标志
        int swiperTag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        // 当某个方向不能滑动可以设置为0
        int flag = makeMovementFlags(moveTag, swiperTag);
        return flag;
    }

    // 拖拽时调用
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder fromViewHolder, RecyclerView.ViewHolder targetViewHolder) {
        // 当拖拽时，调用Adapter中的方法，使数据交换
        if (fromViewHolder.getItemViewType() != targetViewHolder.getItemViewType()) {
            // 两者类型不同
            return false;
        }
        int from = fromViewHolder.getAdapterPosition();
        int targe = targetViewHolder.getAdapterPosition();
        moveChangeListener.onMoveItem(from, targe);
        return true;
    }

    // 侧滑时调用
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        moveChangeListener.onRemoveitem(viewHolder.getAdapterPosition());
    }

    // item被选中
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#ff0000"));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    // 没有item选中时
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 恢复透明度和背景颜色，否者会出现viewholder复用出现问题
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        viewHolder.itemView.setAlpha(1);
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // 侧滑时，设置透明度
            // dx为左右移动位移
            int alpha = 1 - (int) (Math.abs(dX) / viewHolder.itemView.getWidth());
            viewHolder.itemView.setAlpha(alpha);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
