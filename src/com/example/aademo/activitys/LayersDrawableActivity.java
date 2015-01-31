package com.example.aademo.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aademo.R;

/**
 * 设置层叠效果
 * 
 * @author mik_eddy
 * 
 */
public class LayersDrawableActivity extends BaseActivity {
	ImageView imgView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layersdrawable_main);
		init();
		initViews();
	}

	private void init() {
		imgView = (ImageView) findViewById(R.id.layer_imgview);
	}

	private void initViews() {
		imgView.setImageDrawable(getLayerDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.cover_pic)));
	}

	@SuppressWarnings("deprecation")
	private LayerDrawable getLayerDrawable(Bitmap bitmap) {
		// showToast(bitmap.getWidth()+"===="+bitmap.getHeight());
		Drawable[] array = new Drawable[4];
		array[0] = new BitmapDrawable(bitmap);
		array[1] = new BitmapDrawable(bitmap);
		array[2] = new BitmapDrawable(bitmap);
		array[3] = new BitmapDrawable(bitmap);
		LayerDrawable la = new LayerDrawable(array);
		la.setLayerInset(0, -25, 0, 25, 0);
		la.setLayerInset(1, 0, 0, 0, 0);
		la.setLayerInset(2, 25, 0, -25, 0);
		la.setLayerInset(3, 125, 0, -125, 0);
		// la.getDrawable(0).setAlpha(200);
		// la.getDrawable(1).setAlpha(150);
		// la.getDrawable(2).setAlpha(100);
		return la;
	}
}
