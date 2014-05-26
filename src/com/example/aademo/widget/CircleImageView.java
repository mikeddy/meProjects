package com.example.aademo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.aademo.R;

public class CircleImageView extends ImageView {

	private int borderWidth = 0;
	private int viewWidth;
	private int viewHeight;
	// private Bitmap image;
	private Paint paint;
	private Paint paintBorder;

	// private BitmapShader shader;

	public CircleImageView(Context context) {
		super(context);
		setup();
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup();
	}

	private void setup() {
		// init paint
		paint = new Paint();
		paint.setAntiAlias(true);

		paintBorder = new Paint();
		setBorderColor(getResources().getColor(R.color.white_001));
		paintBorder.setAntiAlias(true);
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
		this.invalidate();
	}

	public void setBorderColor(int borderColor) {
		if (paintBorder != null)
			paintBorder.setColor(borderColor);

		this.invalidate();
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		// load the bitmap
		Bitmap image = null;
		BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();
		if (bitmapDrawable != null)
			image = bitmapDrawable.getBitmap();
		// init shader
		if (image != null) {
			BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(
					image, canvas.getWidth(), canvas.getHeight(), false),
					Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
			paint.setShader(shader);
			int circleCenter = viewWidth / 2;

			// circleCenter is the x or y of the view's center
			// radius is the radius in pixels of the cirle to be drawn
			// paint contains the shader that will texture the shape
			canvas.drawCircle(circleCenter + borderWidth, circleCenter
					+ borderWidth, circleCenter + borderWidth, paintBorder);
			canvas.drawCircle(circleCenter + borderWidth, circleCenter
					+ borderWidth, circleCenter, paint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

		viewWidth = width - (borderWidth << 1);
		viewHeight = height - (borderWidth << 1);

		setMeasuredDimension(width, height);
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text
			result = viewWidth + (borderWidth << 1);
		}

		return result;
	}

	private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpecHeight);
		int specSize = MeasureSpec.getSize(measureSpecHeight);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text (beware: ascent is a negative number)
			result = viewHeight + (borderWidth << 1);
		}

		return (result);
	}
}
