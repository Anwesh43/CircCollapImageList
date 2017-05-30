package com.anwesome.ui.circcollapimagelist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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
    private CircImage circImage;
    private AnimationHandler animationHandler;
    public CircCollapImageView(Context context, Bitmap bitmap,int index) {
        super(context);
        this.bitmap = bitmap;
        setScaleY(0.2f);
        this.index = index;
        setPivotY(0);
    }
    public void update(float factor) {
        setScaleY(0.2f+(0.8f)*factor);
        circImage.update(factor);
        postInvalidate();
        if(getParent()!=null) {
            getParent().requestLayout();
        }

    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            circImage = new CircImage();
            animationHandler = new AnimationHandler();
        }
        canvas.drawColor(Color.parseColor("#E0E0E0"));
        circImage.drawImage(canvas);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && circImage != null && circImage.handleTap(event.getX(),event.getY())) {
            animationHandler.start();
        }
        return true;
    }
    private class CircImage {
        private float x = 0,y = h/5, scale = 0.2f,initX;
        public CircImage() {
            x = w/5;
            if(index%2 == 0) {
                x = 4 * w / 5;
            }
            y = h/4;
            initX = x;
            scale = 0.2f;
            bitmap = Bitmap.createScaledBitmap(bitmap,w/2,h/2,true);
        }
        public void drawImage(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,1);
            Path path = new Path();
            path.addCircle(0,0,w/4, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,-w/4,-h/4,paint);
            canvas.restore();
        }
        public void update(float factor) {
            x = initX +(w/2-initX)*factor;
            y = h/4 + (h/4)*factor;
            scale = 0.2f+(0.8f)*factor;
        }
        public boolean handleTap(float x,float y) {
            float w = bitmap.getWidth()*scale,h = bitmap.getHeight();
            return  x >= this.x - w/2 && x <= this.x+w/2 && y >= this.y -h/2 && y <= this.y +h/2;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private int dir = 0;
        private boolean isAnimating = false;
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addListener(this);
            endAnim.addListener(this);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            update((float)valueAnimator.getAnimatedValue());
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                if(dir == 0) {
                }
                else {

                }
                dir = dir == 0?1:0;
                isAnimating = false;
            }
        }
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    startAnim.start();
                }
                else {
                    endAnim.start();
                }
                isAnimating = true;
            }
        }
    }
}
