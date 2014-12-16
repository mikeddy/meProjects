package com.example.aademo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GotoOtherAPPActivity extends BaseActivity {
	Button btn_goto, btn_callPhone;
	Context mContext;
	WebView webView_goto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gotootherapp);
		init();
	}

	/**
	 * 备用非scheme方法跳转 跨APP调用Activity需要执行以下步骤
	 * 1.在对应APP的mainfaset.xml的对应Activity标签中加入以下标签 <intent-filter> <action
	 * android:name="com.xxx1.xxx2.xxxx3"/> <category
	 * android:name="android.intent.category.DEFAULT"/> </intent-filter>
	 * 
	 * 
	 *  Intent i = new Intent(Intent.CATEGORY_DEFAULT); 
	 * i.putExtra("fromotherapp", true); 
	 *  i.putExtra("investID", "4946"); 
	 * i.setAction("com.ncf.firstp2p.newmain"); 
	 * startActivity(i);
	 */

	private void init() {
		mContext = this;
		btn_callPhone = (Button) findViewById(R.id.btn_callphone);
		btn_goto=(Button)findViewById(R.id.btn_gotoapp);
		webView_goto = (WebView) findViewById(R.id.webview_gotoapp_method2);

		btn_goto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkExists(mContext, "com.ncf.firstp2p")) {
					Intent intent = new Intent("com.ncf.firstp2p.activity.SplashActivity", Uri.parse("firstp2p://service.firstp2p.com/p2p/detail?id=4964"));
					startActivity(intent);
				} else {
					showToast("您需要先安装firstp2p");
				}
			}
		});

		btn_callPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:18610327752");
				Intent it = new Intent(Intent.ACTION_DIAL, uri);
				startActivity(it);
			}
		});
		String content = "第二种跳转方法";
		webView_goto.loadDataWithBaseURL(null, "<a href=\"firstp2p://service.firstp2p.com/p2p/detail?id=4964\">" + content + "</a>", "text/html", "UTF-8", null);
	}

	public static boolean checkExists(Context context, String packageName) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
		// 从pinfo中将包名字逐一取出，压入pName list中
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if(pn.equals(packageName)){
					Toast.makeText(context, pinfo.get(i).versionCode+"", Toast.LENGTH_LONG).show();
				}
				pName.add(pn);
			}
		}
		return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
	}
}
