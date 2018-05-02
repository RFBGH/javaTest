package com.rfb.demo.rxjavatest.recoverPublish;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by RFB on 2017/5/17.
 */
public class RecoverPublishSubjectV1<T> {


    private PublishSubject<T> mPublishSubject = PublishSubject.create();

    private Observable<T> recoverSubject(){

        return mPublishSubject
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    public Observable<? extends T> call(Throwable throwable) {
                        System.out.println("error");
                        mPublishSubject = PublishSubject.create();
                        return recoverSubject();
                    }
                });
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
        return recoverSubject().asObservable();
    }
}

/*

final RecoverPublishSubject<String> demo = new RecoverPublishSubject<String>();

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

 */