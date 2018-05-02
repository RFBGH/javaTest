package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by RFB on 2017/5/17.
 */
public class RecoverPublishSubjectV1Demo {

    public void test(){

        final RecoverPublishSubjectV1<String> demo = new RecoverPublishSubjectV1<String>();

        demo.asObservable().subscribe(new Subscriber<String>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {

            }

            public void onNext(String s) {
                System.out.println(s);
            }
        });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    public void call(Subscriber<? super Object> subscriber) {

                        for(int i = 0; i < 20 ;i++){

                            demo.next(i+"");

                            if(i%3 == 0){
                                demo.error(new Exception());
                            }

                            try{
                                Thread.sleep(1000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
