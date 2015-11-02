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
public class MyEventChildLinearLayout extends LinearLayout {
    public MyEventChildLinearLayout(Context context) {
        super(context);
    }

    public MyEventChildLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEventChildLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        PalLog.printD("child:dispatchTouchEvent==>" + AppUtils.getEventActionName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        PalLog.printD("child:onInterceptTouchEvent==>" + AppUtils.getEventActionName(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PalLog.printD("child:onTouchEvent==>" + AppUtils.getEventActionName(event));
        return true;
//        return super.onTouchEvent(event);
    }
}
