package com.example.aademo.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.aademo.events.EventUtil;

@SuppressLint("ShowToast")
public class BaseActivity extends Activity {
	//V4.2
	Toast mToast=null;
	public static final int LONGTOASTDURATION = 2500;
	public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		WebView v;
//		v.loadUrl(url, additionalHttpHeaders)
//		v.postUrl(url, postData);
		
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventUtil.unregister(this);
    }

    //	@Override
//	public View findViewById(int id) {
//		
//		return super.findViewById(id);
//	}
	@SuppressWarnings("unchecked")
	public <T>T findView(int id){
		T t=(T)findViewById(id);
		return t;
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
	
	public int getmColor(int colorid){
		return getResources().getColor(colorid);
	}
}
