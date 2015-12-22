package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/12/22.
 */
public class OptimizeUIView_3_0 extends View {
    Paint mPaint;
    Bitmap mBitmap;
    public OptimizeUIView_3_0(Context context) {
        super(context);
        init();
    }

    public OptimizeUIView_3_0(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OptimizeUIView_3_0(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cover_pic);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int transY=30;
        int startY=0;
        for (int i = 0; i < 10; i++) {
            canvas.save();
            canvas.translate(0,startY);
            canvas.drawBitmap(mBitmap,0,0,mPaint);
            canvas.restore();
            startY+=transY;
        }
    }
}
