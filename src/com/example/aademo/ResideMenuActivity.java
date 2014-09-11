package com.example.aademo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.example.aademo.R;
import com.example.aademo.fragment.CalendarFragment;
import com.example.aademo.fragment.HomeFragment;
import com.example.aademo.fragment.ProfileFragment;
import com.example.aademo.fragment.SettingsFragment;
import com.example.aademo.widget.ResideMenu;
import com.example.aademo.widget.ResideMenuItem;

public class ResideMenuActivity extends FragmentActivity implements View.OnClickListener {

	private ResideMenu resideMenu;
	private ResideMenuActivity mContext;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemProfile;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemSettings;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residemenu_main);
		mContext = this;
		setUpMenu();
		changeFragment(new HomeFragment());
	}

	private void setUpMenu() {

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.jianbian_tv_bg);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		
		View v=LayoutInflater.from(this).inflate(R.layout.left_menucontent, null);
//		View v2=LayoutInflater.from(this).inflate(R.layout.right_menucontent, null);
		resideMenu.setLeftMenuContent(v);
//		resideMenu.setRightMenuContent(v2);

		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.8f);
		resideMenu.setOffsetValue(2.5f);
		resideMenu.setSwipeDir(ResideMenu.SWIPEDIR_LEFT);

		// create menu items;
//		itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
//		itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "Profile");
//		itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
//		itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");
//
//		itemHome.setOnClickListener(this);
//		itemProfile.setOnClickListener(this);
//		itemCalendar.setOnClickListener(this);
//		itemSettings.setOnClickListener(this);

//		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
//		resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
//		resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
//		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);

		// You can disable a direction by setting ->
		// resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

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

	@Override
	public void onClick(View view) {

		if (view == itemHome) {
			changeFragment(new HomeFragment());
		} else if (view == itemProfile) {
			changeFragment(new ProfileFragment());
		} else if (view == itemCalendar) {
			changeFragment(new CalendarFragment());
		} else if (view == itemSettings) {
			changeFragment(new SettingsFragment());
		}

		resideMenu.closeMenu();
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			// Toast.makeText(mContext, "Menu is opened!",
			// Toast.LENGTH_SHORT).show();
		}

		@Override
		public void closeMenu() {
			// Toast.makeText(mContext, "Menu is closed!",
			// Toast.LENGTH_SHORT).show();
		}
	};

	private void changeFragment(Fragment targetFragment) {
		resideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, targetFragment, "fragment").setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
	}

	// What good method is to access resideMenu���
	public ResideMenu getResideMenu() {
		return resideMenu;
	}
}
