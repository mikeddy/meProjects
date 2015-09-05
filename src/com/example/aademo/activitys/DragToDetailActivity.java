package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.aademo.R;
import com.example.aademo.widget.DragLinearLayout;

/**
 * 上拉加载更多
 * Created by mik_eddy on 15/8/14.
 */
public class DragToDetailActivity extends BaseActivity implements View.OnClickListener {
    DragLinearLayout draglin_parent;
    LinearLayout lin_top,lin_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragtodetail);
        init();
        processLogic();
    }


    private void init() {
        draglin_parent=(DragLinearLayout)findViewById(R.id.drag_lin_parent);
        lin_top=(LinearLayout)findViewById(R.id.drag_lin_top);
        lin_bottom=(LinearLayout)findViewById(R.id.drag_lin_bottom);
    }

    private void processLogic(){
        draglin_parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                draglin_parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                lin_top.getLayoutParams().height=draglin_parent.getHeight();
                lin_bottom.getLayoutParams().height=draglin_parent.getHeight();
                lin_top.invalidate();
                lin_bottom.invalidate();
            }
        });
    }

    @Override
    public void onClick(View v) {
    }
}
