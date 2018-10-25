package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.mvp.model.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private List<ShopBean.DataBean> data1 = new ArrayList<>();
    private Context context;

    public ShopAdapter(Context context, List<ShopBean.DataBean> data1) {
        this.data1 = data1;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.shop_show_item, null);
        ViewHolder holder = new ViewHolder(view);
        holder.shopname = view.findViewById(R.id.shopname);
        holder.recyclerView = view.findViewById(R.id.shop_item_recycler);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.shopname.setText(data1.get(i).getSellerName());
        List<ShopBean.DataBean.ListBean> list = data1.get(i).getList();
        ShopItemAdapter shopItemAdapter = new ShopItemAdapter(context, list);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(manager);
        viewHolder.recyclerView.setAdapter(shopItemAdapter);
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView shopname;
        RecyclerView recyclerView;
    }
}
