package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by mik_eddy on 2017/3/30.
 */

public class RxAndroidActivity extends BaseActivity {

    @Bind(R.id.rx_btn_1)
    Button btn_rx1;
    @Bind(R.id.rx_btn_2)
    Button btn_rx2;
    @Bind(R.id.rx_btn_3)
    Button btn_rx3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.rx_btn_1, R.id.rx_btn_2, R.id.rx_btn_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rx_btn_1:
                rx1();
                break;
            case R.id.rx_btn_2:
                break;
            case R.id.rx_btn_3:
                break;
        }
    }

    private void rx1() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                showToast("hello!");
            }
        }).subscribeOn(Schedulers.io())
        .subscribe();
    }
}
