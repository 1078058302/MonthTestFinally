package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.BuyCarAdapter;
import com.umeng.soexample.http.OkHttpHelper;
import com.umeng.soexample.mvp.model.ShopBean;
import com.umeng.soexample.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class BuyCarFragmentPresenter extends AppDelegate {
    private String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private RecyclerView recyclerView;
    private boolean b = true;
    private List<ShopBean.DataBean> data1 = new ArrayList<>();
    private double allprice;
    private int allnum;
    private Button button;
    private TextView bc_price;
    private BuyCarAdapter buyCarAdapter;
    private Object priceAndNum;
    private CheckBox ck;

    @Override
    public int getLayoutId() {
        return R.layout.buycarfragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        recyclerView = get(R.id.buycar_recycler);
        button = get(R.id.finish);
        bc_price = get(R.id.zprice);

        ck = get(R.id.ck);
        ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b) {
                    allCheck(true);
                    b = false;
                } else {
                    allCheck(false);
                    b = true;
                }
            }
        });

        doHttp();
    }

    private void allCheck(boolean b) {
        allnum = 0;
        allprice = 0;
        for (int i = 0; i < data1.size(); i++) {
            List<ShopBean.DataBean.ListBean> list = data1.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setStatus(b);
                int num = list.get(j).getNum();
                double price = list.get(j).getPrice();
                double vprice = num * price;
                allprice += vprice;
                allnum += num;
            }
        }
        if (b) {
            bc_price.setText(allprice + "");
            button.setText("结算(" + allnum + ")");
        } else {
            bc_price.setText(0 + "");
            button.setText("结算(" + 0 + ")");
        }
        buyCarAdapter.notifyDataSetChanged();
    }

    private void doHttp() {
        new OkHttpHelper().get(url).result(new OkHttpHelper.HttpLisenter() {
            @Override
            public void success(String data) {
//                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                ShopBean shopBean = new Gson().fromJson(data, ShopBean.class);
                data1 = shopBean.getData();
                data1.remove(0);
                buyCarAdapter = new BuyCarAdapter(context, data1);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(buyCarAdapter);
                method();

            }

            @Override
            public void error() {

            }
        });
    }

    private void method() {
        buyCarAdapter.setListener(new BuyCarAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<ShopBean.DataBean> list) {
                double allprice = 0;
                int allnum = 0;
                int numAll = 0;
                for (int i = 0; i < list.size(); i++) {
                    List<ShopBean.DataBean.ListBean> list1 = list.get(i).getList();
                    for (int j = 0; j < list1.size(); j++) {
                        numAll = numAll + list1.get(j).getNum();
                        if (list1.get(j).isStatus()) {
                            allprice = allprice + (list1.get(j).getPrice() * list1.get(j).getNum());
                            allnum = allnum + list1.get(j).getNum();
                        }
                    }
                }
                if (allnum < numAll) {//不是全部选中
                    ck.setChecked(false);
                    b = true;
                } else {//全部选中
                    ck.setChecked(true);
                    b = false;
                }
                bc_price.setText(allprice + "");
                button.setText("结算(" + allnum + ")");
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
