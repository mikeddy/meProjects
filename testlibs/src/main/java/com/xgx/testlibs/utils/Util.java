package com.xgx.testlibs.utils;

import android.util.Log;

import com.xgx.testlibs.ac.HelloActivity;

/**
 * Created by mik_eddy on 14/12/25.
 */
public class Util {

    public void MyUtil(){
        HelloActivity helloActivity=new HelloActivity();
        Log.v("util","this is my util");
        helloActivity.hello();
    }
}
