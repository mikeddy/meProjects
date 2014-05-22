package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class CustomSeekBar extends SeekBar{
	Drawable thumbDrawable;
	public CustomSeekBar(Context context) {
		super(context);
	}
	public CustomSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public void setThumb(Drawable thumb) {
		super.setThumb(thumb);
		thumbDrawable=thumb;
	}
	
	public Rect getThumbBounds(){
	    return thumbDrawable.getBounds();	
	}
}
