package com.example.aademo.activitys;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class GaussoBscureOneActivity extends BaseActivity implements View.OnClickListener {
    TextView tv_ms,tv_tips;
    ImageView img_bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaussobscure_1);
        init();
        applyBlur();
    }


    private void init() {
        img_bg=(ImageView)findViewById(R.id.gauss_img_bg);
        tv_ms=(TextView)findViewById(R.id.gauss_tv_ms);
        tv_tips=(TextView)findViewById(R.id.gauss_tv_text);
    }

    @Override
    public void onClick(View v) {
    }

    private void applyBlur() {
        img_bg.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                img_bg.getViewTreeObserver().removeOnPreDrawListener(this);
                img_bg.buildDrawingCache();
                Bitmap bmp = img_bg.getDrawingCache();
                blur(bmp,tv_tips);
                return true;
            }
        });
    }

    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float radius = 20;

        Bitmap overlay = Bitmap.createBitmap((view.getMeasuredWidth()),(view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft(), -view.getTop());
        canvas.drawBitmap(bkg, 0, 0, null);

        RenderScript rs = RenderScript.create(this);

        Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(radius);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(overlay);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        rs.destroy();

        tv_ms.setText("cost " + (System.currentTimeMillis() - startMs) + "ms");
    }
}
