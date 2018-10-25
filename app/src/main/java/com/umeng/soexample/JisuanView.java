package com.umeng.soexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.adapter.BuyCarItemAdapter;
import com.umeng.soexample.mvp.model.ShopBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class JisuanView extends RelativeLayout implements View.OnClickListener {

    private TextView add;
    private TextView jian;
    private EditText jisuan;

    public JisuanView(Context context) {
        super(context);
        init(context);
    }

    public JisuanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JisuanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;
    private int num;

    private void init(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.jisuanview, null);
        add = view.findViewById(R.id.add);
        jian = view.findViewById(R.id.jian);
        jisuan = view.findViewById(R.id.edit_jisuan);
        jian.setOnClickListener(this);
        add.setOnClickListener(this);
        jisuan.setOnClickListener(this);
        addView(view);

    }

    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();
    private int position;
    private BuyCarItemAdapter buyCarItemAdapter;

    public void setText(BuyCarItemAdapter buyCarItemAdapter, List<ShopBean.DataBean.ListBean> list, int i) {
        this.list = list;
        this.buyCarItemAdapter = buyCarItemAdapter;
        position = i;
        num = list.get(i).getNum();
        jisuan.setText(list.get(i).getNum() + "");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                num++;
                jisuan.setText(num + "");
                list.get(position).setNum(num);
                listener.callBack();
                buyCarItemAdapter.notifyItemChanged(position);
                break;
            case R.id.jian:
                if (num > 1) {
                    num--;
                } else {
                    Toast.makeText(context, "最少一件", Toast.LENGTH_SHORT).show();
                }
                jisuan.setText(num + "");
                list.get(position).setNum(num);
                listener.callBack();
                buyCarItemAdapter.notifyItemChanged(position);
                break;
            case R.id.edit_jisuan:
                String trim = jisuan.getText().toString().trim();
                jisuan.setText(trim);
                int i = Integer.parseInt(trim);
                if (i == 0) {
                    Toast.makeText(context, "最少一件", Toast.LENGTH_SHORT).show();
                }
                list.get(position).setNum(i);
                listener.callBack();
                buyCarItemAdapter.notifyItemChanged(position);
                break;
        }
    }

    private CallBackListener listener;

    public void setOnCallBack(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }

}
