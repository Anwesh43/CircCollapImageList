package com.anwesome.ui.circcollapimagelistdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.circcollapimagelist.CircCollapImageList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stp);
        CircCollapImageList circCollapImageList = new CircCollapImageList(this);
        for(int i=0;i<12;i++) {
            circCollapImageList.addImage(bitmap);
        }
        circCollapImageList.show();
    }
}
