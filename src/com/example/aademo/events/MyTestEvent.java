package com.example.aademo.events;

import com.example.aademo.impl.ICallBack;

/**
 * Created by mik_eddy on 14/12/16.
 */
public class MyTestEvent extends BaseEvent{
    String title,message;
    ICallBack mCallback;

    public void setCallback(ICallBack mCallback) {
        this.mCallback = mCallback;
    }

    public ICallBack getCallback() {
        return mCallback;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
