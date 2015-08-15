package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.aademo.R;
import com.example.aademo.util.AnimationUtil;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimationTwoActivity extends BaseActivity {
    ImageView img_pocket,img_ufo,img_redgift,img_light;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_2);
        init();
        processLogic();
    }


    private void init(){
        img_light=(ImageView)findViewById(R.id.animation2_img_light);
        img_pocket=(ImageView)findViewById(R.id.animation2_img_pocket);
        img_ufo=(ImageView)findViewById(R.id.animation2_img_ufo);
        img_redgift=(ImageView)findViewById(R.id.animation2_img_redgift);
    }

    private void processLogic() {
        img_ufo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_2_ufo));
        img_pocket.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_animation_2_pocket));
        img_light.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_animation_2_light));
        Animation tAnimRedgift=AnimationUtils.loadAnimation(this, R.anim.anim_animation_2_redgift);
        tAnimRedgift.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AnimationSet tAnimSet=new AnimationSet(true);
                tAnimSet.addAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_animation_2_redgift_up));
                tAnimSet.addAnimation(AnimationUtils.loadAnimation(mContext,R.anim.anim_animation_2_redgift_down));
                AnimationUtil.repeatAnimation(tAnimSet, img_redgift);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        img_redgift.startAnimation(tAnimRedgift);
    }

}
