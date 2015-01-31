package com.example.aademo.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.widget.WheelMenu;

public class WheelMenuActivity extends BaseActivity {
	private WheelMenu wheelMenu;
	private TextView selectedPositionText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wheelmenu_main);

		wheelMenu = (WheelMenu) findViewById(R.id.wheelMenu);

		wheelMenu.setDivCount(12);
		wheelMenu.setWheelImage(R.drawable.wheel);

		selectedPositionText = (TextView) findViewById(R.id.selected_position_text);
		selectedPositionText.setText("selected: " + (wheelMenu.getSelectedPosition() + 1));

		wheelMenu.setWheelChangeListener(new WheelMenu.WheelChangeListener() {
			@Override
			public void onSelectionChange(int selectedPosition) {
				selectedPositionText.setText("selected: " + (selectedPosition + 1));
			}
		});
//		mHandler.sendEmptyMessageDelayed(0, 5000);
	}
	
	
	Handler mHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			showToast("hello");
			wheelMenu.setAlternateTopDiv(5);
		}
	};

}
