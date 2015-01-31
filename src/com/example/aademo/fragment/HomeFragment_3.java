package com.example.aademo.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.aademo.activitys.ResideMenuActivity;
import com.example.aademo.R;
import com.example.aademo.widget.ResideMenu;

public class HomeFragment_3 extends Fragment implements OnItemClickListener {

	private View parentView;
	private ResideMenu resideMenu;
	private ListView lv;
	private Activity mAc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.residemenu_home, container, false);
		setUpViews();
		return parentView;
	}

	private void setUpViews() {
		mAc = getActivity();
		ResideMenuActivity parentActivity = (ResideMenuActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
		lv = (ListView) parentView.findViewById(R.id.home_lv);
		lv.setAdapter(new HomeAdapter());
		lv.setOnItemClickListener(this);
	}

	class HomeAdapter extends BaseAdapter {

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
			TextView tv;
			if (convertView == null) {
				tv = new TextView(mAc);
				tv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 100));
				// tv.setLayoutParams(new layoutparam(LayoutParams.FILL_PARENT,
				// 100));
				tv.setTextColor(Color.RED);
				convertView = tv;
			} else {
				tv = (TextView) convertView;
			}
			tv.setText("hello home3 这里是 position" + position);
			return convertView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(mAc, position + "", Toast.LENGTH_SHORT).show();
	}

}
