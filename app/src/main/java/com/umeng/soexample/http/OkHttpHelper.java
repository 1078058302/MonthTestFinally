package com.umeng.soexample.http;

import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**/
public class OkHttpHelper {
    private final int HTTP_FAILURE = 100;
    private final int HTTP_SUCCESS = 101;

    public OkHttpHelper get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.sendEmptyMessage(HTTP_FAILURE);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Message obtain = Message.obtain();
                obtain.what = HTTP_SUCCESS;
                obtain.obj = string;
                handler.sendMessage(obtain);
            }
        });
        return this;
    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTTP_SUCCESS:
                    String s = (String) msg.obj;
                    lisenter.success(s);
                    break;
                case HTTP_FAILURE:
                    lisenter.error();
                    break;
            }
        }
    };

    private HttpLisenter lisenter;

    public void result(HttpLisenter lisenter) {
        this.lisenter = lisenter;
    }

    public interface HttpLisenter {
        void success(String data);

        void error();
    }
}
