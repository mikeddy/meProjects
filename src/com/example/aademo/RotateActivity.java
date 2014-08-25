package com.example.aademo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;

public class RotateActivity extends BaseActivity {

	LinearLayout lin_parent;
	//由于Matrix只能旋转图片.所以这里使用setRotation来实现

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rotate_main);
		init();
	}

	private void init() {
		showToast("上一个转盘布局的demo,可以实现touch的手控转动\n在这里只做控件的旋转测试,暂不实现手控转动");
		lin_parent = findView(R.id.rotate_lin);
		lin_parent.post(new Runnable() {
			@Override
			public void run() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							try {
								Thread.sleep(10);
							} catch (Exception e) {
							}
							mHandler.sendEmptyMessage(0);
						}
					}
				}).start();
			}
		});

	}

	int degrees = 0;
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			lin_parent.setRotation(degrees);
			degrees++;
			if(degrees>=360)degrees=0;
		}
	};
}
