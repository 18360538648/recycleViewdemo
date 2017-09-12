package com.example.lsw.recycleviewdemo.slide;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.lsw.recycleviewdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SlideActivity extends Activity {
    private RecyclerView rv_slide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        initViews();
    }

    private void initViews() {
        rv_slide = (RecyclerView) findViewById(R.id.rv_slide);
        rv_slide.setLayoutManager(new LinearLayoutManager(this));
        List<QQMessage> list = DataUtils.init();
        SlideAdapter slideApapter = new SlideAdapter(list);
        rv_slide.setAdapter(slideApapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper(slideApapter));
        //将itemTouchHelper授予rv_slide
        itemTouchHelper.attachToRecyclerView(rv_slide);
    }
}
