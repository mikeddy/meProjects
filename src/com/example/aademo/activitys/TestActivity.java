package com.example.aademo.activitys;

import android.os.Bundle;

import com.example.aademo.R;
import com.hanks.htextview.evaporate.EvaporateTextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TestActivity extends BaseActivity {

    @Bind(R.id.evatext)
    EvaporateTextView tv_eva1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        Observable.timer(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).repeat().subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                int aa=new Random().nextInt(10000);
                tv_eva1.animateText(aa+"aaa");
            }
        });
    }
}
