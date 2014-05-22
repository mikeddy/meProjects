package com.example.aademo;

import com.example.aademo.widget.CustomSeekBar;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import static com.nineoldandroids.view.ViewHelper.setX;

/**
 * 自定义seekbar,滑动seekbar的时候自定义一个跟随一起滑动的textview
 * 
 * @author mik_eddy
 * 
 */

public class CustomSeekBarActivity extends BaseActivity {
	CustomSeekBar mSeekBar;
	TextView tvTip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customseekbar_main);
		init();
	}

	private void init() {
		mSeekBar = (CustomSeekBar) findViewById(R.id.customseekbar_sb);
		tvTip = (TextView) findViewById(R.id.customseekbar_tvtip);
		mSeekBar.setMax(30);
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvTip.setText(progress + "");
				int offsetWidth = tvTip.getWidth() / 2;
				// 4.0以上直接使用setx
				tvTip.setX(mSeekBar.getThumbBounds().left + offsetWidth);
				// 4.0以下版本请使用
				// setX(tvTip, mSeekBar.getThumbBounds().left + offsetWidth);
			}
		});
	}

}
