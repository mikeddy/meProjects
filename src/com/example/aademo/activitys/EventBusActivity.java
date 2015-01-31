package com.example.aademo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;
import com.example.aademo.events.EventUtil;
import com.example.aademo.events.MyTestEvent;
import com.example.aademo.impl.ICallBack;
import com.example.aademo.impl.IEventBusImpl;
import com.example.aademo.widget.EventBusView;

public class EventBusActivity extends BaseActivity implements IEventBusImpl.IEventBusOnEventImpl<MyTestEvent>{
    Button btn_send;
    int onEventMainThreadClickTimes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_main);
        init();
    }

    private void init(){
        EventUtil.register(this);
        EventBusView ebv=new EventBusView();//测试使用
        btn_send=(Button)findViewById(R.id.eventbus_btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTestEvent testEvent = new MyTestEvent();
                testEvent.setTitle("这里是title");
                testEvent.setMessage("这里是message");
                testEvent.setCallback(new ICallBack() {
                    @Override
                    public void callback(int callCode, Object... param) {
                        showToast(param[0].toString());
                    }
                });
                EventUtil.post(testEvent);
            }
        });
    }

    @Override
    public void onEvent(MyTestEvent event) {
        onEventMainThreadClickTimes++;
        btn_send.setText("clicktime is :"+onEventMainThreadClickTimes);
        event.getCallback().callback(0,"hello");
    }
}
