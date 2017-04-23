package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;
import com.example.aademo.bean.TestBean;
import com.example.aademo.util.PalLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
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
                rx2();
                break;
            case R.id.rx_btn_3:
                break;
        }
    }

    private void rx1() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                PalLog.printE("hello rx1=====");
                subscriber.onNext("hi========");
            }
        }).subscribeOn(Schedulers.io())
          .filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                PalLog.printE(s+"2");
                return true;
            }
        }).flatMap(new Func1<String, Observable<TestBean>>() {
            @Override
            public Observable<TestBean> call(String s) {
                return Observable.create(new Observable.OnSubscribe<TestBean>() {
                    @Override
                    public void call(Subscriber<? super TestBean> subscriber) {

                    }
                });
            }
        }).filter(new Func1<TestBean, Boolean>() {
            @Override
            public Boolean call(TestBean testBean) {
                return true;
            }
        }).subscribe();
    }


    private void  rx2(){
        Observable.just("hello1").map(new Func1<String, TestBean>() {
            @Override
            public TestBean call(String s) {
                PalLog.printE("mapcall"+s);
                TestBean bean=new TestBean();
                bean.setAge("12");
                bean.setName("小明");
                return bean;
            }
        }).flatMap(new Func1<TestBean, Observable<TestBean>>() {
            @Override
            public Observable<TestBean> call(final TestBean testBean) {
                PalLog.printE(testBean.getAge()+"=====flatMap1");
                PalLog.printE(testBean.getName()+"=====flatMap1");
                return Observable.create(new Observable.OnSubscribe<TestBean>(){

                    @Override
                    public void call(Subscriber<? super TestBean> subscriber) {
                        PalLog.printE("flatMap1 call");
                        subscriber.onNext(testBean);
                    }
                });
            }
        }).flatMap(new Func1<TestBean, Observable<TestBean>>() {
            @Override
            public Observable<TestBean> call(final TestBean testBean) {
                PalLog.printE("flatmap2");
                return Observable.create(new Observable.OnSubscribe<TestBean>() {
                    @Override
                    public void call(Subscriber<? super TestBean> subscriber) {
                        PalLog.printE("flatMap2 call");
//                        subscriber.onNext(testBean);
                        subscriber.onCompleted();
                    }
                });
            }
        }).subscribe(new Subscriber<TestBean>() {
            @Override
            public void onCompleted() {
                PalLog.printE("subscribe onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                PalLog.printE("subscribe error");
            }

            @Override
            public void onNext(TestBean testBean) {
                PalLog.printE("subscribe onNext");
            }
        });
    }
}
