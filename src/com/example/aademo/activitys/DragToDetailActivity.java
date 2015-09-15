package com.example.aademo.activitys;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.widget.DragLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 上拉加载更多
 * Created by mik_eddy on 15/8/14.
 */
public class DragToDetailActivity extends BaseActivity implements View.OnClickListener {
    DragLinearLayout draglin_parent;
    LinearLayout lin_top, lin_bottom;
    ViewPager vp_content;
    int mIntHeight;
    int mIntWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragtodetail);
        init();
        processLogic();
    }


    private void init() {
        draglin_parent = (DragLinearLayout) findViewById(R.id.drag_lin_parent);
        lin_top = (LinearLayout) findViewById(R.id.drag_lin_top);
        lin_bottom = (LinearLayout) findViewById(R.id.drag_lin_bottom);
        vp_content = (ViewPager) findViewById(R.id.dragitem_vp_content);
    }

    private void processLogic() {
        draglin_parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                draglin_parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                lin_top.getLayoutParams().height = draglin_parent.getHeight();
                lin_bottom.getLayoutParams().height = draglin_parent.getHeight();
                mIntHeight=draglin_parent.getHeight();
                mIntWidth=draglin_parent.getWidth();
                lin_top.invalidate();
                lin_bottom.invalidate();
            }
        });
        vp_content.setAdapter(new ViewPagerAdapter());
    }

    @Override
    public void onClick(View v) {
    }


    class ViewPagerAdapter extends PagerAdapter {

        List<TextView> viewLists=new ArrayList<TextView>();

        public ViewPagerAdapter(){
            for (int i=0;i<10;i++){
                TextView tv=new TextView(mContext);
                viewLists.add(tv);
            }

        }

        @Override
        public int getCount() {//获得size
            // TODO Auto-generated method stub
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }


        @Override
        public void destroyItem(View view, int position, Object object)//销毁Item
        {
            ((ViewPager) view).removeView(viewLists.get(position));
        }

        @Override
        public Object instantiateItem(View view, int position)//实例化Item
        {
            TextView tv=viewLists.get(position);
            tv.setText(position+"");
            tv.setTextSize(50);
            tv.setLayoutParams(new ViewPager.LayoutParams());
            tv.getLayoutParams().height=mIntHeight;
            tv.getLayoutParams().width=mIntWidth;
            tv.setGravity(Gravity.CENTER);
            tv.requestLayout();
            ((ViewPager) view).addView(tv, 0);

            return viewLists.get(position);
        }

    }
}
