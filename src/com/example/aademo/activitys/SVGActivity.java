package com.example.aademo.activitys;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.aademo.R;
import com.example.aademo.widget.PathView;

/**
 * Created by mik_eddy on 15/12/25.
 */
public class SVGActivity extends BaseActivity {
    PathView mPathView;
    PathView mPathSvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        mPathView = (PathView) findViewById(R.id.pathView);
        mPathSvg = (PathView) findViewById(R.id.pathSvg);


        setMyPath();

        mPathSvg.useNaturalColors();
        mPathSvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathSvg.getPathAnimator().delay(100).duration(1500).interpolator(new AccelerateDecelerateInterpolator()).start();
            }
        });
    }

    public void setMyPath() {
        final Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(100, 400);
        path.close();


        mPathView.setPath(path);


    }
}
