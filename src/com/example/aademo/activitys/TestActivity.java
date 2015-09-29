package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class TestActivity extends BaseActivity{
    LinearLayout lin_1,lin_2;
    Button btn_1,btn_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    


    private void init(){
        lin_1=(LinearLayout)findViewById(R.id.test_lin_1);
        lin_2=(LinearLayout)findViewById(R.id.test_lin_2);
        btn_1=(Button)findViewById(R.id.test_btn_1);
        btn_2=(Button)findViewById(R.id.test_btn_2);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("lin1:"+lin_1.getWidth());
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("lin2:"+lin_2.getWidth());
            }
        });
    }


    public int getChildWidth(LinearLayout lin){
        int size=lin.getChildCount();
        int sub=0;
        for (int i = 0; i < size; i++) {
            sub+=lin.getChildAt(i).getWidth();
        }
        return sub;
    }
}
