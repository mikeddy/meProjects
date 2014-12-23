package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.aademo.util.PalLog;

/**
 * Created by mik_eddy on 14/12/22.
 */
public class EffectView extends View {
    public int mtype = 1;
    boolean startDrawing = false;
    private PathMeasure mPathMeasure = new PathMeasure();
    private Path mPath = new Path();
    private Paint mPaint;
    float mRatio = 0;
    myAnimation myAnimation;

    public EffectView(Context context) {
        super(context);
        initPaint();
    }

    public EffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public EffectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }


    public void startType(int type) {
        startDrawing = false;
        mtype = type;
        mRatio = 0;
        myAnimation.cancel();
        myAnimation.setDuration(2000);
        setAnimation(myAnimation);
        startAnimation(myAnimation);
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5.0f);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.argb(180, 255, 255, 255));
        myAnimation = new myAnimation(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        super.onDraw(canvas);
        if (!startDrawing) {
            startDrawing = true;
            makeAndMeasurePath();
        }
        float length = mPathMeasure.getLength();
        PathEffect effect = new DashPathEffect(new float[]{length, length}, length * (1 - mRatio));
        mPaint.setPathEffect(effect);
        canvas.drawPath(mPath, mPaint);
    }

    private void makeAndMeasurePath() {
        mPath.reset();
        if(mtype==1){
            measurePathType_1();
        }
        mPath.close();
        mPathMeasure.setPath(mPath, false);
    }


    private void measurePathType_1(){
        float height=getHeight()/2;
        float width=getWidth()/2;
        float x=getLeft()+width/2;
        float y=getTop()+height/2;


        mPath.moveTo(x,y);
        mPath.lineTo(x + width, y);
        mPath.lineTo(x + width, y+height);
        mPath.lineTo(x, y + height);
        mPath.lineTo(x, y);
        mPath.moveTo(x,y);

//        mPath.lineTo(x + width, y+height);
//        mPath.moveTo(x + width, y+height);
    }




    class myAnimation extends Animation {
        View mView;

        public myAnimation(View v) {
            mView = v;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mRatio = interpolatedTime;
            PalLog.printD(mRatio+"");
            mView.invalidate();
        }
    }
}
