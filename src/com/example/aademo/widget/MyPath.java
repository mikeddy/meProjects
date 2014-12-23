package com.example.aademo.widget;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;

/**
 * Created by mik_eddy on 14/12/22.
 */
public class MyPath {
    private PathMeasure mPathMeasure = new PathMeasure();
    private Path mPath = new Path();
    private Paint mPaint;
    //当前对应本Path的时间轴进度百分比
    float mCalculatePercent = 0;
    //时间轴起始进度,时间轴结束进度
    float mStartRatio=0,mEndRatio=0;

    //动画执行完毕锁(动画执行完毕后需要锁住,避免不必要的重复计算)
    boolean endlocker=false;



    public MyPath(float startRatio,float endRatio){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5.0f);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.argb(180, 255, 255, 255));
        mStartRatio=startRatio;
        mEndRatio=endRatio;
    }


    public Path getPath(){
        return mPath;
    }

    public Paint getPaint(){
        return mPaint;
    }

    /**
     * 计算当前path的绘制进度
     * @param ratio
     */
    public void calculateRatio(float ratio){
        if(endlocker)return;
        mCalculatePercent=(ratio-mStartRatio)/(mEndRatio-mStartRatio);
        if(mCalculatePercent>1)mCalculatePercent=1;
        if(mCalculatePercent<0)mCalculatePercent=0;
    }

    public void makePathEffect(){
        if(endlocker)return;
        float length = mPathMeasure.getLength();
        PathEffect effect = new DashPathEffect(new float[]{length, length}, length * (1 - mCalculatePercent));
        mPaint.setPathEffect(effect);
        if(mCalculatePercent>=1)endlocker=true;
    }

    public void savePath(){
        mPathMeasure.setPath(mPath, false);
    }
}
