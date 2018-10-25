package com.umeng.soexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WaterView extends View {

    private Paint paintTop;
    private Paint paintBottom;
    private Path pathBottom;
    private Path pathTop;
    private float φ;

    public WaterView(Context context) {
        super(context);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paintTop = new Paint();
        paintTop.setColor(Color.WHITE);
        paintTop.setAntiAlias(true);

        paintBottom = new Paint();
        paintBottom.setColor(Color.WHITE);
        paintBottom.setAntiAlias(true);
        paintBottom.setAlpha(60);
        pathTop = new Path();
        pathBottom = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathTop.reset();
        pathBottom.reset();
        //起始路径
        pathTop.moveTo(getLeft(), getBottom());
        pathBottom.moveTo(getLeft(), getBottom());
        //获取每个宽度值所占的度数
        double pi = Math.PI * 2 / getWidth();
        φ -= 0.1f;
        for (float i = 0; i <= getWidth(); i += 20) {
            float y = (float) (10 * Math.cos(pi * i + φ) + 10);
            pathTop.lineTo(i, y);
            pathBottom.lineTo(i, (float) (10 * Math.sin(pi * i + φ)));
            if (listener != null) {
                listener.animation(y);
            }

        }
        //终止路径
        pathTop.lineTo(getRight(), getBottom());
        pathBottom.lineTo(getRight(), getBottom());
        canvas.drawPath(pathTop, paintTop);
        canvas.drawPath(pathBottom, paintBottom);
        postInvalidateDelayed(20);
    }

    private AnimationListener listener;

    public void result(AnimationListener listener) {
        this.listener = listener;
    }

    public interface AnimationListener {
        void animation(float y);
    }
}
