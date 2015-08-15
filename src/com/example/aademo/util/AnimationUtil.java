package com.example.aademo.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

/**
 * Created by mik_eddy on 15/8/15.
 */
public class AnimationUtil {
    public static void repeatAnimation(final AnimationSet mAnimSet, final View v){
        mAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.startAnimation(mAnimSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mAnimSet.setFillAfter(true);
        mAnimSet.setFillBefore(true);
        v.startAnimation(mAnimSet);
    }
}
