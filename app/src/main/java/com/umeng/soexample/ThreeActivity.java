package com.umeng.soexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.mvp.presenter.ThreeActivityPresenter;

public class ThreeActivity extends BaseActivityPresenter<ThreeActivityPresenter> {

    @Override
    public Class<ThreeActivityPresenter> getClassDelegate() {
        return ThreeActivityPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
