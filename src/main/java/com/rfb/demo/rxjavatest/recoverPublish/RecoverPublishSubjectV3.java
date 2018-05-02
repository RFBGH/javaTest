package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by RFB on 2017/5/17.
 */
public class RecoverPublishSubjectV3<T> {


    private PublishSubject<T> mPublishSubject = PublishSubject.create();
    private PublishSubject<T> mRecoverObervable;

    private Observable<T> recoverSubject(){

        return recoverableObservable()
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    public Observable<? extends T> call(Throwable throwable) {
                        System.out.println("error");
                        mPublishSubject = PublishSubject.create();
                        return recoverSubject();
                    }
                });
    }

    protected Observable<T> recoverableObservable(){
        return mPublishSubject;
    }

    public void next(T s){
        mPublishSubject.onNext(s);
    }

    public void error(Throwable t){
        mPublishSubject.onError(t);
    }

    public void complete(){
        mPublishSubject.onCompleted();
    }

    public Observable<T> asObservable(){

        if(mRecoverObervable != null){
            return mRecoverObervable;
        }

        mRecoverObervable = PublishSubject.create();
        recoverSubject()
            .subscribe(new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    mRecoverObervable.onCompleted();
                }

                @Override
                public void onError(Throwable throwable) {
                    mRecoverObervable.onError(throwable);
                }

                @Override
                public void onNext(T t) {
                    mRecoverObervable.onNext(t);
                }
            });
        return mRecoverObervable;
    }
}

/*

    boolean error = false;
    public void testRecoverablePusblishSubject(){


        final RecoverPublishSubject<String> demo = new RecoverPublishSubject<String>(){

            @Override
            protected Observable<String> recoverableObservable() {
                return super.recoverableObservable()
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                return s+" "+s;
                            }
                        })
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {

                                if(s.equals("4 4")){

                                    if(!error){
                                        String xx = null;
                                        xx.length();
                                    }

                                }

                                return s;
                            }
                        });
            }
        };

        demo.asObservable().subscribe(new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println("sub1 "+s);
            }
        });

        demo.asObservable().subscribe(new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println("sub2 "+s);
            }
        });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    public void call(Subscriber<? super Object> subscriber) {

                        for (int i = 0; i < 20; i++) {

                            demo.next(i + "");

                            if (i % 3 == 0) {
                                demo.error(new Exception());
                            }

                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

 */
