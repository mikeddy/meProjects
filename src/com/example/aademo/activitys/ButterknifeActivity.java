package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.aademo.R;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by mik_eddy on 15/10/10.
 */
public class ButterknifeActivity extends BaseActivity {


    @BindColor(R.color.yellow)
    int intColorYellow;


    @OnCheckedChanged(R.id.butterknife_cb_1)
    void onCheckChange(CheckBox cb, boolean sel) {
        showToast(cb.isChecked() + "   sel:" + sel);
    }


    @OnClick({R.id.butterknife_btn_1, R.id.butterknife_btn_2, R.id.butterknife_btn_3})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.butterknife_btn_1:
                showToast("大家好,我是按钮1");
                butterknifeBtn1.setTextColor(intColorYellow);
                break;
            case R.id.butterknife_btn_2:
                showToast("大家好,我是按钮2");
                break;
            case R.id.butterknife_btn_3:
                showToast("大家好,我是按钮3");
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'activity_butterknife.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.butterknife_btn_1)
        Button butterknifeBtn1;
        @Bind(R.id.butterknife_tv_1)
        TextView butterknifeTv1;
        @Bind(R.id.butterknife_btn_2)
        Button butterknifeBtn2;
        @Bind(R.id.butterknife_tv_2)
        TextView butterknifeTv2;
        @Bind(R.id.butterknife_btn_3)
        Button butterknifeBtn3;
        @Bind(R.id.butterknife_tv_3)
        TextView butterknifeTv3;
        @Bind(R.id.butterknife_btn_4)
        Button butterknifeBtn4;
        @Bind(R.id.butterknife_tv_4)
        TextView butterknifeTv4;
        @Bind(R.id.butterknife_cb_1)
        CheckBox butterknifeCb1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
