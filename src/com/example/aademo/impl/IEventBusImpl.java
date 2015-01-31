package com.example.aademo.impl;

import com.example.aademo.events.BaseEvent;

/**
 * ================================
 * onEvent:
 * 事件的处理在和事件的发送在相同的进程,所以事件处理时间不应太长
 * 不然影响事件的发送线程,而这个线程可能是UI线程.
 * ================================
 *
 *
 * ================================
 * onEventMainThread:
 * 事件的处理会在UI线程中执行。
 * 事件处理时间不能太长，长了会ANR的
 * * ================================
 *
 *
 *
 * ================================
 * 3.onEventBackgroundThread(一个后台线程):
 * 注意:
 * 事件的处理会在一个后台线程中执行,但事件处理时间还是不应该太长,否则会阻塞后面的事件的派发或处理
 * a.如果发送事件的线程是后台线程，会直接执行事件.
 * b.如果当前线程是UI线程,事件会被加到一个队列中,由一个线程依次处理这些事件(就是跟a相同的线程)
 * ================================
 *
 *
 *
 * ================================
 * 4.onEventAsync:
 * 事件处理会在单独的线程中执行，主要用于在后台线程中执行耗时操作
 * 每个事件会开启一个线程（有线程池）
 * ================================
 *
 * ================================
 * 关于混编:
 * -libraryjars libs/EventBus-2.4.0.jar
 * -keepclassmembers class ** {
 * public void onEvent*(**);
 * }
 * -keepclassmembers class ** {
 * public void xxxxxx(**);
 * }
 * xxxxx为需要保留的方法
 * ================================
 */
public interface IEventBusImpl {
    interface IEventBusOnEventImpl<T extends BaseEvent> {
        public void onEvent(T event);
    }

    interface IEventBusMainThreadImpl<T extends BaseEvent> {
        public void onEventMainThread(T event);
    }

    interface IEventBusBackgroundImpl<T extends BaseEvent> {
        public void onEventBackgroundThread(T event);
    }

    interface IEventBusAsyncImpl<T extends BaseEvent> {
        public void onEventAsync(T event);
    }
}






