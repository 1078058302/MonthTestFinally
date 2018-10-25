package com.umeng.soexample.fragment;

import android.content.Context;

import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.mvp.presenter.MineFragmentPresenter;

public class MineFragment extends BaseFragmentPresenter<MineFragmentPresenter> {
    @Override
    public Class<MineFragmentPresenter> getClassDelegate() {
        return MineFragmentPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
