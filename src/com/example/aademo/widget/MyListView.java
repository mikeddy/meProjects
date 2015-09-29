package com.example.aademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ListView;

import com.example.aademo.util.AppUtils;
import com.example.aademo.util.PalLog;

import java.util.ArrayList;

/**
 * Created by mik_eddy on 15/9/9.
 */
public class MyListView extends ListView {
    public static final int DIR_DEFAULT = 1, DIR_VERTICAL = 2, DIR_HORIZONTAL = 3;
    ArrayList<HorizontalScrollLinearlayout> arrayList_mHslin;
    private float mFloatLastX;//最后一次获取到的X坐标
    private float mFloatLastY;//最后一次获取到得Y坐标
    private int mTouchSlop;//最小滑动触发阀值
    private int mDir = DIR_DEFAULT;//滑动方向.初始状态为默认值


    public MyListView(Context context) {
        super(context);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        PalLog.printE("parent==>dispatchTouchEvent==>" + AppUtils.getEventActionName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        PalLog.printE("parent==>onInterceptTouchEvent==>" + AppUtils.getEventActionName(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        PalLog.printE("parent==>onTouchEvent==>" + AppUtils.getEventActionName(ev));
        final int action = ev.getAction();
        float y = ev.getY();
        float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFloatLastX = x;
                mFloatLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mFloatLastY;
                float dx = x - mFloatLastX;
                if (mDir == DIR_DEFAULT) {
                    float absDy = Math.abs(dy);
                    float absDx = Math.abs(dx);
                    float absMax = Math.max(absDx, absDy);
                    if (absMax > mTouchSlop) {
                        mDir = absMax == absDx ? DIR_HORIZONTAL : DIR_VERTICAL;
                    }
                    if(mDir==DIR_HORIZONTAL)doChildScroll(ev,true);
                }
                if (mDir == DIR_HORIZONTAL) {
                    doChildScroll(ev,false);
                    return true;
                } else if (mDir == DIR_VERTICAL) {

                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDir = DIR_DEFAULT;
                reSetChild();
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void doChildScroll(MotionEvent ev,boolean isFirst) {
        for (int i = 0; i < getArrayList_mHslin().size(); i++) {
            if(isFirst){
                ev=MotionEvent.obtain(ev);
                ev.setAction(MotionEvent.ACTION_DOWN);
            }
            getArrayList_mHslin().get(i).doScroll(ev);
        }
    }

    public void reSetChild() {
        for (int i = 0; i < getArrayList_mHslin().size(); i++) {
            getArrayList_mHslin().get(i).reSet();
        }
    }


//    public View getChildInTouchRect(MotionEvent event) {
//        Rect rect = new Rect();
//        int childCount = getChildCount();
//        int[] listViewCoords = new int[2];
//        getLocationOnScreen(listViewCoords);
//        int x = (int) event.getRawX() - listViewCoords[0];
//        int y = (int) event.getRawY() - listViewCoords[1];
//        View child;
//        for (int i = 0; i < childCount; i++) {
//            child = getChildAt(i);
//            child.getHitRect(rect);
//            if (rect.contains(x, y)) {
//                return child;
//            }
//        }
//        return null;
//    }

    public ArrayList<HorizontalScrollLinearlayout> getArrayList_mHslin() {
        if (arrayList_mHslin == null) arrayList_mHslin = new ArrayList<HorizontalScrollLinearlayout>();
        return arrayList_mHslin;
    }
}
