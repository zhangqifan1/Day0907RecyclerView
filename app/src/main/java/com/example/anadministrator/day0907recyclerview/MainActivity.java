package com.example.anadministrator.day0907recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 添加
     */
    private Button mBtnAdd;
    /**
     * 删除
     */
    private Button mBtnDelete;
    /**
     * List
     */
    private Button mBtnList;
    /**
     * Grid
     */
    private Button mBtnGrid;
    /**
     * flow
     */
    private Button mBtnFlow;
    private RecyclerView mRecyclerview;
    private List<String> list;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private Animation animation;
    private DefaultItemAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        MyDecoration myDecoration = new MyDecoration(this, linearLayoutManager.getOrientation());
        myRecycleViewAdapter = new MyRecycleViewAdapter(list, this);
        mRecyclerview.addItemDecoration(myDecoration);
        mRecyclerview.setAdapter(myRecycleViewAdapter);

        mRecyclerview.setLayoutManager(linearLayoutManager);
        myRecycleViewAdapter.setmMyItemclickListener(new MyRecycleViewAdapter.MyItemclickListener() {
            @Override
            public void itemclick(View view, int position) {
                Toast.makeText(MainActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("Content_" + i);
        }
    }

    private void initView() {
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(this);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add://D.添加数据
                list.add(1, "孙子");
                myRecycleViewAdapter.notifyItemInserted(1);
                break;
            case R.id.btn_delete://D.删除数据
                list.remove(2);
                myRecycleViewAdapter.notifyItemRemoved(2);
                break;


        }
    }
}