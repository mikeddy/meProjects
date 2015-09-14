package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aademo.R;

/**
 * 上拉加载更多
 * Created by mik_eddy on 15/9/9.
 */
public class TwoDirectListViewActivity extends BaseActivity implements View.OnClickListener {
    ListView lv_content;
    TwoDirectAdapter adapter_todirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twodirectlistview);
        init();
        processLogic();
    }


    private void init() {
        lv_content = (ListView) findViewById(R.id.twodirect_lv_content);
    }

    private void processLogic() {
        adapter_todirect = new TwoDirectAdapter();
        lv_content.setAdapter(adapter_todirect);
    }

    @Override
    public void onClick(View v) {
    }


    class TwoDirectAdapter extends BaseAdapter {

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
            TextView tv_title;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_twodirect, null);
                tv_title = (TextView) convertView.findViewById(R.id.item_twodirect_tv_title);
                convertView.setTag(tv_title);
            } else {
                tv_title = (TextView) convertView.getTag();
            }
            tv_title.setText("第" + position + "行");
            return convertView;
        }
    }
}
