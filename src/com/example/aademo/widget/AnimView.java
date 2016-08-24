package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.aademo.R;
import com.example.aademo.bean.AnimBean;
import com.example.aademo.util.PalLog;

import java.util.ArrayList;

/**
 * Created by mik_eddy on 16/8/24.
 */
public class AnimView extends View {
    Bitmap bitmap_1;
    Bitmap bitmap_2;

    int intViewHeight = 0;

    int bitmap_1_Width = 0;
    int bitmap_1_Height = 0;

    int bitmap_2_Width = 0;
    int bitmap_2_Height = 0;

    ArrayList<AnimBean> arraylist_animbeans = new ArrayList<>();


    Paint paint;
    boolean isRunning = false;

    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        intViewHeight = getHeight();
    }

    public void start() {
        bitmap_1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        bitmap_2 = BitmapFactory.decodeResource(getResources(), R.drawable.captureclose);
        paint = new Paint();
        bitmap_1_Width = bitmap_1.getWidth();
        bitmap_1_Height = bitmap_1.getHeight();
        bitmap_2_Width = bitmap_2.getWidth();
        bitmap_2_Height = bitmap_2.getHeight();

        AnimBean bean1 = new AnimBean();
        bean1.setRotate(0);
        bean1.setRotatespeed(1);
        bean1.setSpeed(5);
        bean1.setStartX(100);
        bean1.setStartY(10);

        AnimBean bean2 = new AnimBean();
        bean2.setRotate(0);
        bean2.setRotatespeed(5);
        bean2.setSpeed(10);
        bean2.setStartX(200);
        bean2.setStartY(0);


        AnimBean bean3 = new AnimBean();
        bean2.setRotate(0);
        bean2.setRotatespeed(7);
        bean2.setSpeed(7);
        bean2.setStartX(300);
        bean2.setStartY(0);

        arraylist_animbeans.add(bean1);
        arraylist_animbeans.add(bean2);
        arraylist_animbeans.add(bean3);


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    isRunning = true;
                    while (true) {
                        Thread.sleep(20);
                        for (int i = 0; i < arraylist_animbeans.size(); i++) {
                            AnimBean bean = arraylist_animbeans.get(i);
                            if (bean.isNeedDraw()) {
                                bean.setStartY(bean.getStartY() + bean.getSpeed());
                                bean.setRotate(bean.getRotate() + bean.getRotatespeed());
                                if (bean.getCurrentImg() == AnimBean.IMG_1) {
                                    int max = Math.max(bitmap_1_Width, bitmap_1_Height);
                                    bean.setRect(bean.getStartX(), bean.getStartY(), bean.getStartX() + max, bean.getStartY() + max);
                                } else if (bean.getCurrentImg() == AnimBean.IMG_2) {
                                    int max = Math.max(bitmap_2_Width, bitmap_2_Height);
                                    bean.setRect(bean.getStartX(), bean.getStartY(), bean.getStartX() + max, bean.getStartY() + max);
                                }
                                //当图片绘制位置大于VIEW的位置的时候就不需要再绘制和累加了
                                if (bean.getStartY() > intViewHeight) bean.setNeedDraw(false);
                                //当图片处于点击后的状态时,倒数N次,之后图片不再绘制
                                if (bean.getCurrentImg() == AnimBean.IMG_2) bean.setDownCount(bean.getDownCount() - 1);
                                if (bean.getCurrentImg() == AnimBean.IMG_2 && bean.getDownCount() <= 0) bean.setNeedDraw(false);
                            }

                        }
                        postInvalidate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < arraylist_animbeans.size(); i++) {
                AnimBean bean = arraylist_animbeans.get(i);
                if (bean.inRect((int) event.getX(), (int) event.getY())) {
                    bean.setCurrentImg(AnimBean.IMG_2);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isRunning) {
            for (int i = 0; i < arraylist_animbeans.size(); i++) {
                AnimBean bean = arraylist_animbeans.get(i);
                PalLog.printD("i:" + i + "___" + bean.isNeedDraw());
                if (bean.isNeedDraw()) {
                    canvas.save();
                    if (bean.getCurrentImg() == AnimBean.IMG_1) {
                        canvas.rotate(bean.getRotate(), bean.getStartX() + (bitmap_1_Width / 2), bean.getStartY() + (bitmap_1_Height / 2));
                        canvas.drawBitmap(bitmap_1, bean.getStartX(), bean.getStartY(), paint);
                    } else if (bean.getCurrentImg() == AnimBean.IMG_2) {
                        canvas.rotate(bean.getRotate(), bean.getStartX() + (bitmap_2_Width / 2), bean.getStartY() + (bitmap_2_Height / 2));
                        canvas.drawBitmap(bitmap_2, bean.getStartX(), bean.getStartY(), paint);
                    }
                    canvas.restore();
                }

            }
        }
    }


}
