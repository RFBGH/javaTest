package com.rfb.demo.rxjavatest.rx;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.ReplaySubject;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public final class OrderSubjectUtil {

    public static <T>Observable<T> ContactObservable(List<Observable<T>> observables) {

        Observable<T> contactObservable = null;

        for(Observable<T> observable:observables){

            final ReplaySubject<T> replaySubject = ReplaySubject.create();

            observable
                    .subscribe(new Subscriber<T>() {
                        @Override
                        public void onCompleted() {
                            replaySubject.onCompleted();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            replaySubject.onCompleted();
                        }

                        @Override
                        public void onNext(T t) {
                            replaySubject.onNext(t);
                        }
                    });

            if(contactObservable == null){
                contactObservable = replaySubject;
            }else{
                contactObservable = contactObservable.concatWith(replaySubject);
            }
        }

        return contactObservable;
    }
}
