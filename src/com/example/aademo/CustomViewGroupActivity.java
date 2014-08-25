package com.example.aademo;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.aademo.impl.ViewAdapter;
import com.example.aademo.util.PalLog;
import com.example.aademo.widget.CustomViewGroup;

public class CustomViewGroupActivity extends BaseActivity {
	CustomViewGroup cvgGroup;
	Button btn_add;
	ArrayList<String> listData = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customviewgroup_main);
		cvgGroup = (CustomViewGroup) findViewById(R.id.cvg_viewgroup);
		btn_add = findView(R.id.cvg_btn_add);
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cvgGroup.startAnimation();
			}
		});
		for (int i = 0; i < 50; i++) {
			listData.add("hello world" + i);
		}
		cvgGroup.setAdapter(new ViewAdapter() {

			@Override
			public int getViewCount() {
				return listData.size();
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = null;
				if (convertView == null) {
					convertView = LayoutInflater.from(mContext).inflate(R.layout.customviewgroup_item, null);
					tv = ((TextView) convertView.findViewById(R.id.svgitem_tv));
					convertView.setTag(tv);
				} else {
					tv = (TextView) convertView.getTag();
				}
				tv.setText(listData.get(position));
				return convertView;
			}
		});
	}

}
