package com.example.aademo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.util.DensityUtils;

/**
 *
 * 自动伸缩的textview
 *
 * @author XuGaoxiang
 * @modified XuWei
 */
public class AutoScaleTextView extends TextView
{

	/** 持续缩放临界值 */
	private static final float THRESHOLD = 0.5f;
	/** 最小字体大小，单位sp */
	private static final float MIN_TEXT_SIZE = 10f;
	/** 缩放比 */
	private static final float SCALE_RATIO = 1.1f;

	/** 用于计算文字所占尺寸 */
	private Paint mMeasureSizePaint;
	/** 设定字体大小，单位px */
	private float mOriginalTextSize;
	/** 转换完的最小字体大小，单位px */
	private float mTranslateMinTextSize;

	/** 是否已经完成了缩放处理 */
	private boolean mIsHasScaled;

	public AutoScaleTextView(Context context)
	{
		this(context, null);
	}

	public AutoScaleTextView(Context context, AttributeSet attrs)
	{
		this(context, attrs, R.attr.autoScaleTextViewStyle);
	}

	public AutoScaleTextView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);

		this.mMeasureSizePaint = new Paint();

		TypedArray attributes = context.obtainStyledAttributes(attrs,R.styleable.AutoScaleTextView, defStyle, 0);
		this.mTranslateMinTextSize = attributes.getDimensionPixelSize(R.styleable.AutoScaleTextView_minTextSize, DensityUtils.sp2px(getContext(), MIN_TEXT_SIZE));
		attributes.recycle();

		this.mOriginalTextSize = this.getTextSize();
	}

	/**
	 *
	 * 设置最小的字体大小
	 * @param minTextSize
	 */
	public void setMinTextSize(float minTextSize)
	{
		this.mTranslateMinTextSize = minTextSize;
	}

	@Override
	public void setTextSize(int unit, float size) {
		super.setTextSize(unit, size);
		mIsHasScaled = false;
		mOriginalTextSize = getTextSize();
		refitText(getText().toString(), getWidth());
	}

	/**
	 *
	 * 重新改变text的size
	 *
	 * @param text  需要传入的text
	 *
	 * @param widgetWidth  text的宽度
	 *
	 */
	private void refitText(String text, int widgetWidth)
	{
		if (widgetWidth <= 0 || text == null || text.length() == 0)
			return;

		//净宽度，除去左右padding
		int pureWidth = widgetWidth - this.getPaddingLeft() - this.getPaddingRight();

		this.mMeasureSizePaint.set(this.getPaint());

		float tempSize = mOriginalTextSize;
		while ((tempSize - mTranslateMinTextSize) > THRESHOLD) {
			mMeasureSizePaint.setTextSize(tempSize);
			if (mMeasureSizePaint.measureText(text) >= pureWidth)
				tempSize /= SCALE_RATIO; // 文字太大了,测绘宽度已经超过控件的宽度了
			else{
				break;
			}
		}
		//循环结束后,就已经设置成一个最合适的字体大小了
		mIsHasScaled = true;
		super.setTextSize(TypedValue.COMPLEX_UNIT_PX, tempSize);
	}


	/**
	 * 设置text并且重新计算伸缩textSize
	 * @param text
	 */
	public void setTextAndResize(String text){
		mIsHasScaled =false;
		setText(text);
	}

	private void reSize(float size,String text,float targetWidth){
		float nSize=this.mOriginalTextSize;
		while ((nSize -mTranslateMinTextSize ) > THRESHOLD)
		{
			size = (nSize + mTranslateMinTextSize) / 2;
			this.mMeasureSizePaint.setTextSize(size);
			if (this.mMeasureSizePaint.measureText(text) >= targetWidth)
				nSize = size; // 文字太大了,测绘宽度已经超过控件的宽度了
			else{
				break;
			}

		}
		//循环结束后,就已经设置成一个最合适的字体大小了
		mIsHasScaled =true;
		this.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
	}

	@Override
	protected void onTextChanged(final CharSequence text, final int start, final int before, final int after)
	{
		this.refitText(text.toString(), this.getWidth());
	}

	@Override
	protected void onSizeChanged(int width, int height, int oldwidth, int oldheight)
	{
		//只要width!=oldwidth 就说明是可以缩放的,否则就说明已经是缩放到最小了
		if (width != oldwidth && !mIsHasScaled)
			this.refitText(this.getText().toString(), width);
	}

}