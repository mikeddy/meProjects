package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.aademo.R;

public class SelImageView extends ImageView {
    private boolean isSelected;
    private boolean needSelected=true;
    private ColorFilter selectorFilter;
    Context mContext;

    public SelImageView(Context context) {
        super(context);
        init(context);
    }

    public SelImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext=context;
        setSelectorColor(context.getResources().getColor(R.color.defselfilter));
    }
    public void setSelectorColor(int selectorColor) {
        this.selectorFilter = new PorterDuffColorFilter(selectorColor, PorterDuff.Mode.SRC_ATOP);
        this.invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (isNeedSelected()&&isSelected()) {
            setColorFilter(selectorFilter);
        } else {
            setColorFilter(null);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!this.isClickable()) {
            this.isSelected = false;
            return super.onTouchEvent(event);
        }

        // Set selected state based on Motion Event
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.isSelected = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_SCROLL:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
                this.isSelected = false;
                break;
        }

        // Redraw image and return super type
        this.invalidate();
        return super.dispatchTouchEvent(event);
    }

    public boolean isSelected() {
        return this.isSelected;
    }
    public boolean isNeedSelected() {
        return needSelected;
    }

    public void setNeedSelected(boolean needSelected) {
        this.needSelected = needSelected;
    }
}
