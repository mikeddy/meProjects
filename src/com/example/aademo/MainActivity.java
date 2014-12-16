package com.example.aademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

import zxing.activity.CaptureActivity;

;

public class MainActivity extends BaseActivity {
	
	ArrayList<MainItemHolder>listItemHolders=new ArrayList<MainActivity.MainItemHolder>();
	LinearLayout linParent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}
	
	private void init(){
		linParent=(LinearLayout)findViewById(R.id.main_parentlin);
		listItemHolders.add(new MainItemHolder("滑动动画开关", SlideParent_Activity.class));
		listItemHolders.add(new MainItemHolder("自定义seekbar", CustomSeekBarActivity.class));
		listItemHolders.add(new MainItemHolder("横向带纹理进度条", HorizontalProgressBarActivity.class));
		listItemHolders.add(new MainItemHolder("层图片", LayersDrawableActivity.class));
		listItemHolders.add(new MainItemHolder("滑动遮盖效果", CoverActivity.class));
		listItemHolders.add(new MainItemHolder("圆形图片", CircleImageActivity.class));
		listItemHolders.add(new MainItemHolder("下拉放大效果", TouchScaleActivity.class));
		listItemHolders.add(new MainItemHolder("文字多少决定textview的字体大小(不超过一行)", ScaleTextViewActivity.class));
		listItemHolders.add(new MainItemHolder("Text颜色渐变AND圆形进度条", CircleProgressActivity.class));
		listItemHolders.add(new MainItemHolder("自定义drawable.xml的深入研究", DrawableXMLActivity.class));
		listItemHolders.add(new MainItemHolder("横向滑动的listview", HorizontalListViewActivity.class));
		listItemHolders.add(new MainItemHolder("翻阅listview源码后根据自己的理解写出的一些小玩意", CustomViewGroupActivity.class));
		listItemHolders.add(new MainItemHolder("图片的手动控制旋转", WheelMenuActivity.class));
		listItemHolders.add(new MainItemHolder("控件旋转", RotateActivity.class));
		listItemHolders.add(new MainItemHolder("仿新版QQ侧滑", ResideMenuActivity.class));
		listItemHolders.add(new MainItemHolder("雪花旋转进度条", SnowProgressActivity.class));
		listItemHolders.add(new MainItemHolder("ListView的保持不动的item", StickyActivity.class));
		listItemHolders.add(new MainItemHolder("跳转到其他app", GotoOtherAPPActivity.class));
        listItemHolders.add(new MainItemHolder("带闪光灯的二维码扫描", CaptureActivity.class));
        listItemHolders.add(new MainItemHolder("Event Bus 全局通信DEMO", EventBusActivity.class));

		
		for (int i = 0; i < listItemHolders.size(); i++) {
			linParent.addView(listItemHolders.get(i).btn);
		}
	}
	
	
	class MainItemHolder{
		public MainItemHolder(String title,Class<?>cla){
			c=cla;
			if(btn==null){
				btn=new Button(mContext);
				btn.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
				btn.setGravity(Gravity.CENTER);
				btn.setText(title);
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent=new Intent(mContext, c);
						startActivity(intent);
					}
				});
			}
		}
		public Button btn;
		Class<?> c;
	}
}
