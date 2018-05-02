package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by RFB on 2017/5/17.
 */
public class RecoverPublishSubjectV4<T> {

    private PublishSubject<T> mPublishSubject = PublishSubject.create();
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

    public Observable<T> asObservable(){
        return recoverSubject().asObservable().compose(onlyOneRunnerScheduler);
    }

    private Observable.Transformer<T, T> onlyOneRunnerScheduler = new Observable.Transformer<T, T>() {

        private PublishSubject<T> mPublishSubject;
        public Observable<T> call(Observable<T> stringObservable) {

            if(mPublishSubject != null){
                return mPublishSubject.asObservable();
            }

            mPublishSubject = PublishSubject.create();
            stringObservable
                    .subscribe(new Subscriber<T>() {
                        public void onCompleted() {
                            mPublishSubject.onCompleted();
                        }

                        public void onError(Throwable throwable) {
                            mPublishSubject.onError(throwable);
                        }

                        public void onNext(T s) {
                            mPublishSubject.onNext(s);
                        }
                    });

            return mPublishSubject.asObservable();
        }
    };

    public void next(T s){
        mPublishSubject.onNext(s);
    }

    public void error(Throwable t){
        mPublishSubject.onError(t);
    }

    public void complete(){
        mPublishSubject.onCompleted();
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

