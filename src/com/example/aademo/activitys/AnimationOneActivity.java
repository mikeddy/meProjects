package com.example.aademo.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.impl.ViewAdapter;
import com.example.aademo.util.DensityUtils;
import com.example.aademo.util.PalLog;
import com.example.aademo.widget.CustomViewGroup;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimationOneActivity extends BaseActivity {
    TextView tv_plan,tv_planbottom;
    CustomViewGroup cvg_bg;
    Random mRandom=new Random();
    ArrayList<Integer>arlit_startIco=new ArrayList<Integer>();

    public static final int STARTCOUNT=10;//一共有多少颗星星
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
        cvg_bg=(CustomViewGroup)findViewById(R.id.animtion1_cvg_bg);
    }

    private void processLogic() {

        arlit_startIco.add(R.drawable.draw_animation_star);
        arlit_startIco.add(R.drawable.draw_animation_star_1);
        arlit_startIco.add(R.drawable.draw_animation_star_2);
        arlit_startIco.add(R.drawable.draw_animation_star_3);

        Animation anim=AnimationUtils.loadAnimation(this, R.anim.anim_animation_1_plan);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mShakeHandler.sendEmptyMessageDelayed(0,2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tv_plan.startAnimation(anim);
        tv_planbottom.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_1_planbottom));
        mHandler.sendEmptyMessageDelayed(0, 1000);

    }
    Handler mShakeHandler=new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            shakeView(tv_plan, 7, 200);
            sendEmptyMessageDelayed(0,3000);
        }
    };

    Handler mHandler=new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            loadBgStartAnimation();
        }
    };

    public static void shakeView(View v, float offset, int dura) {
        float tPositive=v.getTop()-offset;
        float tMinus=v.getTop()+offset;
        float tSource=v.getTop();
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, "y", tPositive, tMinus,tPositive, tMinus, tSource);
        animX.setDuration(dura);
        animX.start();
    }


    private void loadBgStartAnimation(){
        cvg_bg.setAdapter(new ViewAdapter() {

            @Override
            public int getViewCount() {
                return STARTCOUNT;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView img;
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_animation_star, null);
                    img = ((ImageView) convertView.findViewById(R.id.item_animation_img_star));
                    convertView.setTag(img);
                } else {
                    img = (ImageView) convertView.getTag();
                }
                int nextInt=mRandom.nextInt(4);
                img.setImageResource(arlit_startIco.get(nextInt));
                int tIntRange=DensityUtils.dp2px(mContext, (nextInt + 2) * 5);
                img.setLayoutParams(new LinearLayout.LayoutParams(tIntRange,tIntRange));
                return convertView;
            }
        });
        cvg_bg.setDir(CustomViewGroup.DIRECTION.TOP2BTTOM);
        cvg_bg.startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PalLog.printD("=======aaaa");
        cvg_bg.stopAnimation();
    }
}
