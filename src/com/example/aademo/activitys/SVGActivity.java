package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.aademo.R;
import com.example.aademo.widget.PathView;

/**
 * Created by mik_eddy on 15/12/25.
 */
public class SVGActivity extends BaseActivity {
    PathView mPathSvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        mPathSvg = (PathView) findViewById(R.id.pathSvg);

//        mPathSvg.setFill(true);
        mPathSvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathSvg.getPathAnimator().delay(100).duration(1500).interpolator(new AccelerateDecelerateInterpolator()).start();
            }
        });
    }
}
