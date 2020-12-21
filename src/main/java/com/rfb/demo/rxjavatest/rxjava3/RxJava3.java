package com.rfb.demo.rxjavatest.rxjava3;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subscribers.SafeSubscriber;
import org.reactivestreams.Subscription;

public class RxJava3 {

    public static void testSubscrib(){

        Flowable
                .create(new FlowableOnSubscribe<String>() {
                    public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {

                        System.out.println(Thread.currentThread().getName()+" run");

                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onNext("3");

                        emitter.onComplete();

                    }
                }, BackpressureStrategy.LATEST)
                .doOnCancel(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println(Thread.currentThread().getName()+" doOnCancel");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new FlowableSubscriber<String>() {

                    Subscription subscription;

                    public void onSubscribe(@NonNull Subscription s) {

                        System.out.println(Thread.currentThread().getName()+" onSubscribe ");
                        subscription = s;
                        subscription.request(1);
                    }

                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName()+" onNext "+s);
                        subscription.cancel();
                    }

                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName()+" onComplete");
                    }
                });
    }

    private static void delay(long delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void testSubscrib2(){
        Disposable disposable = Flowable
                .create(new FlowableOnSubscribe<String>() {
                    public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {

                        System.out.println(Thread.currentThread().getName()+" run2");

                        emitter.onNext("1");

                        delay(1000);

                        emitter.onNext("2");

                        delay(1000);

                        emitter.onNext("3");

                        delay(1000);

                        emitter.onComplete();

                    }
                }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .doOnCancel(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println(Thread.currentThread().getName()+" doOnCancel");
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                System.out.println(Thread.currentThread().getName() + " onNext " + s);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Throwable {
                                System.out.println(Thread.currentThread().getName()+" onComplete");
                            }
                        });

        delay(2000);
        disposable.dispose();

    }


    public static void test(){

        testSubscrib2();

    }

}
