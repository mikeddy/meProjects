package com.example.aademo.widget;

import com.example.aademo.R;
import com.example.aademo.util.PalLog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class StickListView extends ListView {
	private Callbacks mCallbacks;
	View headView;
	View firstItemView;

	public StickListView(Context context) {
		super(context);
	}

	public StickListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public StickListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mCallbacks != null) {
			int firstVisiablePosition = getFirstVisiblePosition();
			View v = getChildAt(0);
			View itemView = null;
			int id = v.getId();
			if (id != R.id.stick_item && headView == null) {
				headView = v;
			} else if (id == R.id.stick_item) {
				itemView = v;
			}
			if (headView != null) {
				int top = itemView == null ? 0 : -itemView.getTop();
				int scrolly = -headView.getTop() + top + getPaddingTop() + (firstVisiablePosition >= 1 ? firstVisiablePosition - 1 : firstVisiablePosition) * headView.getHeight();
				PalLog.printD(scrolly + "=====" + headView.getTop());
				mCallbacks.onScrollChanged(scrolly);
			}
		}
	}

	public void setCallbacks(Callbacks listener) {
		mCallbacks = listener;
	}

	@Override
	public int computeVerticalScrollRange() {
		return super.computeVerticalScrollRange();
	}

	public static interface Callbacks {
		public void onScrollChanged(int scrollY);

		public void onDownMotionEvent();

		public void onUpOrCancelMotionEvent();
	}
}
