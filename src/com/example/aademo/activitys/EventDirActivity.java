package com.example.aademo.activitys;

import android.os.Bundle;

import com.example.aademo.R;

import butterknife.ButterKnife;

/**
 * Created by mik_eddy on 15/10/10.
 */
public class EventDirActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdir);
        ButterKnife.bind(this);
    }
}
