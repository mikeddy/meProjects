package com.example.aademo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/10/10.
 */
public class OptimizeUIActivity extends BaseActivity implements View.OnClickListener{
    Button btn_1_0,btn_1_1,btn_2_0,btn_2_1,btn_3_0,btn_3_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimizeui);
        init();
    }

    private void init(){
        btn_1_0=(Button)findViewById(R.id.optimize_btn_1);
        btn_1_1=(Button)findViewById(R.id.optimize_btn_1_1);
        btn_2_0=(Button)findViewById(R.id.optimize_btn_2);
        btn_2_1=(Button)findViewById(R.id.optimize_btn_2_1);
        btn_3_0=(Button)findViewById(R.id.optimize_btn_3);
        btn_3_1=(Button)findViewById(R.id.optimize_btn_3_1);

        btn_1_0.setOnClickListener(this);
        btn_1_1.setOnClickListener(this);
        btn_2_0.setOnClickListener(this);
        btn_2_1.setOnClickListener(this);
        btn_3_0.setOnClickListener(this);
        btn_3_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.optimize_btn_1:
                startActivity(new Intent(mContext,OptimizeUI_1_0Activity.class));
                break;
            case R.id.optimize_btn_1_1:
                startActivity(new Intent(mContext,OptimizeUI_1_1Activity.class));
                break;
            case R.id.optimize_btn_2:
                startActivity(new Intent(mContext,OptimizeUI_2_0Activity.class));
                break;
            case R.id.optimize_btn_2_1:
                startActivity(new Intent(mContext,OptimizeUI_2_1Activity.class));
                break;
            case R.id.optimize_btn_3:
                startActivity(new Intent(mContext,OptimizeUI_3_0Activity.class));
                break;
            case R.id.optimize_btn_3_1:
                startActivity(new Intent(mContext,OptimizeUI_3_1Activity.class));
                break;
        }
    }
}
