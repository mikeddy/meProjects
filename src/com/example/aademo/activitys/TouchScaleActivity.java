package com.example.aademo.activitys;

import com.example.aademo.R;
import com.example.aademo.impl.ICallBack;
import com.example.aademo.widget.TouchScaleListView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 下拉放大效果
 * 
 * @author mik_eddy
 * 
 */
public class TouchScaleActivity extends BaseActivity {

	TouchScaleListView lv;
	ImageView headImg;
	View headView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touchscale_main);
		init();
		setListener();
	}

	int count=0;
	private void setListener() {
		lv.setCallBack(new ICallBack() {
			@Override
			public void callback(int callCode, Object... param) {
				if (callCode == TouchScaleListView.ACTION_MOVEINTOP) {
					float sc = (Float.parseFloat(param[0].toString()) + 100) / 100;
					if (sc <= 1)sc = 1;
					headImg.setScaleX(sc);
					headImg.setScaleY(sc);
				} else if (callCode == TouchScaleListView.ACTION_UP) {
					if (headImg.getScaleX() != 1.0f || headImg.getScaleY() != 1.0f) {
						headImg.animate().setDuration(200).scaleX(1.0f).scaleY(1.0f);
					}
				}
			}
		});

	}

	private void init() {
		lv = (TouchScaleListView) findViewById(R.id.touchscale_lv);
		headView = (View) getLayoutInflater().inflate(R.layout.touchscale_head, null);
		headImg = (ImageView) headView.findViewById(R.id.touchscale_head_img);
		TouchScaleItemAdapter adapter = new TouchScaleItemAdapter();
		lv.addHeaderView(headView);
		lv.setAdapter(adapter);
	}

	class TouchScaleItemAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 30;
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
				convertView = getLayoutInflater().inflate(R.layout.touchscale_item, null);
			}
			return convertView;
		}

	}
}
