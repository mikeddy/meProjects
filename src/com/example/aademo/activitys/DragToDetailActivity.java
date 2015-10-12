package com.example.aademo.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aademo.R;
import com.example.aademo.widget.DragLinearLayout;

/**
 * 上拉加载更多
 * Created by mik_eddy on 15/8/14.
 */
public class DragToDetailActivity extends BaseActivity implements View.OnClickListener {
    DragLinearLayout draglin_parent;
    LinearLayout lin_top, lin_bottom;
    ListView lv_content;
    int mIntHeight;
    int mIntWidth;
    public static final int MAXVIEWTYPE = 2;
    public static final int TYPE_HEAD = 0, TYPE_NORMAL = 1;

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
        lv_content = (ListView) findViewById(R.id.drag_lv_content);
    }

    private void processLogic() {
        draglin_parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                draglin_parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                lin_top.getLayoutParams().height = draglin_parent.getHeight();
                lin_bottom.getLayoutParams().height = draglin_parent.getHeight();
                mIntHeight = draglin_parent.getHeight();
                mIntWidth = draglin_parent.getWidth();
                lin_top.invalidate();
                lin_bottom.invalidate();
            }
        });
        lv_content.setAdapter(new BottomAdapter());
        draglin_parent.registerBottomListView(lv_content);
    }

    @Override
    public void onClick(View v) {
    }


    class BottomAdapter extends BaseAdapter {

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return TYPE_HEAD;
            return TYPE_NORMAL;
        }

        @Override
        public int getViewTypeCount() {
            return MAXVIEWTYPE;
        }

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int viewtype=getItemViewType(position);
            if(viewtype==TYPE_HEAD){
                return getHeadView(position,convertView,parent);
            }else if(viewtype==TYPE_NORMAL){
                return getNormalView(position,convertView,parent);
            }
            return null;
        }

        public View getHeadView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(mContext);
                AbsListView.LayoutParams layoutParam = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                tv.setLayoutParams(layoutParam);
                tv.setTextSize(60);
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                convertView=tv;
            } else {
                tv=(TextView)convertView;
            }
            tv.setText("我是head");
            return convertView;
        }

        public View getNormalView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(mContext);
                AbsListView.LayoutParams layoutParam = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
                tv.setLayoutParams(layoutParam);
                tv.setText(position + "");
                tv.setTextSize(30);
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tv.requestLayout();
                convertView = tv;
            } else {
                tv = (TextView) convertView;
            }
            tv.setText("==" + position + "==");

            return convertView;
        }
    }
}
