package com.it520.activity.splash.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kay on 16/5/8.
 */
public class RingTextView extends View {

    TextPaint paint ;
    Paint circlePaint;
    Paint ringPaint;

    String content = "跳过";
    final int STROKE_WIDTH = 5;
    float textWidth ;
    int desireWidth;
    RectF rectF;
    float sweepAngle = 0;



    RingTextViewOnClickListener clickListener;
    public RingTextView(Context context) {
        super(context);
    }

    public RingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new TextPaint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);
        circlePaint = new Paint();
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.GRAY);
        ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setColor(Color.RED);
        ringPaint.setStrokeWidth(STROKE_WIDTH);
        ringPaint.setStyle(Paint.Style.STROKE);
        textWidth =  paint.measureText(content,0,2);
        desireWidth =(int)(textWidth+STROKE_WIDTH*6);

        rectF = new RectF(STROKE_WIDTH/2,STROKE_WIDTH/2,desireWidth-STROKE_WIDTH/2,desireWidth-STROKE_WIDTH/2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(desireWidth, desireWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLUE);

        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        canvas.drawCircle((int)(desireWidth/2),(int)(desireWidth/2),(int)(desireWidth/2),circlePaint);
        canvas.drawText(content, STROKE_WIDTH * 3, yPos, paint);
        canvas.save();
        canvas.rotate(-90f, desireWidth/2,desireWidth/2);
        canvas.drawArc(rectF, 0, sweepAngle, false,ringPaint);
        canvas.restore();
    }


    public void setProgess(int total ,int now){
       float each = 360/total;
        sweepAngle = now*each;
        invalidate();

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.3f);
                break;
            case MotionEvent.ACTION_UP:
                setAlpha(1.0f);
                if(clickListener!=null){
                    clickListener.onClick(this);
                }
                break;
        }
        return true;
    }



    public void setClickListener(RingTextViewOnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
