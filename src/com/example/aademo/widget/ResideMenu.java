package com.example.aademo.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.aademo.R;
import com.example.aademo.util.PalLog;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * User: special Date: 13-12-10 Time: ������10:44 Mail: specialcyci@gmail.com
 */
public class ResideMenu extends FrameLayout {

	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;

	private static final int PRESSED_MOVE_HORIZANTAL = 2;
	private static final int PRESSED_DOWN = 3;
	private static final int PRESSED_DONE = 4;
	private static final int PRESSED_MOVE_VERTICAL = 5;
	private int pressedState = PRESSED_DOWN;

	public static final int SWIPEDIR_LEFT = 6;// 只有左边有菜单
	public static final int SWIPEDIR_RIGHT = 7;// 只有右边有菜单
	public static final int SWIPEDIR_BOTH = 8;// 两边都有菜单

	private static final int VECTOR_RIGHT = 9;// 如果确定presstate==PRESSED_MOVE_HORIZANTAL后再判断滑动的方向
	private static final int VECTOR_LEFT = 10;// 如果确定presstate==PRESSED_MOVE_HORIZANTAL后再判断滑动的方向
	private static final int VECTOR_NONE = 11;// 如果确定presstate==PRESSED_MOVE_HORIZANTAL后再判断滑动的方向
	private int vectordir = VECTOR_NONE;// 滑动的矢量方向(如果pressedState==PRESSED_MOVE_HORIZANTAL)则此变量起作用

	public static final int EDG_LEFT = 12;// 左边界
	public static final int EDG_RIGHT = 13;// 右边
	public static final int EDG_NONE = 14;// 无边界,说明处于中间位置
	private int edg = EDG_NONE;

	// private ImageView imageViewShadow;
	private ImageView imageViewBackground;
	private LinearLayout layoutLeftMenu;
	private LinearLayout layoutRightMenu;
	private ScrollView scrollViewLeftMenu;
	private ScrollView scrollViewRightMenu;
	private ScrollView scrollViewMenu;
	/** the activity that view attach to */
	private Activity activity;
	/** the decorview of the activity */
	private ViewGroup viewDecor;
	/** the viewgroup of the activity */
	private ResideMenuTouchDisableView viewActivity;
	/** the flag of menu open status 菜单的展开状态 */
	private boolean isOpened;
	// private GestureDetector gestureDetector;
	// 在缩放的时候会有个阴影,这个就是主布局后面的阴影的缩放值
	// private float shadowAdjustScaleX;
	// private float shadowAdjustScaleY;

	/** the view which don't want to intercept touch event */
	private List<View> ignoredViews;
	// private List<ResideMenuItem> leftMenuItems;
	// private List<ResideMenuItem> rightMenuItems;
	private DisplayMetrics displayMetrics = new DisplayMetrics();
	private OnMenuListener menuListener;
	private float lastRawX;
	private boolean isInIgnoredView = false;
	private int scaleDirection = DIRECTION_LEFT;
	private List<Integer> disabledSwipeDirection = new ArrayList<Integer>();
	// valid scale factor is between 0.0f and 1.0f
	// 缩放值(展开菜单的时候,主View会缩放成原来的多大1.0f表示跟原来的一样大,0.0f表示缩放到消失)
	private float mScaleValue = 0.5f;
	private float offsetValue = 0.5f;// 移出屏幕的偏移值,值越大移出屏幕的部分越多
	private float mMenuScaleValue = 0.7f;// 执行动画时,Menu的小缩放值
	private int mSwipeDir = SWIPEDIR_LEFT;
	private VelocityTracker mVelocityTracker;// 手势速率
	private int mMinSlideVelocity;// 最小速率触发值
	private int xOffset = 0, yOffset = 0;

	private View mLeftContent, mRightContent;

	public ResideMenu(Context context) {
		super(context);
		mMinSlideVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity() + ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
		mMinSlideVelocity /= 10;
		initViews(context);
	}

	private void initViews(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.residemenu, this);
		scrollViewLeftMenu = (ScrollView) findViewById(R.id.sv_left_menu);
		scrollViewRightMenu = (ScrollView) findViewById(R.id.sv_right_menu);
		// imageViewShadow = (ImageView) findViewById(R.id.iv_shadow);
		layoutLeftMenu = (LinearLayout) findViewById(R.id.layout_left_menu);
		layoutRightMenu = (LinearLayout) findViewById(R.id.layout_right_menu);
		imageViewBackground = (ImageView) findViewById(R.id.iv_background);
	}

	/**
	 * use the method to set up the activity which residemenu need to show;
	 * 
	 * @param activity
	 */
	public void attachToActivity(Activity activity) {
		initValue(activity);
		// setShadowAdjustScaleXByOrientation();
		viewDecor.addView(this, 0);// 由于==part1==部分执行的代码将activity的根view移除了.所以在这里需要添加上(即将此ResideMenu作为activity的根布局)
		setViewPadding();

	}

	private void initValue(Activity activity) {
		this.activity = activity;
		// leftMenuItems = new ArrayList<ResideMenuItem>();
		// rightMenuItems = new ArrayList<ResideMenuItem>();
		ignoredViews = new ArrayList<View>();
		viewDecor = (ViewGroup) activity.getWindow().getDecorView();
		viewActivity = new ResideMenuTouchDisableView(this.activity);

		// ===part1===
		// 这4行代码相当于将本来通过setContentView设置的activity布局,换到了viewActivity中,并且添加到了这个ResideMenu上
		View mContent = viewDecor.getChildAt(0);// 获取activity的setcontentView的那个布局
		viewDecor.removeViewAt(0);// 移除activity的setContentView的那个布局
		viewActivity.setContent(mContent);// 将activity的布局添加进viewActivity
		addView(viewActivity);// 添加viewActivity
		// ViewGroup parent = (ViewGroup) scrollViewLeftMenu.getParent();
		// parent.removeView(scrollViewLeftMenu);
		// parent.removeView(scrollViewRightMenu);
		scrollViewLeftMenu.setVisibility(View.GONE);
		scrollViewRightMenu.setVisibility(View.GONE);

	}

	/**
	 * set the menu background picture;
	 * 
	 * @param imageResrouce
	 */
	public void setBackground(int imageResrouce) {
		imageViewBackground.setImageResource(imageResrouce);
	}

	public void setLeftMenuContent(View v) {
		layoutLeftMenu.addView(v);
		mLeftContent = v;
		rebuildMenu();
	}

	public void setRightMenuContent(View v) {
		layoutRightMenu.addView(v);
		mRightContent = v;
		rebuildMenu();
	}

	private void rebuildMenu() {
		layoutLeftMenu.removeAllViews();
		layoutRightMenu.removeAllViews();
		if (mLeftContent != null)
			layoutLeftMenu.addView(mLeftContent);
		if (mRightContent != null)
			layoutRightMenu.addView(mRightContent);
	}

	/**
	 * if you need to do something on the action of closing or opening menu, set
	 * the listener here.
	 * 
	 * @return
	 */
	public void setMenuListener(OnMenuListener menuListener) {
		this.menuListener = menuListener;
	}

	public OnMenuListener getMenuListener() {
		return menuListener;
	}

	/**
	 * we need the call the method before the menu show, because the padding of
	 * activity can't get at the moment of onCreateView();
	 */
	private void setViewPadding() {
		this.setPadding(viewActivity.getPaddingLeft(), viewActivity.getPaddingTop(), viewActivity.getPaddingRight(), viewActivity.getPaddingBottom());
	}

	/**
	 * show the reside menu;
	 */
	public void openMenu(int direction) {

		setScaleDirection(direction);

		isOpened = true;
		AnimatorSet scaleDown_activity = buildScaleAnimation(viewActivity, mScaleValue, mScaleValue);
		AnimatorSet scaleUp_menu = buildScaleAnimation(scrollViewMenu, 1.0f, 1.0f);
		// AnimatorSet scaleDown_shadow =
		// buildScaleDownAnimation(imageViewShadow, mScaleValue +
		// shadowAdjustScaleX, mScaleValue + shadowAdjustScaleY);// 对应阴影的缩放动画
		AnimatorSet alpha_menu = buildMenuAnimation(scrollViewMenu, 1.0f);// 对应Menu的选项的动画
		scaleDown_activity.addListener(animationListener);
		// scaleDown_activity.playTogether(scaleDown_shadow);
		scaleDown_activity.playTogether(alpha_menu);
		scaleDown_activity.playTogether(scaleUp_menu);
		scaleDown_activity.start();
	}

	/**
	 * close the reslide menu;
	 */
	public void closeMenu() {

		isOpened = false;
		AnimatorSet scaleUp_activity = buildScaleAnimation(viewActivity, 1.0f, 1.0f);
		AnimatorSet scaleUp_menu = buildScaleAnimation(scrollViewMenu, mMenuScaleValue, mMenuScaleValue);
		// AnimatorSet scaleUp_shadow = buildScaleUpAnimation(imageViewShadow,
		// 1.0f, 1.0f);
		AnimatorSet alpha_menu = buildMenuAnimation(scrollViewMenu, 0.0f);
		scaleUp_activity.addListener(animationListener);
		// scaleUp_activity.playTogether(scaleUp_shadow);
		scaleUp_activity.playTogether(alpha_menu);
		scaleUp_activity.playTogether(scaleUp_menu);
		scaleUp_activity.start();
	}

	@Deprecated
	public void setDirectionDisable(int direction) {
		disabledSwipeDirection.add(direction);
	}

	public void setSwipeDirectionDisable(int direction) {
		disabledSwipeDirection.add(direction);
	}

	private boolean isInDisableDirection(int direction) {
		return disabledSwipeDirection.contains(direction);
	}

	/**
	 * 设置主布局的位置
	 * 
	 * @param direction
	 *            对应有两个状态: DIRECTION_LEF:展开左边菜单的时候 DIRECTION_RIGHT:展开右边菜单的时候
	 */
	private void setScaleDirection(int direction) {
		int screenWidth = getScreenWidth();
		float pivotX;// 即将设置的,控件的中心点X轴位置
		float pivotY = getScreenHeight() * 0.5f;// 即将设置的,控件的中心点Y轴位置(要*0.5f,否则,中心点位置就会在屏幕的最下面)

		if (direction == DIRECTION_LEFT) {
			scrollViewMenu = scrollViewLeftMenu;
			pivotX = screenWidth * (1f + offsetValue);
		} else {
			scrollViewMenu = scrollViewRightMenu;
			pivotX = screenWidth * (0.0f - offsetValue);
		}

		ViewHelper.setPivotX(viewActivity, pivotX);
		ViewHelper.setPivotY(viewActivity, pivotY);// 设置主view的中心到pivotY位置
		// ViewHelper.setPivotX(imageViewShadow, pivotX);//
		// 设置主view后面的阴影的中心到pivotX位置
		// ViewHelper.setPivotY(imageViewShadow, pivotY);
		scaleDirection = direction;
	}

	/**
	 * return the flag of menu status;
	 * 
	 * @return
	 */
	public boolean isOpened() {
		return isOpened;
	}

	private OnClickListener viewActivityOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			if (isOpened())
				closeMenu();
		}
	};

	// 展开菜单or关闭菜单的动画回调监听
	private Animator.AnimatorListener animationListener = new Animator.AnimatorListener() {
		@Override
		public void onAnimationStart(Animator animation) {
			if (isOpened()) {
				showScrollViewMenu();
				if (menuListener != null)
					menuListener.openMenu();
			}
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			// reset the view;
			if (isOpened()) {
				viewActivity.setTouchDisable(true);// 菜单展开之后就不让主view上面的按钮获取到touch,或点击事件了
				viewActivity.setOnClickListener(viewActivityOnClickListener);// 同时给这个主view设置一个监听(当然,监听的作用就是点击这个view的时候关闭菜单)
			} else {
				viewActivity.setTouchDisable(false);// 菜单收回来之后又放开了touch,或点击事件,让这些手势可以透传给下面的子控件
				viewActivity.setOnClickListener(null);// 清除监听
				hideScrollViewMenu();// 菜单收起来之后,将后面的scrollview删除掉
				if (menuListener != null)
					menuListener.closeMenu();
			}
		}

		@Override
		public void onAnimationCancel(Animator animation) {

		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	};

	/**
	 * 获取缩放动画的AnimatorSet a helper method to build scale down animation;
	 * 
	 * @param target
	 *            缩放动画执行在哪个view上
	 * @param targetScaleX
	 *            对应这个view的X方向的缩放值 0.0f-1.0f
	 * @param targetScaleY对应这个view的Y方向的缩放值
	 *            0.0f-1.0f
	 * @return
	 */
	private AnimatorSet buildScaleAnimation(View target, float targetScaleX, float targetScaleY) {

		AnimatorSet scaleDown = new AnimatorSet();
		scaleDown.playTogether(ObjectAnimator.ofFloat(target, "scaleX", targetScaleX), ObjectAnimator.ofFloat(target, "scaleY", targetScaleY));
		scaleDown.setInterpolator(AnimationUtils.loadInterpolator(activity, android.R.anim.decelerate_interpolator));
		scaleDown.setDuration(150);
		return scaleDown;
	}

	private AnimatorSet buildMenuAnimation(View target, float alpha) {

		AnimatorSet alphaAnimation = new AnimatorSet();
		alphaAnimation.playTogether(ObjectAnimator.ofFloat(target, "alpha", alpha));

		alphaAnimation.setDuration(150);
		return alphaAnimation;
	}

	/**
	 * if there ware some view you don't want reside menu to intercept their
	 * touch event,you can use the method to set.
	 * 
	 * @param v
	 */
	public void addIgnoredView(View v) {
		if (!ignoredViews.contains(v))
			ignoredViews.add(v);
	}

	/**
	 * remove the view from ignored view list;
	 * 
	 * @param v
	 */
	public void removeIgnoredView(View v) {
		ignoredViews.remove(v);
	}

	/**
	 * clear the ignored view list;
	 */
	public void clearIgnoredViewList() {
		ignoredViews.clear();
	}

	/**
	 * if the motion evnent was relative to the view which in ignored view
	 * list,return true;
	 * 
	 * @param ev
	 * @return
	 */
	private boolean isInIgnoredView(MotionEvent ev) {
		Rect rect = new Rect();
		for (View v : ignoredViews) {
			v.getGlobalVisibleRect(rect);
			if (rect.contains((int) ev.getX(), (int) ev.getY()))
				return true;
		}
		return false;
	}

	private void setScaleDirectionByRawX(float currentRawX) {
		if (mSwipeDir == SWIPEDIR_LEFT) {
			setScaleDirection(DIRECTION_LEFT);
		} else if (mSwipeDir == SWIPEDIR_RIGHT) {
			setScaleDirection(DIRECTION_RIGHT);
		} else {
			setScaleDirection(currentRawX < lastRawX ? DIRECTION_RIGHT : DIRECTION_LEFT);
		}
	}

	private float getTargetScale(float currentRawX) {
		float scaleFloatX = ((currentRawX - lastRawX) / getScreenWidth()) * 0.25f;// 滑动阻力(值越小,阻力越大)
		scaleFloatX = scaleDirection == DIRECTION_RIGHT ? -scaleFloatX : scaleFloatX;
		float targetScale = ViewHelper.getScaleX(viewActivity) - scaleFloatX;
		targetScale = targetScale > 1.0f ? 1.0f : targetScale;
		targetScale = targetScale < mScaleValue ? mScaleValue : targetScale;
		return targetScale;
	}

	private float lastActionDownX, lastActionDownY;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		float currentActivityScaleX = ViewHelper.getScaleX(viewActivity);
		if (currentActivityScaleX == 1.0f) {
//			PalLog.printD("part0");
			setScaleDirectionByRawX(ev.getRawX());
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastActionDownX = ev.getX();
			lastActionDownY = ev.getY();
			xOffset = 0;
			yOffset = 0;
			isInIgnoredView = isInIgnoredView(ev) && !isOpened();
			pressedState = PRESSED_DOWN;
			vectordir = VECTOR_NONE;
			mVelocityTracker = VelocityTracker.obtain();
			mVelocityTracker.addMovement(ev);
			break;

		case MotionEvent.ACTION_MOVE:
			mVelocityTracker.addMovement(ev);
//			PalLog.printD("part1");
			if (isInIgnoredView || isInDisableDirection(scaleDirection))
				break;
			if (pressedState != PRESSED_DOWN && pressedState != PRESSED_MOVE_HORIZANTAL)
				break;
			if (Interplater()) {
				break;
			}
//			PalLog.printD("part2");
			xOffset = (int) (ev.getX() - lastActionDownX);
			yOffset = (int) (ev.getY() - lastActionDownY);
//			PalLog.printD("part2.1");
			if (pressedState == PRESSED_DOWN) {
//				PalLog.printD("part2.2");
				if (yOffset > 15 || yOffset < -15) {
					pressedState = PRESSED_MOVE_VERTICAL;
					break;
				}
//				PalLog.printD("part2.3" + xOffset);
				if (xOffset < -15 || xOffset > 15) {
//					PalLog.printD("part2.4");
					pressedState = PRESSED_MOVE_HORIZANTAL;
					if (xOffset < -15)
						vectordir = VECTOR_LEFT;
					else if (xOffset > 15)
						vectordir = VECTOR_RIGHT;
					if (Interplater()) {
						break;
					} else {
						ev.setAction(MotionEvent.ACTION_CANCEL);
					}
//					PalLog.printD("part3");
					break;
				}
			} else if (pressedState == PRESSED_MOVE_HORIZANTAL) {
//				PalLog.printD("part4");
				if (currentActivityScaleX < 0.95)
					showScrollViewMenu();

				float targetScale = getTargetScale(ev.getRawX());
				ViewHelper.setScaleX(viewActivity, targetScale);
				ViewHelper.setScaleY(viewActivity, targetScale);
				// ViewHelper.setScaleX(imageViewShadow, targetScale +
				// shadowAdjustScaleX);
				// ViewHelper.setScaleY(imageViewShadow, targetScale +
				// shadowAdjustScaleY);
				if (targetScale <= mScaleValue) {
					ViewHelper.setAlpha(scrollViewMenu, 1.0f);
					ViewHelper.setScaleX(scrollViewMenu, 1.0f);
					ViewHelper.setScaleY(scrollViewMenu, 1.0f);
				} else {
					ViewHelper.setAlpha(scrollViewMenu, (1 - targetScale) * 4.0f);
					ViewHelper.setScaleX(scrollViewMenu, (1 - targetScale) * 5.0f);
					ViewHelper.setScaleY(scrollViewMenu, (1 - targetScale) * 5.0f);
				}
				lastRawX = ev.getRawX();
//				PalLog.printD("part5");
				return true;
			}
			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
//			PalLog.printD("part6");
			if (isInIgnoredView)
				break;
			if (pressedState != PRESSED_MOVE_HORIZANTAL)
				break;
			if (mVelocityTracker == null) {
				break;
			}
			if (Interplater()) {
				return super.dispatchTouchEvent(ev);
			}
//			PalLog.printD("part7");
			mVelocityTracker.addMovement(ev);
			mVelocityTracker.computeCurrentVelocity(1000);
			float velocityX = Math.abs(mVelocityTracker.getXVelocity());
			boolean isDoSwipe = velocityX > mMinSlideVelocity;
			pressedState = PRESSED_DONE;
			if (isOpened()) {
				if (mSwipeDir == SWIPEDIR_LEFT) {
					if ((currentActivityScaleX > mScaleValue || isDoSwipe) && xOffset < 0)
						closeMenu();
					else
						openMenu(scaleDirection);
				} else if (mSwipeDir == SWIPEDIR_RIGHT) {
					if ((currentActivityScaleX > mScaleValue || isDoSwipe) && xOffset > 0)
						closeMenu();
					else
						openMenu(scaleDirection);
				} else {
					if (currentActivityScaleX > mScaleValue || isDoSwipe)
						closeMenu();
					else {
						openMenu(scaleDirection);
					}
				}
			} else {
				if (mSwipeDir == SWIPEDIR_LEFT) {
					if ((currentActivityScaleX < 0.94f || isDoSwipe) && xOffset > 0)
						openMenu(scaleDirection);
					else
						closeMenu();
				} else if (mSwipeDir == SWIPEDIR_RIGHT) {
					if ((currentActivityScaleX < 0.94f || isDoSwipe) && xOffset < 0)
						openMenu(scaleDirection);
					else
						closeMenu();
				} else {
					if (currentActivityScaleX < 0.94f || isDoSwipe) {
						openMenu(scaleDirection);
					} else {
						closeMenu();
					}
				}
			}
			break;

		}
		lastRawX = ev.getRawX();
		boolean aa = super.dispatchTouchEvent(ev);
//		PalLog.printD("boolean " + aa);
		return aa;
	}

	public int getScreenHeight() {
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

	public int getScreenWidth() {
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	// 设置可滑动方向
	public void setSwipeDir(int dir) {
		mSwipeDir = dir;
	}

	public void setScaleValue(float scaleValue) {
		this.mScaleValue = scaleValue;
	}

	public void setOffsetValue(float offsetValue) {
		this.offsetValue = offsetValue;
	}

	public void setEdg(int edg) {
		this.edg = edg;
	}

	public interface OnMenuListener {

		/**
		 * the method will call on the finished time of opening menu's
		 * animation.
		 */
		public void openMenu();

		/**
		 * the method will call on the finished time of closing menu's animation
		 * .
		 */
		public void closeMenu();
	}

	private void showScrollViewMenu() {
		// if (scrollViewMenu != null && scrollViewMenu.getParent() == null) {
		if (scrollViewMenu != null) {
			// addView(scrollViewMenu,1);
			scrollViewMenu.setVisibility(View.VISIBLE);
		}
	}

	private void hideScrollViewMenu() {
		// if (scrollViewMenu != null && scrollViewMenu.getParent() != null) {
		if (scrollViewMenu != null) {
			// removeView(scrollViewMenu);
			scrollViewMenu.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean bb = super.onTouchEvent(event);
//		PalLog.printD("onTouchEvent:" + bb);
		return bb;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean bb = super.onInterceptTouchEvent(ev);
//		PalLog.printD("onInterceptTouchEvent:" + bb);
		return bb;
	}

	public boolean Interplater() {
		if (pressedState == PRESSED_MOVE_HORIZANTAL) {
			if (isOpened())
				return false;
			// 如果到达左边界,手指往左边滑
			else if (edg == EDG_LEFT && vectordir == VECTOR_LEFT) {
				return true;
			} else if (edg == EDG_RIGHT && vectordir == VECTOR_RIGHT) {// 如果到达右边界,手指往右边滑
				return true;
			} else if (edg == EDG_NONE) {
				return true;
			}
		}
		return false;
	}

	// /**
	// * a helper method to build scale up animation;
	// *
	// * @param target
	// * @param targetScaleX
	// * @param targetScaleY
	// * @return
	// */
	// private AnimatorSet buildScaleUpAnimation(View target, float
	// targetScaleX, float targetScaleY) {
	//
	// AnimatorSet scaleUp = new AnimatorSet();
	// scaleUp.playTogether(ObjectAnimator.ofFloat(target, "scaleX",
	// targetScaleX), ObjectAnimator.ofFloat(target, "scaleY", targetScaleY));
	// scaleUp.setInterpolator(AnimationUtils.loadInterpolator(activity,
	// android.R.anim.decelerate_interpolator));
	// scaleUp.setDuration(150);
	// return scaleUp;
	// }

	// private AnimatorSet buildMenuScaleUpAnimation(View target, float
	// targetScaleX, float targetScaleY) {
	//
	// AnimatorSet scaleUp = new AnimatorSet();
	// scaleUp.playTogether(ObjectAnimator.ofFloat(target, "scaleX",
	// targetScaleX), ObjectAnimator.ofFloat(target, "scaleY", targetScaleY));
	// scaleUp.setInterpolator(AnimationUtils.loadInterpolator(activity,
	// android.R.anim.decelerate_interpolator));
	// scaleUp.setDuration(150);
	// return scaleUp;
	// }

	// 根据屏幕方向,决定滑动时的阴影缩放值
	// private void setShadowAdjustScaleXByOrientation() {
	// int orientation = getResources().getConfiguration().orientation;
	// if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
	// shadowAdjustScaleX = 0.034f;
	// shadowAdjustScaleY = 0.12f;
	// } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
	// shadowAdjustScaleX = 0.06f;
	// shadowAdjustScaleY = 0.07f;
	// }
	// }

	/**
	 * the visiblity of shadow under the activity view;
	 * 
	 * @param isVisible
	 */
	// public void setShadowVisible(boolean isVisible) {
	// if (isVisible)
	// imageViewShadow.setImageResource(R.drawable.shadow);
	// else
	// imageViewShadow.setImageBitmap(null);
	// }

	// /**
	// * add a single items to left menu;
	// *
	// * @param menuItem
	// */
	// @Deprecated
	// public void addMenuItem(ResideMenuItem menuItem) {
	// this.leftMenuItems.add(menuItem);
	// layoutLeftMenu.addView(menuItem);
	// }
	//
	// /**
	// * add a single items;
	// *
	// * @param menuItem
	// * @param direction
	// */
	// public void addMenuItem(ResideMenuItem menuItem, int direction) {
	// if (direction == DIRECTION_LEFT) {
	// this.leftMenuItems.add(menuItem);
	// layoutLeftMenu.addView(menuItem);
	// } else {
	// this.rightMenuItems.add(menuItem);
	// layoutRightMenu.addView(menuItem);
	// }
	// }

	/**
	 * set the menu items by array list to left menu;
	 * 
	 * @param menuItems
	 */
	// @Deprecated
	// public void setMenuItems(List<ResideMenuItem> menuItems) {
	// this.leftMenuItems = menuItems;
	// rebuildMenu();
	// }
	//
	// /**
	// * set the menu items by array list;
	// *
	// * @param menuItems
	// * @param direction
	// */
	// public void setMenuItems(List<ResideMenuItem> menuItems, int direction) {
	// if (direction == DIRECTION_LEFT)
	// this.leftMenuItems = menuItems;
	// else
	// this.rightMenuItems = menuItems;
	// rebuildMenu();
	// }

	// /**
	// * get the left menu items;
	// *
	// * @return
	// */
	// @Deprecated
	// public List<ResideMenuItem> getMenuItems() {
	// return leftMenuItems;
	// }
	//
	// /**
	// * get the menu items;
	// *
	// * @return
	// */
	// public List<ResideMenuItem> getMenuItems(int direction) {
	// if (direction == DIRECTION_LEFT)
	// return leftMenuItems;
	// else
	// return rightMenuItems;
	// }
}
