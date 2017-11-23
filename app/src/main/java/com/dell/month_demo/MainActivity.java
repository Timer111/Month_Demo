package com.dell.month_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dell.month_demo.api.Api;
import com.dell.month_demo.bean.News;
import com.dell.month_demo.presenter.Presenter;
import com.dell.month_demo.view.Iview;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview{

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Presenter p = new Presenter(this);
        p.getUrl(Api.HOME_URL);
    }

    @Override
    public void ShowMessage(final List<News.DataBean> list) {
        LinearLayoutManager manager = new LinearLayoutManager(this);  //显示方式
        recyclerView.setLayoutManager(manager);   //与recyclerview绑定
        //适配器
        myAdapter = new MyAdapter(MainActivity.this,list);
        myAdapter.setOnClickLisener(new MyAdapter.OnClickLisener() {
            @Override
            public void OnDainji(View v, int position) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                intent.putExtra("url", list.get(position).getVedio_url());
                startActivity(intent);
            }

            @Override
            public void OnLong(View v, int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("mm",list.get(position).getVedio_url());
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(myAdapter);
    }
}
