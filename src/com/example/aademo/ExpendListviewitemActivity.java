package com.example.aademo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ExpendListviewitemActivity extends BaseActivity {
    ListView lv;
    ArrayList<ExpendBean> arraylist_expendBeans = new ArrayList<ExpendListviewitemActivity.ExpendBean>();
    ExpendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expendlistviewitem_main);
        init();
    }

    private void init() {
        lv = (ListView) findViewById(R.id.expenditem_lv);

        for (int i = 0; i < 50; i++) {
            arraylist_expendBeans.add(new ExpendBean(i + ""));
        }

        adapter= new ExpendAdapter();
        lv.setAdapter(adapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    class ExpendAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arraylist_expendBeans.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button btn = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.expendlistviewitem_item, null);
                btn = (Button) convertView.findViewById(R.id.expendlistviewitem_tv_item);
                convertView.setTag(btn);
            } else {
                btn = (Button) convertView.getTag();
            }
            final String text=arraylist_expendBeans.get(position).text;
            btn.setText(text);
            final View finalConvertView = convertView;
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    arraylist_expendBeans.add(position, new ExpendBean(text + "."));
                    adapter.notifyDataSetChanged();
                    int height= finalConvertView.getHeight();
                    lv.smoothScrollBy(height,200);
//                    smoothScrollByOffset(50);
                }
            });
            return convertView;
        }

    }

    class ExpendBean {
        public ExpendBean(String txt) {
            text = txt;
        }

        String text;
    }
}
