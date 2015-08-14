package com.example.aademo.bean;

import android.view.View;

import java.util.Random;

/**
 * 用来记录每一个移动的View的当前位置的对象
 */
public class MoveBean {
	int speed;
	int potinX;
	int pointY;
	
	public MoveBean(View v,int startx,int starty) {
		speed = new Random().nextInt(10);
		speed += 5;// 速度范围5-15px;
		potinX = new Random().nextInt(startx - v.getMeasuredWidth());
		pointY = starty;
	}
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getPotinX() {
		return potinX;
	}

	public void setPotinX(int potinX) {
		this.potinX = potinX;
	}

	public int getPointY() {
		return pointY;
	}

	public void setPointY(int pointY) {
		this.pointY = pointY;
	}
}