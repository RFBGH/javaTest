package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by RFB on 2017/5/17.
 */
public abstract class RecoverPublishSubjectV5<T, R> {

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

/*

    boolean error = false;

    public void testRecoverablePusblishSubject() {


        final RecoverPublishSubject<String, String> demo = new RecoverPublishSubject<String, String>() {
            @Override
            protected Observable.Transformer<String, String> tranform() {

                return new Observable.Transformer<String, String>() {
                    @Override
                    public Observable<String> call(Observable<String> stringObservable) {
                        return stringObservable
                                .map(new Func1<String, String>() {
                                    @Override
                                    public String call(String s) {
                                        return s + " " + s;
                                    }
                                })
                                .map(new Func1<String, String>() {
                                    @Override
                                    public String call(String s) {

                                        if (s.equals("4 4")) {

                                            if (!error) {
                                                String xx = null;
                                                xx.length();
                                            }

                                        }
                                        return s;
                                    }
                                });
                    }
                };
            }
        };

        demo.asObservable().subscribe(new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println("sub1 " + s);
            }
        });

        demo.asObservable().subscribe(new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println("sub2 " + s);
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