package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.ShopAdapter;
import com.umeng.soexample.http.OkHttpHelper;
import com.umeng.soexample.mvp.model.ShopBean;
import com.umeng.soexample.mvp.view.AppDelegate;

import java.util.List;

public class ShopShowActivityPresenter extends AppDelegate implements View.OnClickListener {
    private String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_show;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        recyclerView = get(R.id.ss_recycler);
        doHttp();
    }

    private void doHttp() {
        new OkHttpHelper().get(url).result(new OkHttpHelper.HttpLisenter() {
            @Override
            public void success(String data) {
                ShopBean shopBean = new Gson().fromJson(data, ShopBean.class);
                List<ShopBean.DataBean> data1 = shopBean.getData();
                data1.remove(0);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                ShopAdapter shopAdapter = new ShopAdapter(context, data1);
                recyclerView.setAdapter(shopAdapter);
            }

            @Override
            public void error() {

            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void Result(int requestCode, int resultCode, @Nullable Intent data) {
        super.Result(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

    }
}
