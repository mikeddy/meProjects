package com.example.aademo.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.util.MetaData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class DeviceInfoActivity extends Activity {
    @Bind(R.id.deviceinfo_tv_content)
    TextView tv_devicecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceinfo);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        tv_devicecontent.setText(printScreenParam(this));
    }


    // 打印屏幕参数
    public static String printScreenParam(Activity ac) {
        StringBuilder sb=new StringBuilder();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ac.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        MetaData  metdata=new MetaData(ac);
        sb.append("=============================屏幕参数==============================");
        sb.append("\n");
        sb.append("++++++     屏幕宽度:" + displayMetrics.widthPixels + "px");
        sb.append("\n");
        sb.append("++++++     屏幕高度:" + displayMetrics.heightPixels + "px");
        sb.append("\n");
        sb.append("++++++     X方向每英尺像素:" + displayMetrics.xdpi + "px");
        sb.append("\n");
        sb.append("++++++     Y方向每英尺像素:" + displayMetrics.ydpi + "px");
        sb.append("\n");
        sb.append("++++++     屏幕密度: px=" + displayMetrics.density + "*dip");
        sb.append("\n");
        float densityDPi=displayMetrics.densityDpi ;
        String s="";
        if(densityDPi>0&&densityDPi<=120)s="ldpi";
        else if(densityDPi>120&&densityDPi<=160)s="mdpi";
        else if(densityDPi>160&&densityDPi<=240)s="hdpi";
        else if(densityDPi>240&&densityDPi<=320)s="xhdpi";
        else if(densityDPi>320)s="xxhdpi";
        sb.append("++++++     DPI:" +densityDPi + "属于:"+s);
        sb.append("\n");
        sb.append("++++++     应用名称:"+metdata.getAppId());
        sb.append("\n");
        sb.append("++++++     操作系统平台:"+metdata.getPlatform());
        sb.append("\n");
        sb.append("++++++     操作系统版本:"+metdata.getOsVersion());
        sb.append("\n");
        sb.append("++++++     手机语言:"+metdata.getLanguage());
        sb.append("\n");
        sb.append("++++++     手机设备唯一ID:"+metdata.getDeviceId());
        sb.append("\n");
        sb.append("++++++     国际国家码:"+metdata.getMcc());
        sb.append("\n");
        sb.append("++++++     手机运营商码:"+metdata.getMnc());
        sb.append("\n");
        sb.append("++++++     当前应用版本:"+metdata.getVersion());
        sb.append("\n");
        sb.append("++++++     设备型号,厂商:"+metdata.getModuleName());
        sb.append("\n");
        sb.append("++++++     是否ROOT:"+metdata.isRoot());
        sb.append("\n");
        sb.append("++++++     经度,纬度:"+metdata.getLongitude()+","+metdata.getLatitude());
        sb.append("\n");
        sb.append("++++++     IMEI:"+metdata.getImei());
        sb.append("\n");
        sb.append("++++++     IMSI:"+metdata.getImsi());
        sb.append("\n");
        sb.append("++++++     网络类型:"+metdata.getNetworkType());
        sb.append("\n");
        sb.append("=============================屏幕参数==============================");
        return sb.toString();
    }
}