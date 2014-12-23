package com.example.aademo.events;

/**
 * Created by mik_eddy on 14/12/23.
 */
public class BaseEvent {
    int code;//传递的code码
    Object obj;//传递的object

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
