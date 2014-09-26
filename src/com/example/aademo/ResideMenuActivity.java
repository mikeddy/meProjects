package com.example.aademo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.example.aademo.fragment.HomeFragment;
import com.example.aademo.fragment.HomeFragment_2;
import com.example.aademo.fragment.HomeFragment_3;
import com.example.aademo.widget.ResideMenu;

public class ResideMenuActivity extends FragmentActivity {

	private ResideMenu resideMenu;

	PagerAdapter mPagerAdapter;
	ArrayList<Fragment> arraylist_Fragment;
	ViewPager vp_main;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residemenu_main);
		setUpMenu();
		init();
	}

	private void init() {
		vp_main = (ViewPager) findViewById(R.id.residemenu_vp_main);
		arraylist_Fragment = new ArrayList<Fragment>();
		arraylist_Fragment.add(new HomeFragment());
		arraylist_Fragment.add(new HomeFragment_2());
		arraylist_Fragment.add(new HomeFragment_3());
		final MyFragementAdapter adapter = new MyFragementAdapter(getSupportFragmentManager());
		vp_main.setAdapter(adapter);
		vp_main.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				resideMenu.setEdg(arg0 == 0 ? ResideMenu.EDG_LEFT : ResideMenu.EDG_NONE);
//				resideMenu.setEdg(arg0 == adapter.getCount()-1 ? ResideMenu.EDG_RIGHT : ResideMenu.EDG_NONE);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
//		resideMenu.setEdg(ResideMenu.EDG_NONE);
		resideMenu.setEdg(ResideMenu.EDG_LEFT);
		// resideMenu.addIgnoredView(vp_main);
	}

	private void setUpMenu() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.jianbian_tv_bg);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		View v = LayoutInflater.from(this).inflate(R.layout.left_menucontent, null);
		resideMenu.setLeftMenuContent(v);
		resideMenu.setScaleValue(0.8f);
		resideMenu.setOffsetValue(2.5f);
//		resideMenu.setSwipeDir(ResideMenu.SWIPEDIR_RIGHT);
		resideMenu.setSwipeDir(ResideMenu.SWIPEDIR_LEFT);

		findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
		}

		@Override
		public void closeMenu() {
		}
	};

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	class MyFragementAdapter extends FragmentPagerAdapter {

		public MyFragementAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return arraylist_Fragment.get(arg0);
		}

		@Override
		public int getCount() {
			return arraylist_Fragment.size();
		}

	}

}
