package com.example.aademo.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;
import com.example.aademo.bean.TestBean;
import com.example.aademo.util.PalLog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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


    ArrayList<String> mArrayStrs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        for (int i = 0; i < 100; i++) {
            mArrayStrs.add("hello" + i);
        }
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
                rx3();
                break;
        }
    }

    private void rx1() {
        Observable.from(mArrayStrs).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(final String s) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (s.equals("hello1") || s.equals("hello2")) {
                            PalLog.printE("flatmap:" + s + "  do onndex threadid:" + Thread.currentThread().getName());
                            subscriber.onNext(s);
                        } else {
                            PalLog.printE("flatmap:" + s + "  do complete");
                            subscriber.onCompleted();
                        }
                    }
                });
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                PalLog.printE("filter :" + s);
                return s.equals("hello1");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                PalLog.printE("Action" + s);
            }
        });
    }


    private void rx2() {
        TestBean bean = new TestBean();
        bean.setName("hello");
        bean.setAge("12");
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                PalLog.printE("call");
                subscriber.onNext(null);
                subscriber.onCompleted();
                return;
            }
        }).subscribe(new Observer<Void>() {
            @Override
            public void onCompleted() {
                PalLog.printE("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void s) {
                PalLog.printE("onNext");
            }
        });
    }


    Subscriber<Void>mSubscrib=new Subscriber<Void>() {
        @Override
        public void onCompleted() {
            PalLog.printE("========message onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            PalLog.printE("========message onError");
        }

        @Override
        public void onNext(Void aVoid) {
            PalLog.printE("========message onNext");
        }
    };

    Runnable mRunner=new Runnable() {
        @Override
        public void run() {
            PalLog.printE("mSubscrib:"+mSubscrib.isUnsubscribed());
        }
    };

    public void rx3() {
        Handler handler=new Handler();
        handler.postDelayed(mRunner,10000);

        login().filter(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean aBoolean) {
                PalLog.printE("========message2");
                return aBoolean;
            }
        }).flatMap(new Func1<Boolean, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(Boolean aBoolean) {
                PalLog.printE("========message3");
                return authen();
            }
        }).filter(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean aBoolean) {
                PalLog.printE("========message5");
                return aBoolean;
            }
        }).map(new Func1<Boolean, Void>() {
            @Override
            public Void call(Boolean aBoolean) {
                PalLog.printE("========message6");
                return null;
            }
        }).subscribe(mSubscrib);
    }

    boolean isLogin = true;
    boolean isAuthen = true;

    public Observable<Boolean> login() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                PalLog.printE("========message1"+mSubscrib.isUnsubscribed());
                if (isLogin) {
                    subscriber.onNext(true);
                } else {
                    subscriber.onNext(false);
                }
            }
        });
    }

    public Observable<Boolean> authen() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                PalLog.printE("========message4");
                if (isAuthen) {
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                } else {
                    subscriber.onNext(false);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
