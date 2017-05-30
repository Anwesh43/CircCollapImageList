package com.anwesome.ui.circcollapimagelist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 30/05/17.
 */

public class CircCollapImageList {
    private Activity activity;
    private ScrollView scrollView;
    private ImageLayout imageLayout;
    private boolean isShown = false;
    public CircCollapImageList(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
        imageLayout = new ImageLayout(activity);
        scrollView.addView(imageLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addImage(Bitmap bitmap) {
        if(!isShown) {
            imageLayout.addImage(bitmap);
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    private class ImageLayout extends ViewGroup{
        private int w,h;
        public ImageLayout(Context context) {
            super(context);
            initDimension(context);
        }
        public void initDimension(Context context) {
            DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
            if(displayManager!=null) {
                Display display = displayManager.getDisplay(0);
                Point size = new Point();
                display.getRealSize(size);
                w = size.x;
                h = size.y;
            }
        }
        public void addImage(Bitmap bitmap) {
            CircCollapImageView circCollapImageView = new CircCollapImageView(getContext(),bitmap,getChildCount());
            addView(circCollapImageView,new LayoutParams(9*w/10,9*w/10));
            requestLayout();
        }
        public void onMeasure(int wspec,int hspec) {
            int hmax = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hmax += ((int)(child.getMeasuredHeight()*child.getScaleY())+h/20);
            }
            setMeasuredDimension(w,Math.max(hmax,h));
        }
        public void onLayout(boolean reloaded,int a,int b,int wa,int ha) {
            int x = w/20,y = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(x,y,x+child.getMeasuredHeight(),(int)(y+child.getMeasuredHeight()));
                y += (((int)(child.getMeasuredHeight()*child.getScaleY()))+h/20);
            }
        }
    }
}
