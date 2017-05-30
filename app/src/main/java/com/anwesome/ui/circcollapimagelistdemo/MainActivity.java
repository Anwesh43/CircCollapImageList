package com.anwesome.ui.circcollapimagelistdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.circcollapimagelist.CircCollapImageList;
import com.anwesome.ui.circcollapimagelist.OnCollapseListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stp);
        CircCollapImageList circCollapImageList = new CircCollapImageList(this);
        for(int i=0;i<12;i++) {
            final int index = i+1;
            circCollapImageList.addImage(bitmap, new OnCollapseListener() {
                @Override
                public void onCollapse() {
                    Toast.makeText(MainActivity.this, String.format("%d collapsed",index), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onOpen() {
                    Toast.makeText(MainActivity.this, String.format("%d opened",index), Toast.LENGTH_SHORT).show();
                }
            });
        }
        circCollapImageList.show();
    }
}
