package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.SelfView;
import com.umeng.soexample.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class LiushiActivityPresenter extends AppDelegate {

    private Button add_x;
    private EditText add_l;
    private List<String> strings=new ArrayList<>();
    private SelfView selfView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_liushi;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        selfView = get(R.id.selfview);
        add_l = get(R.id.add_l);
        add_x = get(R.id.add_x);
        add_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = add_l.getText().toString().trim();
                if (s == null) {
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                }
                strings.add(s);
           selfView.setList(strings);

            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
