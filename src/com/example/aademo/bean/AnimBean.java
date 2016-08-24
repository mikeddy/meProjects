package com.example.aademo.bean;

import android.graphics.Rect;

/**
 * Created by mik_eddy on 16/8/25.
 */
public class AnimBean {
    public static final int IMG_1 = 1, IMG_2 = 2;
    public static final int DEFAULTDOWNCOUNT=20;

    int startX = 200;  //X位置
    int startY = 0;  //y位置
    int rotate = 0;  //当前旋转角度
    int rotatespeed = 1;//旋转速度
    int speed = 10; //下落速度
    int currentImg = IMG_1;//当前图片
    boolean needDraw = true;//是否需要再继续绘制了
    int downCount = DEFAULTDOWNCOUNT;//当图片1点击一下变成图片2的时候会持续一段时间,然后消失(这个倒计时就是持续的次数,每次刷新图片这个数字-1,当他等于0的时候就不显示图片2了)
    Rect rect;//当前img所在范围区域

    public boolean inRect(int x, int y) {
        if (rect != null) {
            return rect.contains(x, y);
        }
        return false;
    }

    public void setRect(int l, int t, int r, int b) {
        if (rect == null) rect = new Rect();
        rect.set(l, t, r, b);
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getRotatespeed() {
        return rotatespeed;
    }

    public void setRotatespeed(int rotatespeed) {
        this.rotatespeed = rotatespeed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCurrentImg() {
        return currentImg;
    }

    public void setCurrentImg(int currentImg) {
        this.currentImg = currentImg;
    }

    public boolean isNeedDraw() {
        return needDraw;
    }

    public void setNeedDraw(boolean needDraw) {
        this.needDraw = needDraw;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }


}
