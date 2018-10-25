package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.umeng.soexample.LiushiActivity;
import com.umeng.soexample.MainActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.ShopShowActivity;
import com.umeng.soexample.ThreeActivity;
import com.umeng.soexample.WaterActivity;
import com.umeng.soexample.mvp.view.AppDelegate;

public class MainActivityPresenter extends AppDelegate implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        get(R.id.shopshow).setOnClickListener(this);
        get(R.id.threeshow).setOnClickListener(this);
        get(R.id.water).setOnClickListener(this);
        get(R.id.liushi).setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.shopshow:
                ((MainActivity) context).startActivity(new Intent(context, ShopShowActivity.class));
                break;
            case R.id.threeshow:
                ((MainActivity) context).startActivity(new Intent(context, ThreeActivity.class));
                break;
            case R.id.water:
                ((MainActivity) context).startActivity(new Intent(context, WaterActivity.class));
                break;
            case R.id.liushi:
                ((MainActivity) context).startActivity(new Intent(context, LiushiActivity.class));
                break;
        }
    }
}
