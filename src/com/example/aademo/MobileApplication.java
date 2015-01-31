package com.example.aademo;

import android.app.Application;

/**
 * Created by mik_eddy on 15/1/31.
 */
public class MobileApplication extends Application{

    public static MobileApplication myApplication;

    public static MobileApplication getInstance(){
        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
    }
}
