package com.anwesome.ui.circcollapimagelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    private class CircImage {
        private float x = 0,y = h/5, scale = 0.2f;
        public CircImage() {
            x = w/5;
            if(index%2 == 0) {
                x = 4 * w / 5;
            }
            y = h/5;
            scale = 0.2f;
            bitmap = Bitmap.createScaledBitmap(bitmap,w/2,h/2,true);
        }
        public void drawImage(Canvas canvas) {
            canvas.save();
            canvas.scale(scale,scale);
            Path path = new Path();
            path.addCircle(x,y,w/4, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,x-w/4,y-h/4,paint);
            canvas.restore();
        }
        public void update(float factor) {
            x = w/5 +(w/2-w/5)*factor;
            y = h/5 + (h/2-h/5)*factor;
            scale = 0.2f+(0.8f)*factor;
        }
    }
}
