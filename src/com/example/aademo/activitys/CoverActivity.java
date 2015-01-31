package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.impl.ICallBack;
import com.example.aademo.widget.InterceptFrameLayout;

public class CoverActivity extends BaseActivity {
	boolean isOpen = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cover_main);
//		lin_under = (LinearLayout) findViewById(R.id.cover_lvitem_lin_under);
//		lin_up = (LinearLayout) findViewById(R.id.cover_lvitem_lin_up);
//		img_projectIcon = (ImageView) findViewById(R.id.cover_lvitem_img_projecticon);
//		rela_projectIcon = (RelativeLayout) findViewById(R.id.cover_lvitem_rela_projecticon);
//		interceptFrame = (InterceptFrameLayout) findViewById(R.id.cover_frame_itercept);
//		CoverLvItemBean bean = new CoverLvItemBean(getResources());
//		bean.setName1("name1");
//		bean.setAction("action");
//		bean.setName2("name2");
//		bean.setMoney("dis");
		
		final coverItemHolder holder = new coverItemHolder();
		holder.lin_under = (LinearLayout) findViewById(R.id.cover_lvitem_lin_under);
		holder.lin_up = (LinearLayout) findViewById(R.id.cover_lvitem_lin_up);
		holder.img_projectIcon = (ImageView) findViewById(R.id.cover_lvitem_img_projecticon);
		holder.rela_projectIcon = (RelativeLayout) findViewById(R.id.cover_lvitem_rela_projecticon);
		holder.interceptFrame = (InterceptFrameLayout)findViewById(R.id.cover_frame_itercept);
		holder.initHolder();

//		lin_up.post(new Runnable() {
//			@Override
//			public void run() {
//				LinearLayout.LayoutParams p = (LayoutParams) lin_up.getLayoutParams();
//				p.width = (lin_under.getWidth() - rela_projectIcon.getWidth() + img_projectIcon.getLeft());
//				OffsetWidth = p.width;
//				p.leftMargin = -OffsetWidth;
//				lin_up.setLayoutParams(p);
//			}
//		});
		holder.lin_under.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("点击");
			}
		});

		holder.interceptFrame.setCallBack(new ICallBack() {
			@Override
			public void callback(int callCode, Object... param) {
				if (!isOpen()) {
					holder.lin_up.setTranslationX(-holder.OffsetWidth);
					holder.lin_up.animate().translationX(holder.OffsetWidth).setDuration(500);
					setOpen(true);
				} else {
					holder.lin_up.setTranslationX(holder.OffsetWidth);
					holder.lin_up.animate().translationX(-holder.OffsetWidth).setDuration(500);
					setOpen(false);
				}
			}
		});
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	class coverItemHolder {
		LinearLayout lin_under, lin_up;
		InterceptFrameLayout interceptFrame;
		ImageView img_projectIcon;
		RelativeLayout rela_projectIcon;
		TextView tv_name1,tv_name2,tv_action,tv_money,tv_content,tv_lasttime,tv_haveown,tv_joinpersion,tv_plan;
		int OffsetWidth = 0;

		public void initHolder() {
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
		}
	}
}
