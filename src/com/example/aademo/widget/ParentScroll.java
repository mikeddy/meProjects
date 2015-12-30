package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by mik_eddy on 15/12/25.
 */
public class ParentScroll extends ScrollView {
    public ParentScroll(Context context) {
        super(context);
    }

    public ParentScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
