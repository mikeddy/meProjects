package com.example.aademo.widget;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by mik_eddy on 14/12/22.
 */
public class MyEffectPath {
    ArrayList<MyPath>arraylist_Mypath=new ArrayList<MyPath>();

    public void clear(){
        arraylist_Mypath.clear();
    }

    /**
     * 执行动画的时间轴百分比
     * @param startRatio  动画起点时间轴半分比
     * @param endRatio 动画结束时间轴半分比
     * @return 比如一个动画一共需要2秒,此时startRatio 为0.4,endRatio为0.6,则这个动画的执行时间轴是 2*0.4-2*0.6 即为0.8秒-1.2秒
     *
     */
    public MyPath createNew(float startRatio,float endRatio){
        MyPath path=new MyPath(startRatio,endRatio);
        arraylist_Mypath.add(path);
        return path;
    }

    /**
     * 开始执行绘画动画
     * @param canvas  对应View的画布
     * @param ratio   动画时间轴
     */
    public void draw(Canvas canvas,float ratio){
        int size=arraylist_Mypath.size();
        for (int i = 0; i < size; i++) {
            MyPath path=arraylist_Mypath.get(i);
            path.calculateRatio(ratio);
            path.makePathEffect();
            canvas.drawPath(path.getPath(),path.getPaint());
        }
    }
}
