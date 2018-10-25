package com.umeng.soexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.mvp.presenter.ShopShowActivityPresenter;

public class ShopShowActivity extends BaseActivityPresenter<ShopShowActivityPresenter> {

    @Override
    public Class<ShopShowActivityPresenter> getClassDelegate() {
        return ShopShowActivityPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
