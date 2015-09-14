package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.aademo.util.AppUtils;
import com.example.aademo.util.PalLog;

/**
 * Created by mik_eddy on 15/9/9.
 */
public class HorizontalScrollLinearlayout extends LinearLayout {

//    private float mFloatLastX;//最后一次获取到的X坐标
//    private float mFloatLastY;//最后一次获取到得Y坐标
//    private int mTouchSlop;//最小滑动触发阀值
//    private boolean mBoolDragging = false;//是否处在拖动状态
//    private boolean mBoolInControl = true;//默认让横向滑动控件先获取事件处理权,如果发现用户实际想滑动的是listview,再把事件给listview

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
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        PalLog.printE("child==>dispatchTouchEvent==>"+ AppUtils.getEventActionName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        PalLog.printE("child==>onInterceptTouchEvent==>"+ AppUtils.getEventActionName(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        PalLog.printE("child==>onTouchEvent==>"+ AppUtils.getEventActionName(ev));
        return super.onTouchEvent(ev);
//        final int action = ev.getAction();
//        float x = ev.getX();
//        float y=ev.getY();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mFloatLastX = x;
//                mFloatLastY=y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float dx = x - mFloatLastX;
//                float dy=y-mFloatLastY;
//                PalLog.printD("bbbb" + mBoolDragging + "   " + dx + "    " + mTouchSlop);
//                if (Math.abs(dy) > mTouchSlop&&Math.abs(dy)>Math.abs(dx)) {
////                    mBoolDragging = true;
//                    mBoolInControl=false;
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
////                mBoolDragging = false;
//                break;
//        }
//        requestDisallowInterceptTouchEvent(mBoolInControl);
//        return mBoolInControl;
    }
}
