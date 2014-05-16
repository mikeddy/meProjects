package com.example.aademo;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aademo.impl.ICallBack;
import com.example.aademo.widget.SlideParent;

public class SlideParent_Activity extends BaseActivity {

	View slide_view;
	LinearLayout slide_parent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slideparent_main);
		slide_view = (TextView) findViewById(R.id.tv_slideview);
		slide_parent = (LinearLayout) findViewById(R.id.lin_slideparent);
		final SlideParent sp = new SlideParent();
		sp.setParentView(slide_parent);
		sp.setSlideView(slide_view);
		sp.setCallBackListener(new ICallBack() {
			@Override
			public void callback(int callCode, Object... param) {
				showToast(param[0] + "");
			}
		});
		slide_view.post(new Runnable() {
			@Override
			public void run() {
				sp.init(mContext);
			}
		});
	}
}
