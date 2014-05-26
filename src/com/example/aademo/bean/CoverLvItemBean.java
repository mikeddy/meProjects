package com.example.aademo.bean;

import android.content.res.Resources;

public class CoverLvItemBean {
	public CoverLvItemBean(Resources re) {
		res = re;
	}

	Resources res;
	String name1;// 人名1
	String action;// 动作
	String name2;// 人名2
	String money;// 钱数
	String content;// 内容
	String lasttime;// 最后更新时间

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getName1() {
		return name1 == null ? "" : name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getAction() {
		return action == null ? "" : action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName2() {
		return name2 == null ? "" : name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getMoney() {
		return money == null ? "" : money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * spannable方法改变字体大小,颜色(勿删)
	 */

	// public Spannable getTitleSSpannable(){
	// SpannableStringBuilder ssb=new SpannableStringBuilder();
	// String[] strArr=new
	// String[]{getName1(),getAction(),getName2(),getMoney()};
	// int []Colors=new
	// int[]{R.color.holo_green_light,R.color.holo_blue_bright,R.color.holo_green_light,R.color.black};
	// int []Sizes=new int[]{24,20,24,24};
	// int length=strArr.length;
	// for (int i = 0; i < length; i++) {
	// SpannableString spannableString=new SpannableString(strArr[i]);
	// spannableString.setSpan(new ForegroundColorSpan(res.getColor(Colors[i])),
	// 0,strArr[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// spannableString.setSpan(new AbsoluteSizeSpan(Sizes[i]),
	// 0,strArr[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// ssb.append(spannableString);
	// }
	// return ssb;
	// //span的点击事件
	// // ClickableSpan span = new ClickableSpan() {
	// //
	// // @Override
	// // public void updateDrawState(TextPaint ds) {
	// // super.updateDrawState(ds);
	// // }
	// //
	// // @Override
	// // public void onClick(View widget) {
	// // Spannable spannable = ((Spannable) ((TextView) widget).getText());
	// // Selection.removeSelection(spannable);
	// // ((ChatActivity) context).loadQuestion(subQuestion.getTag());
	// // ((ChatActivity) context).updateType(subQuestion.getType());
	// // }
	// // };

	// }

}
