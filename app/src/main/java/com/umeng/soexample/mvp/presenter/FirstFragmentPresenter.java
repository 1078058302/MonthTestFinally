package com.umeng.soexample.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.zxing.WriterException;
import com.umeng.soexample.R;
import com.umeng.soexample.http.OkHttpHelper;
import com.umeng.soexample.mvp.model.ThreeBean;
import com.umeng.soexample.mvp.view.AppDelegate;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.android.PermissionUtils;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.util.ArrayList;
import java.util.List;

public class FirstFragmentPresenter extends AppDelegate implements View.OnClickListener {
    private String url = "http://www.zhaoapi.cn/ad/getAd";
    private ViewPager viewPager;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = viewPager.getCurrentItem();
            if (item < 3) {
                item++;
            } else {
                item = 0;
            }
            viewPager.setCurrentItem(item);
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };
    private EditText editText;
    private ImageView imageView;
    private Bitmap qrCode;

    @Override
    public int getLayoutId() {
        return R.layout.firstfragment_layout;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewPager = get(R.id.three_f_vp);
        doHttp();
        get(R.id.two).setOnClickListener(this);
        editText = get(R.id.edit_two);
        get(R.id.addtwo).setOnClickListener(this);
        imageView = get(R.id.two_image);
    }

    private void doHttp() {
        new OkHttpHelper().get(url).result(new OkHttpHelper.HttpLisenter() {
            @Override
            public void success(String data) {
//                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                ThreeBean threeBean = new Gson().fromJson(data, ThreeBean.class);
                List<ThreeBean.DataBean> data1 = threeBean.getData();
                ThreeAdapter threeAdapter = new ThreeAdapter();
                threeAdapter.setList(data1);
                viewPager.setAdapter(threeAdapter);
                handler.sendEmptyMessageDelayed(0, 2000);
            }

            @Override
            public void error() {

            }
        });
    }

    @Override
    public void Result(int requestCode, int resultCode, @Nullable Intent data) {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.two:
                PermissionUtils.permission(context, new PermissionUtils.PermissionListener() {
                    @Override
                    public void success() {
                        context.startActivity(new Intent(context, CaptureActivity.class));
                    }
                });
                break;
            case R.id.addtwo:
                String trim = editText.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(context, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                try {
                    qrCode = CodeCreator.createQRCode(trim, 200, 200, bitmap);
                    imageView.setImageBitmap(qrCode);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    class ThreeAdapter extends PagerAdapter {
        private List<ThreeBean.DataBean> list = new ArrayList<>();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.threeimage, null);
            ImageView imageView = view.findViewById(R.id.pager);
            String icon = list.get(position).getIcon();
            String replace = icon.replace("https", "http");
            Glide.with(context).load(replace).centerCrop().into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        public void setList(List<ThreeBean.DataBean> list) {
            this.list = list;
        }
    }
}
