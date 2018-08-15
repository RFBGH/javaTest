package com.rfb.demo.rxjavatest.test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Administrator on 2018/7/25 0025.
 */
public class Test3 {

    public static void test(){

        final PublishSubject<String> publishSubject1 = PublishSubject.create();
        final PublishSubject<String> publishSubject2 = PublishSubject.create();

        publishSubject1
                .asObservable()
                .concatWith(Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("subscribe");
                        for(int i = 0; i < 500; i++){

                            subscriber.onNext("in "+i);
                            delay(100);
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    publishSubject1.onNext("thread1 "+i);
                    delay(300);
                }
                publishSubject1.onCompleted();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 50; i++){
                    publishSubject2.onNext("thread2 "+i);
                    delay(100);
                }
                publishSubject2.onCompleted();
            }
        }.start();

        delay(10000);
    }

    private static void delay(int delay){

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
