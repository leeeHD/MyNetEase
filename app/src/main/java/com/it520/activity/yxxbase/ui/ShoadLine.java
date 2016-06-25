package com.it520.activity.yxxbase.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Yu xiangxin on 2016/6/21 - 14:16.
 *
 * @desc ${TODD}
 */
public class ShoadLine extends View {
    private Paint mPaint;

    public ShoadLine(Context context) {
        super(context, null);
    }

    public ShoadLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setShadowLayer(50f, 0, 0, Color.WHITE);


        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕宽度
        float width = wm.getDefaultDisplay().getWidth();
        //控件不含阴影部分的宽度
        float startLeft = 5;
        //圆角矩形起始坐标
        float startTop = 3;

        canvas.drawRoundRect(new RectF(0, 0, width, 5), 0, 0, mPaint);
        //canvas.drawLine(startLeft, startTop, (startLeft + width), 40,mPaint);
        //canvas.drawLine(0,0,width,0,mPaint);
    }
}
