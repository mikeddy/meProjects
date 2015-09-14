package com.example.aademo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.MotionEvent;

//跟App相关的辅助类
public class AppUtils
{

	private AppUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getEventActionName(MotionEvent event){
		if(event.getAction()==MotionEvent.ACTION_CANCEL)return "ACTION_CANCEL";
		else if(event.getAction()==MotionEvent.ACTION_DOWN)return "ACTION_DOWN";
		else if(event.getAction()==MotionEvent.ACTION_MOVE)return "ACTION_MOVE";
		else if(event.getAction()==MotionEvent.ACTION_OUTSIDE)return "ACTION_OUTSIDE";
		else if(event.getAction()==MotionEvent.ACTION_UP)return "ACTION_UP";
		return "UNKNOW:"+event.getAction();

	}

}
