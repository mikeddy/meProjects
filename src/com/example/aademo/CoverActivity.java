package com.example.aademo;

import com.example.aademo.bean.CoverLvItemBean;
import com.example.aademo.impl.ICallBack;
import com.example.aademo.widget.InterceptFrameLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class CoverActivity extends BaseActivity {
	LinearLayout lin_under, lin_up;
	InterceptFrameLayout interceptFrame;
	ImageView img_projectIcon;
	RelativeLayout rela_projectIcon;
	boolean isOpen = false;
	TextView tv_title;
	int OffsetWidth = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cover_main);
		lin_under = (LinearLayout) findViewById(R.id.cover_lvitem_lin_under);
		lin_up = (LinearLayout) findViewById(R.id.cover_lvitem_lin_up);
		img_projectIcon = (ImageView) findViewById(R.id.cover_lvitem_img_projecticon);
		rela_projectIcon = (RelativeLayout) findViewById(R.id.cover_lvitem_rela_projecticon);
		interceptFrame = (InterceptFrameLayout) findViewById(R.id.cover_frame_itercept);
		// tv_title=(TextView)findViewById(R.id.cover_lvitem_tv_);
		CoverLvItemBean bean = new CoverLvItemBean(getResources());
		bean.setName1("name1");
		bean.setAction("action");
		bean.setName2("name2");
		bean.setMoney("dis");

		lin_up.post(new Runnable() {
			@Override
			public void run() {
				LinearLayout.LayoutParams p = (LayoutParams) lin_up.getLayoutParams();
				p.width = (lin_under.getWidth() - rela_projectIcon.getWidth() + img_projectIcon.getLeft());
				OffsetWidth = p.width;
				p.leftMargin = -OffsetWidth;
				lin_up.setLayoutParams(p);
			}
		});

		lin_under.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("点击事件");
			}
		});

		interceptFrame.setCallBack(new ICallBack() {
			@Override
			public void callback(int callCode, Object... param) {
				mHandler.sendEmptyMessage(1);
			}
		});
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			showToast("滑动事件");
			if (!isOpen) {
				lin_up.animate().translationX(OffsetWidth).setDuration(500);
				isOpen = true;
			} else {
				lin_up.animate().translationX(-OffsetWidth).setDuration(500);
				isOpen = false;
			}
		}
	};
}
