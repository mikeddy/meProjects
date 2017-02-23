package com.example.aademo.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.aademo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class TestActivity extends Activity {
    @Bind(R.id.textlin)
    LinearLayout lin_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        View v1= View.inflate(this, R.layout.item_test, null);
        lin_parent.addView(v1);

//          LayoutInflater.from(this).inflate(R.layout.item_test, lin_parent, false);

//          LayoutInflater.from(this).inflate(R.layout.item_test, lin_parent, true);
    }
}