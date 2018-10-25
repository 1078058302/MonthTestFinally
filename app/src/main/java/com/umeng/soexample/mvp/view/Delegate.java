package com.umeng.soexample.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface Delegate {
    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    void initData(Bundle savedInstanceState);

    View rootView();
}
