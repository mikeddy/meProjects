package com.example.aademo.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.aademo.R;
import com.example.aademo.widget.AnimView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimViewActivity extends Activity {
    @Bind(R.id.anim_v_content)
    AnimView animv_content;
    private Timer timer;
    private TimerTask timerTask;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animview);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        animv_content.setDirection(AnimView.UPWARD);
//        animv_content.setDirection(AnimView.DOWNWARD);
        animv_content.start();

        mHandler = new Handler();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        animv_content.stop();
                    }
                });
            }
        };
        timer = new Timer();
//        timer.schedule(timerTask,10000);
    }
}