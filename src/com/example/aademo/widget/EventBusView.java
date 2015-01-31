package com.example.aademo.widget;

import com.example.aademo.events.EventBusViewEvent;
import com.example.aademo.events.EventUtil;
import com.example.aademo.impl.IEventBusImpl;

/**
 * Created by mik_eddy on 15/1/31.
 */
public class EventBusView implements IEventBusImpl.IEventBusOnEventImpl<EventBusViewEvent>{
    public EventBusView(){
        EventUtil.register(this);
    }

    @Override
    public void onEvent(EventBusViewEvent event) {

    }
}
