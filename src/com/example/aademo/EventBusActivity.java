package com.example.aademo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.events.MyTestEvent;

import de.greenrobot.event.EventBus;

/**
 * onEvent，onEventMainThread，onEventBackgroundThread，onEventAsync
 */
public class EventBusActivity extends BaseActivity {
    Button btn_send;
    EventBus eventBus;

    int onEventMainThreadClickTimes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_main);
        init();
    }

    private void init(){
        eventBus=EventBus.getDefault();
        eventBus.register(this);
        btn_send=(Button)findViewById(R.id.eventbus_btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTestEvent testEvent=new MyTestEvent();
                testEvent.setTitle("这里是title");
                testEvent.setMessage("这里是message");
                eventBus.post(testEvent);
            }
        });
    }

    public void onEventMainThread(MyTestEvent event){
        onEventMainThreadClickTimes++;
        showToast(event.getTitle()+"\n"+event.getMessage());
        btn_send.setText("onEventMainThread_clicktime is :"+onEventMainThreadClickTimes);
    }
}
