package com.example.aademo.activitys;

import android.os.Bundle;

import com.example.aademo.R;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.activity_test);
    }
}
