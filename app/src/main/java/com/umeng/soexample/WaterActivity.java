package com.umeng.soexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.mvp.presenter.WaterActivityPresenter;

public class WaterActivity extends BaseActivityPresenter<WaterActivityPresenter> {

    @Override
    public Class<WaterActivityPresenter> getClassDelegate() {
        return WaterActivityPresenter.class;
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
