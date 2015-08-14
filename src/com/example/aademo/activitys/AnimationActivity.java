package com.example.aademo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimationActivity extends BaseActivity implements View.OnClickListener{
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        init();
    }


    private void init(){
        btn1=(Button)findViewById(R.id.animation_btn_1);
        btn2=(Button)findViewById(R.id.animation_btn_2);
        btn3=(Button)findViewById(R.id.animation_btn_3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animation_btn_1:
                startActivity(new Intent(this,AnimationOneActivity.class));
                break;
            case R.id.animation_btn_2:
                startActivity(new Intent(this,AnimationTwoActivity.class));
                break;
            case R.id.animation_btn_3:
                startActivity(new Intent(this,AnimationThreeActivity.class));
                break;
        }
    }
}
