package com.umeng.soexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class SelfView extends RelativeLayout {

    private LinearLayout layout_v;
    private List<String> list;
    private List<String> listShow;

    public SelfView(Context context) {
        super(context);
        init(context);
    }

    public SelfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;

    private void init(Context context) {
        this.context = context;
        layout_v = (LinearLayout) View.inflate(context, R.layout.layout_v, null);

        addView(layout_v);
    }


    public void setList(List<String> list) {
        this.list = list;
        if (list != null) {
            setListShow(list);
        }
    }

    public void setListShow(List<String> listShow) {
        layout_v.removeAllViews();
        LinearLayout layout_h = (LinearLayout) View.inflate(context, R.layout.layout_h, null);
        layout_v.addView(layout_h);
        layout_h.removeAllViews();
        int len = 0;
        for (int i = 0; i < listShow.size(); i++) {
            String s = listShow.get(i);
            len += s.length();
            if (len > 22) {
                layout_h = (LinearLayout) View.inflate(context, R.layout.layout_h, null);
                layout_v.addView(layout_h);
                len = 0;
            }
            View view = View.inflate(context, R.layout.layout_text, null);
            TextView textView = view.findViewById(R.id.text_l);
            textView.setText(s);
            layout_h.addView(view);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);
        }
    }
}
