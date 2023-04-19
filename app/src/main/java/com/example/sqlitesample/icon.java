package com.example.sqlitesample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class icon extends FrameLayout {
    int setHeight;
    int setWidth;
    String TAG = "manh mon ha ha";
    public icon(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
        setWidth = setHeight;
        Log.d(TAG, "onMeasure: widthMeasureSpec = "+widthMeasureSpec+"heightMeasureSpec = "+heightMeasureSpec+" setWidth = "+setWidth+" setHeight = "+setHeight);
        setMeasuredDimension(setWidth,setHeight);
    }
}
