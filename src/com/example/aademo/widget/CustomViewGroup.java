package com.example.aademo.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.aademo.R;
import com.example.aademo.bean.MoveBean;
import com.example.aademo.impl.ViewAdapter;

import java.util.LinkedList;
import java.util.Queue;

public class CustomViewGroup extends ViewGroup {
	ViewAdapter mAdapter;
	int TopCount = 0;// 已经弹幕出来的个数
	private Queue<View> mRemovedViewQueue = new LinkedList<View>();
	public static final int MAXCOUNT = 10;
	public int defaultMaxCount = MAXCOUNT;
	boolean isStart = false;

	public CustomViewGroup(Context context) {
		super(context);
	}

	public CustomViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		reNewPosition();
		removeNonVisibleItems();
	}

	public void startAnimation() {
		if (isStart) return;
		isStart = true;
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					mHandler.sendEmptyMessage(0);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
			}
		});

		t.start();
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			requestLayout();
		};
	};

	private void addChildView(View v) {
		LayoutParams params = v.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		}
		addViewInLayout(v, -1, params, true);
		v.measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST));
	}

	private void reNewPosition() {
		if (mAdapter == null)
			return;
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			MoveBean bean = getMoveBeanFromTag(childView);
			if (bean != null) {
				int t = reLayout(childView, bean);
				bean.setPointY(t - bean.getSpeed());
				childView.setTag(R.id.tag_moveitem, bean);
			}
		}
		if (childCount < defaultMaxCount) {
			int viewCount = mAdapter.getViewCount();
			if (TopCount >= viewCount)
				TopCount = 0;
			defaultMaxCount = viewCount < MAXCOUNT ? viewCount : MAXCOUNT;
			View v = mAdapter.getView(TopCount, mRemovedViewQueue.poll(), this);
			addChildView(v);
			MoveBean moveBean = new MoveBean(v, getWidth(), getHeight());
			v.setTag(R.id.tag_moveitem, moveBean);
			reLayout(v, moveBean);
			TopCount++;
		}
	}

	private int reLayout(View childView, MoveBean bean) {
		int l = bean.getPotinX();
		int t = bean.getPointY();
		int r = bean.getPotinX() + childView.getMeasuredWidth();
		int b = bean.getPointY() + childView.getMeasuredHeight();
		childView.layout(l, t, r, b);
		return t;
	}

	private void removeNonVisibleItems() {
		if (mAdapter == null)
			return;
		int startIndex = getChildCount() - 1;
		while (startIndex > 0) {
			View child = getChildAt(startIndex);
			MoveBean moveBean = getMoveBeanFromTag(child);
			if (moveBean != null) {
				if (child.getBottom() - moveBean.getSpeed() <= 0) {
					removeViewInLayout(child);
					mRemovedViewQueue.offer(child);
				}
			}
			startIndex--;
		}
	}

	private MoveBean getMoveBeanFromTag(View v) {
		Object object = v.getTag(R.id.tag_moveitem);
		MoveBean bean = null;
		if (object != null)
			bean = (MoveBean) object;
		return bean;
	}

	public void setAdapter(ViewAdapter adapter) {
		mAdapter = adapter;
	}

}
