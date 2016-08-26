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
import java.util.Random;

/**
 * Created by mik_eddy on 16/8/24.
 */
public class AnimView extends View {
    public final static int UPWARD = 0, DOWNWARD = 1;//动画方向，向上，向下
    Bitmap bitmap_1;//初始化图片
    Bitmap bitmap_2;//被点中后图片

    int intViewWidth = 0;//整个view的宽度
    int intViewHeight = 0;//整个view的高度

    int bitmap_1_Width = 0;//图片1宽度
    int bitmap_1_Height = 0;//图片1高度

    int bitmap_2_Width = 0;//图片2宽度
    int bitmap_2_Height = 0;//图片2高度

    ArrayList<AnimBean> arraylist_animbeans = new ArrayList<>();//运动物体个数
    private boolean isClicked;//同一区域点击一次只能有一次

    Paint paint;
    boolean isRunning = false;//是否运动

    public AnimView(Context context) {
        super(context);
        init();
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void init(){
        bitmap_1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        bitmap_2 = BitmapFactory.decodeResource(getResources(), R.drawable.captureclose);
        paint = new Paint();
        bitmap_1_Width = bitmap_1.getWidth();
        bitmap_1_Height = bitmap_1.getHeight();
        bitmap_2_Width = bitmap_2.getWidth();
        bitmap_2_Height = bitmap_2.getHeight();


    }

    public void resetAnimBean(AnimBean bean) {
        Random random = new Random();
        bean.setRotate(random.nextInt(360));
        bean.setRotatespeed(5 + random.nextInt(10));
        bean.setSpeed(speed + random.nextInt(30));
        bean.setStartX(random.nextInt(intViewWidth - bitmap_1_Width));
        bean.setStartY(startY);//Y轴坐标为
        bean.setDownCount(AnimBean.DEFAULTDOWNCOUNT);
        bean.setNeedDraw(true);//是否再次绘制
        bean.setCurrentImg(AnimBean.IMG_1);//初始时图片为图片1
    }

    public void start() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);

                    intViewHeight = getHeight();//取view的高度
                    intViewWidth = getWidth();//取view的宽度

                    switch (direction){
                        case UPWARD:
                            startY =intViewHeight;
                            speed = -35;
                            break;
                        case DOWNWARD:
                            startY = 0;
                            speed = 5;
                            break;
                        default:
                            startY = 0;
                            speed = 5;
                            break;
                    }



                    //初始化运动对象
                    for (int i = 0; i < 10; i++) {
                        AnimBean bean = new AnimBean();
                        resetAnimBean(bean);
                        arraylist_animbeans.add(bean);
                    }

                    isRunning = true;
                    while (true) {
                        Thread.sleep(20);
                        for (int i = 0; i < arraylist_animbeans.size(); i++) {
                            AnimBean bean = arraylist_animbeans.get(i);
                            if (bean.isNeedDraw()) {//需要绘制运动对象
                                bean.setStartY(bean.getStartY() + bean.getSpeed());//设置y坐标
                                bean.setRotate(bean.getRotate() + bean.getRotatespeed());//设置旋转角度
                                if (bean.getCurrentImg() == AnimBean.IMG_1) {
                                    int max = Math.max(bitmap_1_Width, bitmap_1_Height);//运动对象图片的最长边
                                    bean.setRect(bean.getStartX(), bean.getStartY(), bean.getStartX() + max, bean.getStartY() + max);//设置图片所在区域
                                } else if (bean.getCurrentImg() == AnimBean.IMG_2) {
                                    int max = Math.max(bitmap_2_Width, bitmap_2_Height);
                                    bean.setRect(bean.getStartX(), bean.getStartY(), bean.getStartX() + max, bean.getStartY() + max);
                                }
                                //当图片绘制位置大于VIEW的位置的时候就不需要再绘制和累加了

                                if (bean.getStartY() > intViewHeight && direction == DOWNWARD) bean.setNeedDraw(false);//向下运动时超过view的高度，就不再绘制
                                if (bean.getStartY() < 0 && direction == UPWARD) bean.setNeedDraw(false);//向上运动时超过y轴零坐标，就不再绘制

                                //当图片处于点击后的状态时,倒数N次,之后图片不再绘制
                                if (bean.getCurrentImg() == AnimBean.IMG_2) bean.setDownCount(bean.getDownCount() - 1);
                                if (bean.getCurrentImg() == AnimBean.IMG_2 && bean.getDownCount() <= 0) bean.setNeedDraw(false);
                            } else {
                                resetAnimBean(bean);
                            }
                        }
                        postInvalidate();//重绘
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
                if (bean.inRect((int) event.getX(), (int) event.getY()) && !isClicked) {
                    bean.setCurrentImg(AnimBean.IMG_2);
                    isClicked = true;//同一区域点击一次只能有一次
                }
            }
        }
        isClicked = false;
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

    int startY = 0;//起始时Y轴位置
    int speed = 5;
    int direction = -1;//方向
    public void setDirection(int direct){
        direction = direct;
    }

    /*停止动画*/
    public void stop(){
        setRunning(false);
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


}
