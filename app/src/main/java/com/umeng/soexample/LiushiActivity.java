package com.umeng.soexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.mvp.presenter.LiushiActivityPresenter;

public class LiushiActivity extends BaseActivityPresenter<LiushiActivityPresenter> {

    @Override
    public Class<LiushiActivityPresenter> getClassDelegate() {
        return LiushiActivityPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
