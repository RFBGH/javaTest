package com.rfb.demo.rxjavatest.rx;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class BackPressureBehaviorSubject<T> {

    private PublishSubject<T> mPublishSubject = PublishSubject.create();
    private T mLastT;

    public void onNext(T t) {
        mLastT = t;
        mPublishSubject.onNext(t);
    }

    public Observable<T> asObservable() {

        return Observable
                .create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(final Subscriber<? super T> subscriber) {

                        if (mLastT != null) {
                            subscriber.onNext(mLastT);
                        }

                        mPublishSubject.subscribe(new Subscriber<T>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                subscriber.onError(throwable);
                            }

                            @Override
                            public void onNext(T t) {
                                subscriber.onNext(t);
                            }
                        });
                    }
                })
                .onBackpressureBuffer();
    }
}
