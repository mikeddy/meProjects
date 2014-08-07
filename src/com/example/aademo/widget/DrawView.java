package com.example.aademo.widget;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
public class DrawView extends View {
	private Point[] mPoints = new Point[2];
	private int Q_START = 0;
	private int Q_END = 1;
	private Double[] endPointCoord;
	float radian;
	float banjing=0;
	float xiaoyuanbanjing=0;
	Bitmap xiapquan;
	float xiaodianweizhi;
	float neiquan;
	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
    public void putPercent(float percent){
    	 endPointCoord=new Double[2];
    	 jisuanradian(percent);
    }
    public void setWidth(float banjing,float neiquan,float xiaoyuanbanjing){
    	this.banjing=banjing;
    	this.xiaoyuanbanjing=xiaoyuanbanjing;
    	this.neiquan=neiquan;
    	xiaodianweizhi=banjing/2;
    	Log.d("banjing",""+banjing);
    }
    
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.GREEN);
		p.setAntiAlias(true);
		p.setStyle(Paint.Style.FILL);
		p.setStrokeWidth(16);
		RectF oval1 = new RectF(0, 0, banjing, banjing);//float left, float top, float right, float bottom
		canvas.drawArc(oval1, (float)radian-90, 360-(float)radian, true, p);
//	    xiapquan=BitmapFactory.decodeResource(getResources(), R.drawable.heimaihall_dian);
		p.setColor(Color.RED);
		p.setStyle(Style.FILL);
		p.setStrokeWidth(2);
		canvas.drawCircle(banjing/2, xiaoyuanbanjing/2, xiaoyuanbanjing/2+2,p);
		canvas.drawCircle(endPointCoord[0].floatValue(), endPointCoord[1].floatValue(), xiaoyuanbanjing/2+2, p);
	    
		p.setColor(Color.WHITE);
		p.setStyle(Style.STROKE);
		p.setStrokeWidth(6);
		canvas.drawCircle(banjing/2, xiaoyuanbanjing/2, xiaoyuanbanjing/2,p);
		canvas.drawCircle(endPointCoord[0].floatValue(), endPointCoord[1].floatValue(), xiaoyuanbanjing/2, p);
	 
//	    Bitmap newBitMap=Bitmap.createScaledBitmap(xiapquan,(int) xiaoyuanbanjing+1, (int)xiaoyuanbanjing+1, true);
	}
	
	private void jisuansin(double radian){
		double x=0;
		double y=0;
		if(radian<=90){
			 x=xiaodianweizhi+Math.abs(Math.sin((double)radian*Math.PI/180)*(banjing/2-((banjing-neiquan)/4)));
		     y=xiaodianweizhi-Math.abs(Math.cos((double)radian*Math.PI/180)*(banjing/2-((banjing-neiquan)/4)));
		}if(radian>90&&radian<=180){
			 x=xiaodianweizhi+Math.abs(Math.sin((double)((180-radian)*Math.PI/180))*(banjing/2-((banjing-neiquan)/4)));
			 y=xiaodianweizhi+Math.abs(Math.cos((double)((180-radian)*Math.PI/180))*(banjing/2-((banjing-neiquan)/4)));
		}
		if(radian>180&&radian<=270){
			 x=xiaodianweizhi-Math.abs(Math.cos((double)(((270-radian)*(Math.PI/180))))*(banjing/2-((banjing-neiquan)/4)));
			 y=xiaodianweizhi+Math.abs(Math.sin((double)(((270-radian)*(Math.PI/180))))*(banjing/2-((banjing-neiquan)/4)));
		}
		if(radian>270){
			 x=xiaodianweizhi-Math.abs(Math.sin(((double)((360-radian)*Math.PI/180)))*(banjing/2-((banjing-neiquan)/4)));
			 y=xiaodianweizhi-Math.abs(Math.cos(((double)((360-radian)*Math.PI/180)))*(banjing/2-((banjing-neiquan)/4)));
		}
		endPointCoord[0]=x;
		endPointCoord[1]=y;
	}
	private void jisuanradian(float percent){
	    radian=(float)percent/100*360;
		jisuansin(radian);
	}
	

}
