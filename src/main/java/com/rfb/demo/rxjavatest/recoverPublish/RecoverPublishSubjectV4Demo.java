package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by RFB on 2017/5/17.
 */
public class RecoverPublishSubjectV4Demo {

    boolean error = false;
    public void test(){

        final RecoverPublishSubjectV4<String> demo = new RecoverPublishSubjectV4<String>(){

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
}
