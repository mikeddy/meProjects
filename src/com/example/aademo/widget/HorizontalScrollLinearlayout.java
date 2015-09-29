package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.example.aademo.util.AppUtils;
import com.example.aademo.util.PalLog;

/**
 * Created by mik_eddy on 15/9/9.
 */
public class HorizontalScrollLinearlayout extends LinearLayout {

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mMaximumVelocity;//最大手势速率
    private int mMinimumVelocity;//最小手势速率
    private static final int DEFAULTX = -10000000;
    private float mFloatLastX = DEFAULTX;//最后一次获取到的X坐标

    public HorizontalScrollLinearlayout(Context context) {
        super(context);
        init(context);
    }

    public HorizontalScrollLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalScrollLinearlayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mScroller = new OverScroller(context);
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity= ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        PalLog.printE("child==>dispatchTouchEvent==>" + AppUtils.getEventActionName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        PalLog.printE("child==>onInterceptTouchEvent==>" + AppUtils.getEventActionName(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PalLog.printE("child==>onTouchEvent==>" + AppUtils.getEventActionName(event));
        return super.onTouchEvent(event);
    }

//    @Override
//    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
//        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//        int childHeightMeasureSpec=child.getMeasuredHeight();
//        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
////        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
//    }

    /**
     * 重写scrollTo防止滑过头
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        if (x < 0) {
            x = 0;
        }
        if (x > getWidth()) {
            x = getWidth();
        }
        if (x != getScrollX()) {
            super.scrollTo(x, y);
        }
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    public void doScroll(MotionEvent ev) {
        getVelocityTracker().addMovement(ev);
        float x = ev.getX();
        if (mFloatLastX == DEFAULTX) mFloatLastX = x;
        float dx = ev.getX() - mFloatLastX;
        int nIntOffset = (int) -dx;
        PalLog.printD(ev.getAction() + "nIntOffset:" + nIntOffset);
        scrollBy(nIntOffset, 0);
        mFloatLastX = x;
    }

    public void reSet() {
        mFloatLastX = DEFAULTX;
        getVelocityTracker().computeCurrentVelocity(1000, mMaximumVelocity);
        int velocityX = (int) getVelocityTracker().getXVelocity();
        if (Math.abs(velocityX) > mMinimumVelocity) {
            fling(-velocityX);
        }
        recycleVelocityTracker();
    }

    public void fling(int velocityX) {
        mScroller.fling(getScrollX(), 0, velocityX, 0, 0, getWidth(), 0, 0);
        invalidate();
    }

    private VelocityTracker getVelocityTracker() {
        if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
        return mVelocityTracker;
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

}
