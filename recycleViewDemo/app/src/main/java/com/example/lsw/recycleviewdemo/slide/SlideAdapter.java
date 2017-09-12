package com.example.lsw.recycleviewdemo.slide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lsw.recycleviewdemo.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> implements MoveChangeListener {
    private List<QQMessage> qqMessageList;

    public SlideAdapter(List<QQMessage> qqMessageList) {
        this.qqMessageList = qqMessageList;
    }

    @Override
    public SlideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SlideViewHolder holder, int position) {
        QQMessage qqMessage = qqMessageList.get(position);
        holder.iv_logo.setImageResource(qqMessage.getLogo());
        holder.tv_name.setText(qqMessage.getName());
        holder.tv_Msg.setText(qqMessage.getLastMsg());
        holder.tv_time.setText(qqMessage.getTime());
        // 触摸头像时，移动item
        holder.iv_logo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return qqMessageList.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int targePosition) {
        // 交换数据
        Collections.swap(qqMessageList, fromPosition, targePosition);
        // 更新界面
        notifyItemMoved(fromPosition, targePosition);
    }

    @Override
    public void onRemoveitem(int removePosition) {
        qqMessageList.remove(removePosition);
        notifyItemRemoved(removePosition);
    }

    public class SlideViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_logo;
        private TextView tv_name;
        private TextView tv_Msg;
        private TextView tv_time;

        public SlideViewHolder(View itemView) {
            super(itemView);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_Msg = (TextView) itemView.findViewById(R.id.tv_lastMsg);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
