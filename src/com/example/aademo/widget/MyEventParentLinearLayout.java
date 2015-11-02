package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.aademo.util.AppUtils;
import com.example.aademo.util.PalLog;

/**
 * Created by mik_eddy on 15/10/27.
 */
public class MyEventParentLinearLayout extends LinearLayout {
    public MyEventParentLinearLayout(Context context) {
        super(context);
    }

    public MyEventParentLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEventParentLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        PalLog.printD("parent:dispatchTouchEvent==>"+ AppUtils.getEventActionName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        PalLog.printD("parent:onInterceptTouchEvent==>"+ AppUtils.getEventActionName(ev));
        return super.onInterceptTouchEvent(ev);
//        return true;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        PalLog.printD("parent:onTouchEvent==>"+ AppUtils.getEventActionName(ev));
        return super.onTouchEvent(ev);
//        return true;
    }
}
