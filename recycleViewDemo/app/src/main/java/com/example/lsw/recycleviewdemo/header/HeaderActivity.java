package com.example.lsw.recycleviewdemo.header;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.example.lsw.recycleviewdemo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */

public class HeaderActivity extends Activity {
    private WrapRecyclerView wrapRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        initViews();
    }

    private void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item " + i);
        }
        TextView tvHeader = new TextView(this);
        tvHeader.setText("这是头部");
        TextView tvFooter = new TextView(this);
        tvFooter.setText("这是尾部");
        wrapRecyclerView = (WrapRecyclerView) findViewById(R.id.wrap_recyclerview);
        wrapRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wrapRecyclerView.addHeaderView(tvHeader);
        wrapRecyclerView.addFooterView(tvFooter);
        wrapRecyclerView.setAdapter(new MyHeaderAdapter(list));
    }
}
