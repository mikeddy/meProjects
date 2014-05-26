package com.example.aademo.widget;

import com.example.aademo.impl.ICallBack;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

public class InterceptFrameLayout extends FrameLayout {

	private float mDownX;// 手指按下的X位置
	private float mCurrX;// 手指当前的X位置
	private boolean intercept = false;
	private ICallBack mCallBack;
	int mSlop;

	public InterceptFrameLayout(Context context) {
		super(context);
		init();
	}

	public InterceptFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public InterceptFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setCallBack(ICallBack callBack) {
		mCallBack = callBack;
	}

	private void init() {
		ViewConfiguration vc = ViewConfiguration.get(getContext());
		mSlop = vc.getScaledTouchSlop();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mCurrX = 0;
			mDownX = event.getX();
			intercept = false;
			break;
		case MotionEvent.ACTION_MOVE:
			mCurrX = event.getX();
			float slopX = mCurrX - mDownX;
			if (Math.abs(slopX) > mSlop) {
				intercept = true;
			}
			break;
		}
		return intercept;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE:
			if (intercept && mCallBack != null) {
				mCallBack.callback(1, "");
			}
			break;
		}
		return intercept;
	}
}
