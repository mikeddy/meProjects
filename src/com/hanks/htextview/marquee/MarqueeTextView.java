package com.hanks.htextview.marquee;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.hanks.htextview.base.HTextView;

/**
 * Created by mik_eddy on 2017/9/7.
 */

public class MarqueeTextView extends HTextView {

    private MarqueeText marqueeText;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        marqueeText = new MarqueeText();
        marqueeText.init(this, attrs, defStyleAttr);
        setMaxLines(1);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void setProgress(float progress) {
        marqueeText.setProgress(progress);
    }

    @Override
    public void animateText(CharSequence text) {
        marqueeText.animateText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        marqueeText.onDraw(canvas);
    }
}
