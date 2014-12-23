package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 14/12/22.
 */
public class EffectView extends View {
    public int mtype = 1;
    boolean startDrawing = false;
    float mRatio = 0;
    myAnimation myAnimation;
    MyEffectPath mEffectPath;

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
        mEffectPath = new MyEffectPath();
        myAnimation = new myAnimation(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!startDrawing) {
            startDrawing = true;
            makeAndMeasurePath();
        }
        mEffectPath.draw(canvas, mRatio);
    }

    private void makeAndMeasurePath() {
        if (mtype == 1) {
            measurePathType_1();
        } else if (mtype == 2) {
            measurePathType_2();
        }else if(mtype==3){

        }
    }

    private void measurePathType_2() {
        float height = getHeight() / 2;
        float width = getWidth() / 2;
        float radius = width / 5;

        mEffectPath.clear();
        MyPath pathCircle = mEffectPath.createNew(0, 0.7f);
        pathCircle.getPath().addCircle(width, height, radius, Path.Direction.CCW);
        pathCircle.savePath();

        MyPath pathRight = mEffectPath.createNew(0.7f, 0.9f);
        pathRight.getPath().moveTo(width - radius / 2, height - radius / 12);
        pathRight.getPath().lineTo(width - radius / 4, height + radius / 3);
        pathRight.getPaint().setColor(getResources().getColor(R.color.red));
        pathRight.savePath();

        MyPath pathRight1 = mEffectPath.createNew(0.9f, 1.0f);
        pathRight1.getPath().moveTo(width - radius / 4, height + radius / 3);
        pathRight1.getPath().lineTo(width + radius / 2, height - radius / 3);
        pathRight1.getPaint().setColor(getResources().getColor(R.color.red));
        pathRight1.savePath();
    }

    private void measurePathType_1() {
        float height = getHeight() / 2;
        float width = getWidth() / 2;
        float x = getLeft() + width / 2;
        float y = getTop() + height / 2;

        mEffectPath.clear();
        MyPath path = mEffectPath.createNew(0, 0.7f);
        path.getPath().moveTo(x, y);
        path.getPath().lineTo(x + width, y);
        path.getPath().lineTo(x + width, y + height);
        path.getPath().lineTo(x, y + height);
        path.getPath().lineTo(x, y);
        path.savePath();

        MyPath path1 = mEffectPath.createNew(0.7f, 1.0f);
        path1.getPath().moveTo(x, y);
        path1.getPath().lineTo(x + width, y + height);
        path1.savePath();

        MyPath path2 = mEffectPath.createNew(0.7f, 1.0f);
        path2.getPath().moveTo(x + width, y);
        path2.getPath().lineTo(x, y + height);
        path2.savePath();
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
            mView.invalidate();
        }
    }
}
