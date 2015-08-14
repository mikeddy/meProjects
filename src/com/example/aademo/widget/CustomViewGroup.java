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
	public enum DIRECTION{
		BOTTOM2TOP,TOP2BTTOM
	}
	ViewAdapter mAdapter;
	int mIntVisiableIndex = 0;// 已经弹幕出来的个数
	/**
	 * 这里引用了Queue机制来存储被删掉的ChildView目的是为了重复利用,避免重复创建View对象
	 */
	private Queue<View> mRemovedViewQueue = new LinkedList<View>();
	public static final int MAXCOUNT = 10;//最多能允许的子view个数
	public int defaultMaxCount = MAXCOUNT;
	boolean isStart = false;
	private DIRECTION dir=DIRECTION.BOTTOM2TOP;//默认的滚动方向

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

	/**
	 * 执行动画
	 */
	public void startAnimation() {
		if (isStart) return;//如果动画之前已经执行过,则return否则会重复执行
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

	/**
	 * 添加一个view到此布局中
	 * @param v
	 */
	private void addChildView(View v) {
		LayoutParams params = v.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		}
		addViewInLayout(v, -1, params, true);
		v.measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST));
	}

	/**
	 * 更新每个子view的位置
	 */
	private void reNewPosition() {
		if (mAdapter == null)
			return;
		int childCount = getChildCount();//获取子view的个数
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			MoveBean bean = getMoveBeanFromTag(childView);//获取每个childview中的MoveBean对象
			if (bean != null) {
				int t = reLayout(childView, bean);//重新计算View的新位置,并布局
				if(dir==DIRECTION.BOTTOM2TOP){//从底向顶移,则每次是减一个偏移量
					bean.setPointY(t - bean.getSpeed());
				}else if(dir==DIRECTION.TOP2BTTOM){//从顶向底移,则每次是加一个偏移量
					bean.setPointY(t + bean.getSpeed());
				}
				childView.setTag(R.id.tag_moveitem, bean);
			}
		}
		//如果子view的个数小于默认个数,则进入此方法用来添加一个view
		if (childCount < defaultMaxCount) {
			int viewCount = mAdapter.getViewCount();
			if (mIntVisiableIndex >= viewCount)
				mIntVisiableIndex = 0;
			defaultMaxCount = viewCount < MAXCOUNT ? viewCount : MAXCOUNT;
			View v = mAdapter.getView(mIntVisiableIndex, mRemovedViewQueue.poll(), this);
			addChildView(v);
			int startx=getWidth();
			int starty=0;
			if(dir==DIRECTION.BOTTOM2TOP){
				starty=getBottom();
			}else if(dir==DIRECTION.TOP2BTTOM){
				starty=getTop();
			}
			MoveBean moveBean = new MoveBean(v, startx,starty);
			v.setTag(R.id.tag_moveitem, moveBean);
			reLayout(v, moveBean);
			mIntVisiableIndex++;
		}
	}

	/**
	 * 重新计算一个View的位置
	 * @param childView
	 * @param bean
	 * @return
	 */
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
				boolean needRemove=false;
				if(dir==DIRECTION.BOTTOM2TOP){
					if(child.getBottom() - moveBean.getSpeed() <= 0)needRemove=true;
				}else if(dir==DIRECTION.TOP2BTTOM){
					if(child.getTop()+moveBean.getSpeed()>=getBottom())needRemove=true;
				}
				if (needRemove) {
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

	public void setDir(DIRECTION tDir){
		dir=tDir;
	}

}
