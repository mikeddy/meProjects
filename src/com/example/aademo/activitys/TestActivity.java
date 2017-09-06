package com.example.aademo.activitys;

import android.os.Bundle;

import com.example.aademo.R;
import com.example.aademo.widget.MarqueeTextView;

public class TestActivity extends BaseActivity {
     MarqueeTextView marqueeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.activity_test);
        marqueeTextView=(MarqueeTextView)findViewById(R.id.marqueetext);
        marqueeTextView.setText("aaaabbbbccc");
    }
}
