package com.example.aademo.bean;

import java.util.Random;

import android.view.View;

public class MoveBean {
	int speed;
	int potinX;
	int pointY;
	
	public MoveBean(View v,int width,int height) {
		speed = new Random().nextInt(10);
		speed += 5;// 速度范围5-15px;
		potinX = new Random().nextInt(width - v.getMeasuredWidth());
		pointY = height;
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