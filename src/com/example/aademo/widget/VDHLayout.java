package com.example.aademo.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.aademo.util.PalLog;

public class VDHLayout extends LinearLayout {
    private ViewDragHelper mDragger;

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                PalLog.printD("pointerID:" + pointerId);
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                PalLog.printD("left:" + left + "   dx:" + dx);
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
//                PalLog.printD("top:" + top + "   dy:" + dy);
//                if(top>=576)top=576;
                return top;
            }

            //手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                PalLog.printD("x:"+xvel+"    y:"+yvel);
//                mDragger.settleCapturedViewAt(100,100);
                invalidate();
            }

            //在边界拖动时回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//                mDragger.captureChildView(mEdgeTrackerView, pointerId);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll()
    {
        if(mDragger.continueSettling(true))
        {
            invalidate();
        }
    }
}