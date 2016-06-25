package com.it520.activity.news.widght;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.it520.activity.R;

;

/**
 * Created by kay on 16/5/13.
 */
public class MarsView extends View {
    Bitmap mars ;
    Bitmap ring_top;
    Bitmap ring_bottom;
    Bitmap hole;
    final int pading =2;
    Paint paint ;
    int ringTopHeight ;
    int marsWidth ;
    int viewWidth ,viewHeight;
    int xMars ;

    int ringMaxY ;
    int marsMiddleY ;
    float scal = 1.0f;
    int ringMiddle;
    int top = 0 ;
    boolean isUp = false;
    int holePading = 0;
    int holeMoing;
    boolean isRun = false;

    PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);

    public MarsView(Context context) {
        super(context);
    }

    public MarsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mars = BitmapFactory.decodeResource(getResources(), R.drawable.base_refresh_mars);
        ring_top = BitmapFactory.decodeResource(getResources(),R.drawable.base_refresh_mars_circle_top);
        ring_bottom = BitmapFactory.decodeResource(getResources(),R.drawable.base_refresh_mars_circle_bottom);
        hole = BitmapFactory.decodeResource(getResources(), R.drawable.base_refresh_mars_hole);
        ringTopHeight = ring_top.getHeight();
        marsWidth = mars.getWidth();
        marsMiddleY =mars.getHeight()/2;
        ringMiddle = marsMiddleY-ring_top.getHeight();
        holePading = (marsWidth - hole.getHeight())/2;
        paint = new Paint();
    }

    public int getMaxY(){
        return ringMaxY;
    }

    public void stopAnimation(){
        isRun = false;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         viewWidth = ring_top.getWidth()+pading*2;
         viewHeight = mars.getHeight()+pading*2;
        xMars = (viewWidth - marsWidth)/2;
        ringMaxY = viewHeight - (ring_top.getHeight()+ring_bottom.getHeight())-pading;
        setMeasuredDimension(viewWidth,viewHeight);
        setPadingTop(0,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLUE);
        setLayerType(LAYER_TYPE_HARDWARE, paint);
        canvas.save();
        canvas.scale(scal, scal, pading + ring_top.getWidth() / 2, pading + ring_top.getHeight() / 2 + top);
        canvas.drawBitmap(ring_top, pading, pading + top, paint);
        canvas.restore();
        canvas.drawBitmap(mars, pading + xMars, pading, paint);

        paint.setXfermode(mode);
        canvas.save();
        if(holeMoing>=hole.getWidth()){
            holeMoing = 0;
        }
        canvas.translate(holeMoing, 0);
        canvas.drawBitmap(hole, pading + xMars, pading + holePading, paint);
        canvas.drawBitmap(hole, pading + xMars-hole.getWidth(), pading + holePading, paint);
        canvas.restore();

        paint.setXfermode(null);

        canvas.save();
        canvas.scale(scal, scal, pading + ring_bottom.getWidth() / 2, pading + ring_top.getHeight() * scal + ring_bottom.getHeight()/2+top);
        canvas.drawBitmap(ring_bottom, pading, pading + ringTopHeight * scal + top, paint);
        canvas.restore();


    }

    public void setPadingTop(int distance ,boolean isAunto){
        if(distance<=0){
            top = 0;
            scal = 0.6f;
            if(isAunto){
                postInvalidate();
            }else{
                invalidate();
            }
            return;
        }else if(distance>ringMaxY){
            top = ringMaxY;
            scal = 0.6f;
            if(isAunto){
                postInvalidate();
            }else{
                invalidate();
            }
            return;
        }
        top = distance;
         if(top<ringMiddle){
             scal = 0.6f+ ((float)top/ringMiddle)*0.4f;
         }else{
             scal = 1.0f- ((float)(top-ringMiddle)/(ringMaxY-ringMiddle))*0.4f;
         }
        if(isAunto){
            postInvalidate();
        }else{
            invalidate();
        }
    }


    public void autoResh(){
        if(!isUp){

            if(top +2>=ringMaxY){
                top = ringMaxY;
                setPadingTop(top,true);
                isUp = true;
                return;
            }
            top = top+2;
            setPadingTop(top,true);
        }else{
            if(top -2<=0){
                top = 0;
                setPadingTop(top,true);
                isUp = false;
                return;
            }
            top = top-2;
            setPadingTop(top,true);
        }
        holeMoing =holeMoing+10;
    }

    public void startThreadResh(){
        if(isRun){
            return;
        }
        Thread trhead = new Thread(){
            @Override
            public void run() {
                while(isRun){
                    //Log.i("hked","run");
                    autoResh();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        trhead.start();
        isRun = true;
    }
}
