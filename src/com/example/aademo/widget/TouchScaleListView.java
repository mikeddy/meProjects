package com.example.aademo.widget;

import com.example.aademo.impl.ICallBack;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ListView;

public class TouchScaleListView extends ListView {

	ICallBack mCallBack;
	private float mLastY = -1; // save event y
	private View firstChildView;
	boolean isOpenScale = false,enabletouch=true;

	/** 阻尼系数,越小阻力就越大. */
	private static final float SCROLL_RATIO = 0.1f;
	// 缩放最大系数0.5(50就代表0.5)
	private static final float MAX_SCALE = 50;
	public static final int ACTION_MOVEINTOP = 1, ACTION_UP = 2;

	public TouchScaleListView(Context context) {
		super(context);
	}

	public TouchScaleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TouchScaleListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setCallBack(ICallBack callback) {
		mCallBack = callback;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = ev.getRawY();
			firstChildView = getChildAt(0);
			isOpenScale = false;
			enabletouch=true;
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = ev.getRawY() - mLastY;
			if (getFirstVisiblePosition() == 0 && deltaY > 0 && !isOpenScale) {
				if (firstChildView != null && firstChildView.getTop() >= 0 && mCallBack != null) {
					isOpenScale = true;
					enabletouch=false;
					mLastY = ev.getRawY();
				}
			}
			if (isOpenScale){
				float fScale = (deltaY * SCROLL_RATIO * 0.5f);
				if(fScale<=0){
					fScale=0;
//					enabletouch=true;
				}
				mCallBack.callback(ACTION_MOVEINTOP, "" + (fScale >MAX_SCALE ?MAX_SCALE: fScale));
			}
				
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE:
			isOpenScale = false;
			enabletouch=true;
			if (mCallBack != null)
				mCallBack.callback(ACTION_UP, "");
			break;
		}
		return enabletouch&&super.onTouchEvent(ev);
	}
}
