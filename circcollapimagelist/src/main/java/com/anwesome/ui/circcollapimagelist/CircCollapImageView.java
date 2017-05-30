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
    }
    public void update(float factor) {
        setScaleY(0.2f+(0.8f)*factor);
        circImage.update(factor);
        postInvalidate();
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            circImage = new CircImage();
            animationHandler = new AnimationHandler();
        }
        canvas.drawColor(Color.parseColor("#BDBDBD"));
        circImage.drawImage(canvas);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.start();
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
