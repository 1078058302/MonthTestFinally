package com.umeng.soexample.fragment;

import android.content.Context;

import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.mvp.presenter.FirstFragmentPresenter;

public class FirstFragment extends BaseFragmentPresenter<FirstFragmentPresenter> {
    @Override
    public Class<FirstFragmentPresenter> getClassDelegate() {
        return FirstFragmentPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
