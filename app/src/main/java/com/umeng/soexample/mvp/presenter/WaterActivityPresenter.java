package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.umeng.soexample.R;
import com.umeng.soexample.WaterView;
import com.umeng.soexample.mvp.view.AppDelegate;

public class WaterActivityPresenter extends AppDelegate {

    private ImageView imageView;
    private WaterView view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_water;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        final ImageView image = get(R.id.image);
        WaterView waterView = get(R.id.waterView);
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        waterView.result(new WaterView.AnimationListener() {
            @Override
            public void animation(float y) {
                layoutParams.setMargins(0, 0, 0, (int) y);
                image.setLayoutParams(layoutParams);
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;

    }

}
