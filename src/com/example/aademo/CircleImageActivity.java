package com.example.aademo;

import com.example.aademo.util.PalLog;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

public class CircleImageActivity extends BaseActivity {
	//v4.1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circleimage_main);
	}

}
