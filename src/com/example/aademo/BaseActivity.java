package com.example.aademo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class BaseActivity extends Activity {
	//new
	Toast mToast=null;
	public static final int LONGTOASTDURATION = 2500;
	public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		if (mToast == null)mToast = Toast.makeText(mContext, "", LONGTOASTDURATION);
	}

	public void showToast(String message) {
		if (mToast != null) {
			mToast.setDuration(LONGTOASTDURATION);
			mToast.setText(message);
			mToast.show();
		}
	}
	
	public void printLogv(String message) {
		Log.v(this.getClass().getName(), message);
	}

	public void printLogD(String message) {
		Log.d(this.getClass().getName(), message);
	}

	public void printLogE(String message) {
		Log.e(this.getClass().getName(), message);
	}
}
