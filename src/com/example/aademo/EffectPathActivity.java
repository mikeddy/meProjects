package com.example.aademo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.widget.EffectView;

/**
 * Created by mik_eddy on 14/12/22.
 * 初步简单实现,如需实际使用,需要自己考虑并完善
 */
public class EffectPathActivity extends  BaseActivity implements View.OnClickListener{
    Button btn_1,btn_2,btn_3,btn_4;
    EffectView efView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.effect_main);
        init();
    }

    private void init(){
        btn_1=(Button)findViewById(R.id.effect_btn1);
        btn_2=(Button)findViewById(R.id.effect_btn2);
        btn_3=(Button)findViewById(R.id.effect_btn3);
        btn_4=(Button)findViewById(R.id.effect_btn4);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);

        efView=(EffectView)findViewById(R.id.effect_v_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.effect_btn1:
                efView.startType(1);
                break;
            case R.id.effect_btn2:
                efView.startType(2);
                break;
            case R.id.effect_btn3:
                break;
            case R.id.effect_btn4:
                break;
            default:
                break;
        }
    }
}
