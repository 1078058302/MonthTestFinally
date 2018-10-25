package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.soexample.JisuanView;
import com.umeng.soexample.R;
import com.umeng.soexample.mvp.model.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class BuyCarItemAdapter extends RecyclerView.Adapter<BuyCarItemAdapter.ViewHolder> {
    private Context context;
    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();
    private double p;
    private int n;

    public BuyCarItemAdapter(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.buy_car_show_item, null);
        ViewHolder holder = new ViewHolder(view);
        holder.imageView = view.findViewById(R.id.bc_image);
        holder.title = view.findViewById(R.id.bc_title);
        holder.desc = view.findViewById(R.id.bc_desc);
        holder.jisuanView = view.findViewById(R.id.bc_num);
        holder.price = view.findViewById(R.id.bc_price);
        holder.checkBox = view.findViewById(R.id.bc_ck);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImages();
        String[] split = images.split("[|]");
        if (images != null) {
            Glide.with(context).load(split[0]).into(viewHolder.imageView);
        } else {
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.desc.setText(list.get(i).getSubhead());
        viewHolder.price.setText(list.get(i).getPrice() + "");
        viewHolder.checkBox.setChecked(list.get(i).isStatus());
        //如果商品选中
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isStatus()) {
                    list.get(i).setStatus(false);
                } else {
                    list.get(i).setStatus(true);
                }
                notifyItemChanged(i);
                listener.callBack();
            }
        });
        viewHolder.jisuanView.setText(this, list, i);
        viewHolder.jisuanView.setOnCallBack(new JisuanView.CallBackListener() {
            @Override
            public void callBack() {
                listener.callBack();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        ImageView imageView;
        TextView title;
        TextView desc;
        TextView price;
        JisuanView jisuanView;
        CheckBox checkBox;
    }

    //传递接口
    private ShopCallBackListener listener;

    public void setListener(ShopCallBackListener listener) {
        this.listener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();
    }
}
