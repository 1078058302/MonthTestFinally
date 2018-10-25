package com.umeng.soexample.fragment;

import android.content.Context;

import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.mvp.presenter.BuyCarFragmentPresenter;

public class BuyCarFragment extends BaseFragmentPresenter<BuyCarFragmentPresenter> {
    @Override
    public Class<BuyCarFragmentPresenter> getClassDelegate() {
        return BuyCarFragmentPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
