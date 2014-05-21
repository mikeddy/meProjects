package com.example.aademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class LayersDrawableActivity extends BaseActivity {
	ImageView imgView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layersdrawable_main);
		init();
		initViews();
	}
	
	private void init(){
		imgView=(ImageView)findViewById(R.id.layer_imgview);
	}
	
	
	private void initViews(){
		imgView.setImageDrawable(getLayerDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
//		imgView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.headicon));
//		imgView.setImageResource(R.drawable.ic_launcher);
//		imgView.post(new Runnable() {
//			@Override
//			public void run() {
//				showToast(imgView.getWidth()+"===="+imgView.getHeight());				
//			}
//		});

	}
	
	
	private LayerDrawable getLayerDrawable(Bitmap bitmap){
		showToast(bitmap.getWidth()+"===="+bitmap.getHeight());	
		Drawable[] array = new Drawable[4];
		array[0] = new BitmapDrawable(bitmap);
		array[1] = new BitmapDrawable(bitmap);
		array[2] = new BitmapDrawable(bitmap);
		array[3] = new BitmapDrawable(bitmap);
		LayerDrawable la = new LayerDrawable(array);
//		la.setLayerInset(0, 10, 0, 10, 0);
//		la.setLayerInset(1, 40, 0, 40, 0);
//		la.setLayerInset(2, 70, 0, 70, 0);
//		la.setLayerInset(3, 100, 0, 100, 0);
//		la.getDrawable(0).setAlpha(200);
//		la.getDrawable(1).setAlpha(150);
//		la.getDrawable(2).setAlpha(100);
		return la;
	}
	
	
	

//	private Drawable getLayerDrawableAA(Bitmap bitmap){
//		showToast(bitmap.getWidth()+"===="+bitmap.getHeight());
//		 d= new BitmapDrawable(bitmap);
////		Drawable[] array = new Drawable[4];
////		array[0] = new BitmapDrawable(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),  bitmap.getHeight(),  new Matrix(), true));
////		array[1] = new BitmapDrawable(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),  bitmap.getHeight(),  new Matrix(), true));
////		array[2] = new BitmapDrawable(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),  bitmap.getHeight(),  new Matrix(), true));
////		array[3] = new BitmapDrawable(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),  bitmap.getHeight(),  new Matrix(), true));
////		
////		LayerDrawable la = new LayerDrawable(array);
////		la.setLayerInset(0, 15, 5, 15, 0);
////		la.setLayerInset(1, 10, 10, 10, 0);
////		la.setLayerInset(2, 5, 15, 5, 0);
////		la.setLayerInset(3, 0, 20, 0, 0);
//		return d;
//	}
}
