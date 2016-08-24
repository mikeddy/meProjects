package com.example.aademo.activitys;

import android.app.Activity;
import android.os.Bundle;

import com.example.aademo.R;
import com.example.aademo.widget.AnimView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimViewActivity extends Activity {
    @Bind(R.id.anim_v_content)
    AnimView animv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animview);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        animv_content.start();
    }
}