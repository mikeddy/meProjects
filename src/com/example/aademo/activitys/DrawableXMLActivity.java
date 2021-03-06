package com.example.aademo.activitys;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.aademo.R;

public class DrawableXMLActivity extends BaseActivity {
	ImageView imgView,imgView_2_3,imgView_3_2,imgView_3_3,imgView_4_1,imgView_4_2;

	ClipDrawable mClipDrawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawablexmlmain);
		init();
	}

	private void init() {
		imgView = (ImageView) findViewById(R.id.img_item_2_2); 
		imgView_2_3=(ImageView)findViewById(R.id.img_item_2_3);
		imgView_3_2=(ImageView)findViewById(R.id.img_item_3_2);
		imgView_3_3=(ImageView)findViewById(R.id.img_item_3_3);
		imgView_4_1=(ImageView)findViewById(R.id.img_item_4_1);
		imgView_4_2=(ImageView)findViewById(R.id.img_item_4_2);

//		new MyThread().start();
		AnimationDrawable frameAnimation = (AnimationDrawable)imgView_2_3.getDrawable();
		frameAnimation.start();
		
		AnimationDrawable frameAnimation3_2 = (AnimationDrawable)imgView_3_2.getDrawable();
		frameAnimation3_2.start();

		TransitionDrawable frameAnimation4_1 = (TransitionDrawable)imgView_4_1.getDrawable();
		frameAnimation4_1.startTransition(5000);

		mClipDrawable=(ClipDrawable)imgView_4_2.getDrawable();

		mRepeatHander.sendEmptyMessage(0);
		mRepeatHander1.sendEmptyMessage(0);

	}


	Handler mRepeatHander1 = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if(mClipDrawable.getLevel()>=1000)mClipDrawable.setLevel(0);
			mClipDrawable.setLevel(mClipDrawable.getLevel() + 20);
			mRepeatHander1.sendEmptyMessageDelayed(0, 5);
		};
	};


	Handler mRepeatHander = new Handler() {
		public void handleMessage(android.os.Message msg) {
			TransitionDrawable drawable = (TransitionDrawable) imgView.getDrawable();
			drawable.startTransition(2000);

			mRepeatHander.sendEmptyMessageDelayed(0, 2000);
		};
	};

//	class MyThread extends Thread {
//		@Override
//		public void run() {
//			super.run();
//			while (true) {
//				try {
//					mRepeatHander.sendEmptyMessage(0);
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
