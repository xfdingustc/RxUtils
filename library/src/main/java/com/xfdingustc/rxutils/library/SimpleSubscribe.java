package com.xfdingustc.rxutils.library;

/**
 * Created by Xiaofei on 2016/8/30.
 */

import rx.Subscriber;

public abstract class SimpleSubscribe<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
