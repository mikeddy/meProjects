package com.example.aademo.widget;

import static com.nineoldandroids.view.ViewHelper.setTranslationX;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;

import com.example.aademo.impl.ICallBack;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;

public class SlideParent {
	
	/**
	 * 滑动按钮控件用法见SlideParent_Activity
	 */
	View slideView;
	View parentView;
	
	ICallBack mCallBack;
	
	boolean isOpen = false;
//	private int mSlop;//
	private int mMinSlideVelocity;//最小速率触发值
	int slopViewWidth = 0, parentViewWidth = 0;// slopViewWidth滑块的宽度,parentViewWidth父容器的宽度
	int leftEdgX, rightEdgX; // 左边界,右边接

	private int mDownX;// 手指按下的X位置
	private int mCurrX;// 手指当前的X位置
	private int middleX;// 中间线,当手抬起的时候如果X坐标在中间线左边,则向左滑,如果在右边则向右滑
	private int currentSlopX;// 开关当前滑动到的x坐标
	private int slopViewTouchOffsetX;// 初次触摸时手触摸到的滑块的位置距离滑块的左边界偏移值
	private boolean isSlide = false;// 是否滑动了
	private static final int MINSLOP = 5;// 最小触发滑动值
	private VelocityTracker mVelocityTracker;

	public void setParentView(View v){
		parentView=v;
	}
	public void setSlideView(View v){
		slideView=v;
	}
	
	public void  setCallBackListener(ICallBack callback){
		mCallBack=callback;
	}
	
	public void init(final Context context){
		slopViewWidth = slideView.getWidth();
		parentViewWidth = parentView.getWidth();
		//计算最小触发滑动速率
		mMinSlideVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity()+ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
		mMinSlideVelocity/=10;
		leftEdgX = 0;
		rightEdgX = parentViewWidth - slopViewWidth;
		middleX = (rightEdgX - leftEdgX) / 2;
		parentView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					currentSlopX = 0;
					slopViewTouchOffsetX = 0;
					isSlide = false;
					mCurrX=0;
					mDownX = (int) event.getX();
					// 如果触摸的位置在滑块的范围内,计算触摸位置与滑块左边界的偏移量
					if (!isOpen && mDownX <= leftEdgX + slopViewWidth) {
						slopViewTouchOffsetX = mDownX - leftEdgX;
					} else if (isOpen && mDownX >= rightEdgX - slopViewWidth) {
						slopViewTouchOffsetX = mDownX - rightEdgX;
//						 Log.v("tag2222",mDownX+"==="+rightEdgX+"==="+slopViewWidth);
					}
					mVelocityTracker = VelocityTracker.obtain();
					mVelocityTracker.addMovement(event);
					break;
				case MotionEvent.ACTION_MOVE:
					mCurrX = (int) event.getX();// 当前手指的位置
					int slopX = mCurrX - mDownX;
					if (Math.abs(slopX) > MINSLOP)
						isSlide = true;
					if (isSlide) {
						currentSlopX = mDownX + slopX - slopViewTouchOffsetX;
						if (currentSlopX < leftEdgX)currentSlopX = leftEdgX;
						if (currentSlopX > rightEdgX)currentSlopX = rightEdgX;
						setTranslationX(slideView, currentSlopX);
					}
					mVelocityTracker.addMovement(event);
					break;
				case MotionEvent.ACTION_OUTSIDE:
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_CANCEL:
					if (mVelocityTracker == null) {
						break;
					}
					mVelocityTracker.addMovement(event);
					mVelocityTracker.computeCurrentVelocity(1000);
					float velocityX = Math.abs(mVelocityTracker.getXVelocity());
//					Toast.makeText(context, velocityX+"==="+mMinSlideVelocity, Toast.LENGTH_SHORT).show();
//					Log.v("tag2222",velocityX+"==="+mMinSlideVelocity);
					if(velocityX>mMinSlideVelocity&&isSlide){
						isOpen=!isOpen;
					}else if(isSlide){
					isOpen = currentSlopX > middleX;
					}else {
						isOpen=!isOpen;
					}
					// 如果滑块处在middlex左边,则向左边界滑动,否则右滑
					int edg = isOpen ? rightEdgX : leftEdgX;
					animate(slideView).translationX(edg).setDuration(200).setListener(new AnimatorListener() {
						@Override
						public void onAnimationStart(Animator arg0) {
						}
						
						@Override
						public void onAnimationRepeat(Animator arg0) {
						}
						
						@Override
						public void onAnimationEnd(Animator arg0) {
							if(mCallBack!=null)mCallBack.callback(0, isOpen);
						}
						
						@Override
						public void onAnimationCancel(Animator arg0) {
						}
					});
					break;
					default:
						
						break;
				}
				return true;
			}
		});
	}
}
