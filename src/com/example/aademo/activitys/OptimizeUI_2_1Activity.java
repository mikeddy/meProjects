package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aademo.R;

/**
 *
 * Created by mik_eddy on 15/10/10.
 */
public class OptimizeUI_2_1Activity extends BaseActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimizeui_1_0);
        init();
    }

    private void init() {
        lv = (ListView) findViewById(R.id.optimize_lv);
        lv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
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
            MyHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_optimizeui_2_1, null);
                holder = new MyHolder();
                holder.tv_title = (TextView) convertView.findViewById(R.id.optimize_tv_top);
                holder.tv_content = (TextView) convertView.findViewById(R.id.optimize_tv_bottom);
                convertView.setTag(holder);
            } else {
                holder = (MyHolder) convertView.getTag();
            }
            holder.tv_title.setText("大家好:" + position);
            holder.tv_content.setText("大家好才是真的好:" + position);
            return convertView;
        }
    }


    class MyHolder {
        TextView tv_title;
        TextView tv_content;
    }
}
