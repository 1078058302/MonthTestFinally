package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.umeng.socialize.UMShareAPI;
import com.umeng.soexample.R;
import com.umeng.soexample.ThreeActivity;
import com.umeng.soexample.fragment.BuyCarFragment;
import com.umeng.soexample.fragment.FirstFragment;
import com.umeng.soexample.fragment.MineFragment;
import com.umeng.soexample.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class ThreeActivityPresenter extends AppDelegate implements View.OnClickListener {

    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_three;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewPager = get(R.id.three_vp);
        get(R.id.first).setOnClickListener(this);
        get(R.id.three).setOnClickListener(this);
        get(R.id.buycar).setOnClickListener(this);
        fragments.add(new FirstFragment());
        fragments.add(new MineFragment());
        fragments.add(new BuyCarFragment());
        MyAdapter myAdapter = new MyAdapter(((ThreeActivity) context).getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

    }


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void Result(int requestCode, int resultCode, @Nullable Intent data) {
        super.Result(requestCode, resultCode, data);
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first:
                viewPager.setCurrentItem(0);
                break;
            case R.id.three:
                viewPager.setCurrentItem(1);
                break;
            case R.id.buycar:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
