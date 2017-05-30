package com.anwesome.ui.circcollapimagelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 30/05/17.
 */

public class CircCollapImageView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private int time = 0,w,h,index;
    public CircCollapImageView(Context context, Bitmap bitmap,int index) {
        super(context);
        this.bitmap = bitmap;
        setScaleY(0.2f);
        this.index = index;
    }
    public void update(float factor) {
        setScaleY(0.2f+(0.8f)*factor);
        postInvalidate();
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        canvas.drawColor(Color.parseColor("#BDBDBD"));
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
