package com.example.aademo.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.impl.ViewAdapter;
import com.example.aademo.widget.CustomViewGroup;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class AnimationOneActivity extends BaseActivity {
    TextView tv_plan,tv_planbottom;
    CustomViewGroup cvg_bg;
    public static final int STARTCOUNT=20;//一共有多少颗星星
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
        tv_plan.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_1_plan));
        tv_planbottom.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_animation_1_planbottom));
        mHandler.sendEmptyMessageDelayed(0, 1000);

    }

    Handler mHandler=new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            loadBgStartAnimation();
        }
    };


    private void loadBgStartAnimation(){
        cvg_bg.setAdapter(new ViewAdapter() {

            @Override
            public int getViewCount() {
                return 20;
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
                return convertView;
            }
        });
        cvg_bg.setDir(CustomViewGroup.DIRECTION.TOP2BTTOM);
        cvg_bg.startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cvg_bg.stop();
    }
}
