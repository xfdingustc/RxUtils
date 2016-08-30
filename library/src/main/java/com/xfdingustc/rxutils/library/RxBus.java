package com.xfdingustc.rxutils.library;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Xiaofei on 2016/8/30.
 */
public class RxBus {
    private static RxBus mBusInstance = null;

    private final Subject<Object, Object> mBus;

    private RxBus() {
        mBus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        if (mBusInstance == null) {
            synchronized (RxBus.class) {
                if (mBusInstance == null) {
                    mBusInstance = new RxBus();
                }
            }
        }

        return mBusInstance;
    }

    public void post(Object o) {
        mBus.onNext(o);
    }

    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }

}