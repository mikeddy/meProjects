package com.example.aademo;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;;

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
