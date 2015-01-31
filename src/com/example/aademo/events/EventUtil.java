package com.example.aademo.events;

import android.app.Fragment;
import android.widget.Toast;

import com.example.aademo.MobileApplication;
import com.example.aademo.activitys.BaseActivity;
import com.example.aademo.util.PalLog;

import de.greenrobot.event.EventBus;

/**
 * Created by mik_eddy on 15/1/31.
 */
public class EventUtil {

    /**
     * 注册event
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus eb = EventBus.getDefault();
        String className=subscriber.getClass().getSimpleName();
        if (subscriber instanceof BaseActivity) {
            PalLog.printE("register:   "+className+"    is a activity");
        } else if (subscriber instanceof Fragment) {
            PalLog.printE("register:   "+className+"    is a fragment");
        } else {
            Toast.makeText(MobileApplication.getInstance(), "类名:"+className +"\n"+
                    "它不是一个有生命周期的组件\n" +
                    "在不使用时无法自动注销\n" +
                    "请确保在其依赖的有生命周期的组件中注销它", Toast.LENGTH_LONG).show();
        }
        eb.register(subscriber);
    }


    /**
     * 发送一个event
     *
     * @param subscriber
     */
    public static void post(Object subscriber) {
        PalLog.printE("post:"+subscriber.getClass().getName());
        EventBus.getDefault().post(subscriber);
    }

    /**
     * 注销一个event
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        PalLog.printE("unregister:"+subscriber.getClass().getName());
        EventBus.getDefault().unregister(subscriber);
    }
}
