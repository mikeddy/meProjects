package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aademo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mik_eddy on 15/10/10.
 */
public class ButterknifeActivity extends BaseActivity {
    @Bind(R.id.butterknife_btn_1)
    Button btn_1;
    @Bind(R.id.butterknife_tv_1)
    TextView tv_1;
    @Bind(R.id.butterknife_btn_2)
    Button btn_2;
    @Bind(R.id.butterknife_tv_2)
    TextView tv_2;
    @Bind(R.id.butterknife_btn_3)
    Button btn_3;
    @Bind(R.id.butterknife_tv_3)
    TextView tv_3;
    @Bind(R.id.butterknife_btn_4)
    Button btn_4;
    @Bind(R.id.butterknife_tv_4)
    TextView tv_4;


    @OnClick({R.id.butterknife_btn_1, R.id.butterknife_btn_2, R.id.butterknife_btn_3})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.butterknife_btn_1:
                showToast("大家好,我是按钮1");
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
}
