package com.example.aademo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	View slide_view;
	LinearLayout slide_parent;
	private AccelerateDecelerateInterpolator interpolator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_three);
		slide_view = (TextView) findViewById(R.id.tv_m);
		// slide_parent = (LinearLayout) findViewById(R.id.lin_m);
		// final SlideParent sp=new SlideParent();
		// sp.setParentView(slide_parent);
		// sp.setSlideView(slide_view);
		// sp.setCallBackListener(new ICallBack() {
		// @Override
		// public void callback(int callCode, Object... param) {
		// Toast.makeText(MainActivity.this, param[0]+"",
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		// interpolator=new AccelerateDecelerateInterpolator();

		mHandler.sendEmptyMessageDelayed(0, 5000);
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(MainActivity.this, "aaaa", Toast.LENGTH_SHORT).show();
			// Animation
			// animation=AnimationUtils.loadAnimation(MainActivity.this,
			// R.anim.umeng_socialize_slide_out_from_bottom);
			// slide_view.setAnimation(animation);
			// slide_view.startAnimation(animation);
			// Toast.makeText(MainActivity.this, "aaaa",
			// Toast.LENGTH_SHORT).show();
			// ObjectAnimator animY = ObjectAnimator.ofFloat(slide_view, "y",
			// 100f, 300f,200f);
			// ObjectAnimator animX = ObjectAnimator.ofFloat(slide_view, "x",
			// 100f, 300f,200f);
			// ObjectAnimator alphaX = ObjectAnimator.ofFloat(slide_view,
			// "alpha", 0f,0.5f,1f,0f,1f);
			// animY.setDuration(3000);
			// animX.setDuration(2000);
			// alphaX.setDuration(3000);
			// AnimatorSet set=new AnimatorSet();
			// // set.playTogether(animX,animY);
			// set.play(animX).before(animY);
			// set.play(alphaX).after(animY);
			// set.start();
			
			Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.umeng_socialize_slide_out_from_bottom);
			slide_view.startAnimation(animation);

		};
	};
}
