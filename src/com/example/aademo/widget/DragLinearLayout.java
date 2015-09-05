package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by mik_eddy on 15/9/2.
 */
public class DragLinearLayout extends LinearLayout {

    private OverScroller mScroller;
    private float mFloatLastY;//最后一次获取到的Y坐标
    private int mTouchSlop;//最小滑动触发阀值
    private boolean mBoolDragging = false;//是否处在拖动状态

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
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mFloatLastY=y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mFloatLastY;
                if(!mBoolDragging&&Math.abs(dy) > mTouchSlop){
                    mBoolDragging=true;
                }
                if (mBoolDragging) {
                    int nIntOffset=(int) -dy;
//                    PalLog.printD("getScrollY():" + getScrollY() + " height:" + getHeight() + "  dy:" + nIntOffset);
                    int nIntScrollY=getScrollY();
                    //只有当滑动位置在0-控件高度之间才允许滑动.否则就会滑过头
                    if((nIntScrollY>0&&nIntOffset<0)||(nIntScrollY<getHeight()&&nIntOffset>0)){
                        if(nIntOffset<0&&nIntScrollY+nIntOffset<0)nIntOffset=-nIntScrollY;
                        else if(nIntOffset>0&&nIntScrollY+nIntOffset>getHeight())nIntOffset=getHeight()-nIntScrollY;
                        scrollBy(0, nIntOffset);
                    }
                    mFloatLastY = y;//只有在mBoolDragging==true.即滑动状态时才计算新的偏移
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mBoolDragging=false;
                break;

        }
        return super.onTouchEvent(event);
    }
}
