package com.example.aademo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aademo.widget.StickListView;
import com.example.aademo.widget.StickListView.Callbacks;
import com.nineoldandroids.view.ViewHelper;

public class StickyActivity extends BaseActivity implements Callbacks {
	StickListView lv_main;
	View v_head;
	TextView tv_item2, tv_item3, tv_item4;
	int marginTop2 = 0, marginTop3 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stick_main);
		init();
	}

	private void init() {
		lv_main = (StickListView) findViewById(R.id.stick_lv);
		v_head = getLayoutInflater().inflate(R.layout.stick_head, null);
		tv_item2 = (TextView) v_head.findViewById(R.id.stick_item_tv_2);
		tv_item3 = (TextView) v_head.findViewById(R.id.stick_item_tv_3);

		tv_item4 = (TextView) findViewById(R.id.stick_item_tv_4);

		lv_main.addHeaderView(v_head);
		lv_main.setAdapter(new StickAdapter());

		lv_main.setCallbacks(this);
		lv_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				onScrollChanged(lv_main.getScrollY());
			}
		});

		tv_item3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				marginTop3 = tv_item3.getTop();
				ViewHelper.setTranslationY(tv_item4, marginTop3);
				// PalLog.printD("aaaa===" + marginTop3);
			}
		});

		tv_item2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				marginTop2 = tv_item2.getTop();
			}
		});
	}

	class StickAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 50;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.stick_item, null);
			}
			return convertView;
		}

	}

	@Override
	public void onScrollChanged(int scrollY) {
		int transY3 = marginTop3 - scrollY;
		ViewHelper.setTranslationY(tv_item4, transY3 > 0 ? transY3 : 0);

		int transY2 = marginTop2 - scrollY;
		ViewHelper.setTranslationY(tv_item2, transY2 > 0 ? 0 : -transY2);
	}

	@Override
	public void onDownMotionEvent() {
	}

	@Override
	public void onUpOrCancelMotionEvent() {
	}
}
