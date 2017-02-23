package com.example.aademo.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aademo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mik_eddy on 2017/2/23.
 */

public class RecyclerViewActivity extends BaseActivity {

    @Bind(R.id.id_recyclerview)
    RecyclerView recycview_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recycview_content.setLayoutManager(new LinearLayoutManager(this));
        recycview_content.setAdapter(new MyRecycAdapter());
    }


    class MyRecycAdapter extends RecyclerView.Adapter<RecycHolder> {

        @Override
        public RecycHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//          RecycHolder holder = new RecycHolder(View.inflate(mContext, R.layout.item_recyclerholder, null));
//            RecycHolder holder = new RecycHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerholder, parent, false));
          RecycHolder holder = new RecycHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerholder, parent, true));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecycHolder holder, int position) {
            holder.tv_one.setText("position:" + position);
            holder.tv_two.setText("第" + position + "个");
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class RecycHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_tv_one)
        TextView tv_one;
        @Bind(R.id.recycler_tv_two)
        TextView tv_two;

        public RecycHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
//    class MyAdapter extends RecyclerView.Adapter<>


}
