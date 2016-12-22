package com.example.aademo.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class MoreItemViewpagerActivity extends Activity {
    ViewPager vp_main;

    SparseArray<View> viewCache = new SparseArray<>();
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreviewpager);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        container=(RelativeLayout)findViewById(R.id.container);
        init();
    }


    private void init() {
        // pageCount设置红缓存的页面数
        vp_main.setOffscreenPageLimit(3);
        // 设置2张图之前的间距。
        vp_main.setPageMargin(10);
        vp_main.setAdapter(new ViewPagerAdapter());
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp_main.dispatchTouchEvent(event);
            }
        });
    }


    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemview = viewCache.get(position);
            if (itemview == null) {
                itemview = View.inflate(MoreItemViewpagerActivity.this, R.layout.item_viewpager, null);
                viewCache.put(position, itemview);
            }
            container.addView(itemview);
            return itemview;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewCache.get(position));
        }

        @Override
        public void notifyDataSetChanged() {
            viewCache.clear();
            super.notifyDataSetChanged();
        }
    }
}