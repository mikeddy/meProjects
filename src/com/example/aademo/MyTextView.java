package com.example.aademo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {
	private float degree;
	private float x;
	private float y;

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 从layout布局中获取属性值
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
		x = a.getFloat(R.styleable.MyTextView_x, 0);
		y = a.getFloat(R.styleable.MyTextView_y, 0);
		degree = a.getFloat(R.styleable.MyTextView_degree, 0);
		a.recycle(); // 要记得释放
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.rotate(degree, x, y);
		super.onDraw(canvas);
	}
}
