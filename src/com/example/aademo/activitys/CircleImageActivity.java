package com.example.aademo.activitys;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;

public class CircleImageActivity extends BaseActivity {
	Button btn_ic;

	//v4.1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circleimage_main);
		init();
	}

	private void init(){
		btn_ic=(Button)findViewById(R.id.circleimage_btn_ic);
		btn_ic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("我是按钮");
			}
		});
//		setGeneralStateDrawable();
	}


	/**
	 * 使用代码来设置selector
	 */
	private void setGeneralStateDrawable(){
		Drawable drawable=getResources().getDrawable(R.drawable.ic_launcher);//normal
		Drawable selectorDrawable=getResources().getDrawable(R.drawable.icon_calendar);//selector
//		ColorFilter selectorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//		selectorDrawable.setColorFilter(selectorFilter);
		//初始化一个空对象
		StateListDrawable stalistDrawable = new StateListDrawable();
		//获取对应的属性值 Android框架自带的属性 attr
		int pressed = android.R.attr.state_pressed;
		int window_focused = android.R.attr.state_window_focused;
		int focused = android.R.attr.state_focused;
		int selected = android.R.attr.state_selected;
		int enable=android.R.attr.state_enabled;
		stalistDrawable.addState(new int []{pressed}, selectorDrawable);
		stalistDrawable.addState(new int []{focused}, selectorDrawable);
		stalistDrawable.addState(new int []{enable}, drawable);
		//此值前面有个"-"表示属性为false
//		stalistDrawable.addState(new int []{pressed , -focused}, getResources().getDrawable(R.drawable.pic2);
		//没有任何状态时显示的图片，我们给它设置我空集合
		stalistDrawable.addState(new int []{}, drawable);
		btn_ic.setBackgroundDrawable(stalistDrawable);
	}
}
