package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.example.aademo.util.PalLog;

/**
 * Created by mik_eddy on 15/9/2.
 */
public class DragLinearLayout extends LinearLayout {

    private OverScroller mScroller;
    private float mFloatLastY;//最后一次获取到的Y坐标
    private int mTouchSlop;//最小滑动触发阀值
    private boolean mBoolDragging = false;//是否处在拖动状态
    private int mMaximumVelocity;//最大手势速率
    private int mMinimumVelocity = 4000;//最小触发滚屏手势速率
    private VelocityTracker mVelocityTracker;

    public DragLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public DragLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        PalLog.printD("parent==>dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        final int action = ev.getAction();
//        float y = ev.getY();
//        PalLog.printD("parent==>onInterceptTouchEvent");
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mFloatLastY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float dy = y - mFloatLastY;
////                PalLog.printD("bbbb"+mBoolDragging+"   "+dy+"    "+mTouchSlop);
//                if (Math.abs(dy) > mTouchSlop) {
//                    mBoolDragging = true;
////                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                mBoolDragging = false;
//                break;
//        }
        return true;
//        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        getVelocityTracker().addMovement(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mFloatLastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mFloatLastY;
                if (!mBoolDragging && Math.abs(dy) > mTouchSlop) {
                    mBoolDragging = true;
                }
                if (mBoolDragging) {
                    int nIntOffset = (int) -dy;
                    scrollBy(0, nIntOffset);
                    mFloatLastY = y;//只有在mBoolDragging==true.即滑动状态时才计算新的偏移
                }
                PalLog.printD("scroll:" + getScrollY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mBoolDragging = false;
                getVelocityTracker().computeCurrentVelocity(1000, mMaximumVelocity);
                int velocityY = (int) mVelocityTracker.getYVelocity();
                //根据速率来判断应该滑到顶部还是底部
                if (Math.abs(velocityY) > mMinimumVelocity) {
                    boolean toTop = velocityY > 0;
                    scrollToTop(toTop);
                } else {//根据已经滑动的距离来判断
                    int scrollY = getScrollY();
                    //当距离底部距离>距离顶部距离的时候:向顶部滑动,反之向底部滑
                    scrollToTop(getHeight() - scrollY > scrollY - 0);
                }
                recycleVelocityTracker();
                break;

        }
        return super.onTouchEvent(event);
    }

    /**
     * 控制整体滑动到顶部
     *
     * @param nBooltoTop true:滑动到顶部 false:滑动到底部
     */
    private void scrollToTop(boolean nBooltoTop) {
        int nIntOffset;
        if (nBooltoTop) {
            nIntOffset=0 - getScrollY();
        } else {
            nIntOffset=getHeight() - getScrollY();
        }
        int nIntDuration=Math.abs(nIntOffset/2);
        mScroller.startScroll(0, getScrollY(), 0, nIntOffset, nIntDuration);
        invalidate();
    }

    /**
     * 重写scrollTo防止滑过头
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > getHeight()) {
            y = getHeight();
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
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
