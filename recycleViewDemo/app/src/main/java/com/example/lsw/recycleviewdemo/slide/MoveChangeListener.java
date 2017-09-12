package com.example.lsw.recycleviewdemo.slide;

/**
 * Created by Administrator on 2017/9/12.
 */

public interface MoveChangeListener {
    /**
     * @param fromPosition  移动的位置
     * @param targePosition 交换的位置
     */
    public void onMoveItem(int fromPosition, int targePosition);

    public void onRemoveitem(int removePosition);
}
