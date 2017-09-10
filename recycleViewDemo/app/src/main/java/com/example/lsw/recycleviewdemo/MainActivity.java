package com.example.lsw.recycleviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    LinearLayoutCompat linearLayoutCompat;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 60; i++) {
            list.add("item" + i);
        }
        initViews();
    }

    private void initViews() {
        myAdapter = new MyAdapter(list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // 一定要设置setLayoutManager，否则绘制不出来布局
//        // 第三个参数是数据是否倒置
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // gridview效果
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new MyAdapter(list));
        // 瀑布流效果，每一个项大小不一致
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(new MyStaggerAdapter(list));
        // gridView分割线
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // Listview分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
        myAdapter.setItemClick(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "click me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addItem(View v) {
        myAdapter.addItem(3);
    }

}
