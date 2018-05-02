package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by RFB on 2017/5/17.
 */
public abstract class RecoverPublishSubject<T, R> {

    private PublishSubject<T> mPublishSubject = PublishSubject.create();

    private Observable<R> recoverSubject(){

        return recoverableObservable()
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends R>>() {
                    public Observable<? extends R> call(Throwable throwable) {
                        System.out.println("error");
                        mPublishSubject = PublishSubject.create();
                        return recoverSubject();
                    }
                });
    }

    private Observable<R> recoverableObservable(){
        return mPublishSubject
                .compose(tranform());
    }

    abstract protected Observable.Transformer<T, R> tranform();

    public void next(T s){
        mPublishSubject.onNext(s);
    }

    public void error(Throwable t){
        mPublishSubject.onError(t);
    }

    public void complete(){
        mPublishSubject.onCompleted();
    }

    public Observable<R> asObservable(){
        return recoverSubject().asObservable().compose(onlyOneRunnerScheduler);
    }

    private Observable.Transformer<R, R> onlyOneRunnerScheduler = new Observable.Transformer<R, R>() {

        private PublishSubject<R> mPublishSubject;
        public Observable<R> call(Observable<R> stringObservable) {

            if(mPublishSubject != null){
                return mPublishSubject.asObservable();
            }

            mPublishSubject = PublishSubject.create();
            stringObservable
                    .subscribe(new Subscriber<R>() {
                        public void onCompleted() {
                            mPublishSubject.onCompleted();
                        }

                        public void onError(Throwable throwable) {
                            mPublishSubject.onError(throwable);
                        }

                        public void onNext(R s) {
                            mPublishSubject.onNext(s);
                        }
                    });

            return mPublishSubject.asObservable();
        }
    };
}
