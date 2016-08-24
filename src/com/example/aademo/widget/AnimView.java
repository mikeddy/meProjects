package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mik_eddy on 16/8/24.
 */
public class AnimView extends View {
    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
