package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.aademo.R;
import com.example.aademo.util.AnimationUtil;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimationThreeActivity extends BaseActivity {
    ImageView img_light,img_satellite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_3);
        init();
        processLogic();
    }


    private void init(){
        img_light=(ImageView)findViewById(R.id.animation_img_light);
        img_satellite=(ImageView)findViewById(R.id.animation_img_satellite);
    }

    private void processLogic() {
        AnimationSet tAnimSet=new AnimationSet(true);
        tAnimSet.addAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_animation_3_satellite));
        tAnimSet.addAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_3_satelliterecover));
        tAnimSet.addAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_3_light));
        tAnimSet.addAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_3_light2dark));
        AnimationUtil.repeatAnimation(tAnimSet, img_light);

        AnimationSet tAnimSet2=new AnimationSet(true);
        tAnimSet2.addAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_animation_3_satellite));
        tAnimSet2.addAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_3_satelliterecover));
        AnimationUtil.repeatAnimation(tAnimSet2,img_satellite);

    }

}
