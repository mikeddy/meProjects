package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimationOneActivity extends BaseActivity {
    TextView tv_plan,tv_planbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_1);
        init();
        processLogic();
    }


    private void init(){
        tv_plan=(TextView)findViewById(R.id.animtion1_plan);
        tv_planbottom=(TextView)findViewById(R.id.animtion1_planbottom);
    }

    private void processLogic() {
        tv_plan.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_animation_1_plan));
        tv_planbottom.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_animation_1_planbottom));
    }

}
