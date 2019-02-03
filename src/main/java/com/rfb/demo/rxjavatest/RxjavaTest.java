package com.rfb.demo.rxjavatest;

import com.rfb.demo.rxjavatest.bean.MyAdd;
import com.rfb.demo.rxjavatest.recoverPublish.RecoverPublishSubject;
import com.rfb.demo.rxjavatest.rx.BackPressureBehaviorSubject;
import com.rfb.demo.rxjavatest.rx.OrderSubjectUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.*;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class RxjavaTest implements Cloneable{

    private PublishSubject<String> mPublishSubject;

    public Observable<String> getSubjectObservabl() {
        return mPublishSubject;
    }

    public Observable<String> getNotify() {
        return mPublishSubject;
    }

    public void start() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPublishSubject.onNext("hello world");
            }
        });

    }

    public Observable<String> getNotify2() {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("start thread " + Thread.currentThread().getName());
                subscriber.onNext("hello world");
                subscriber.onCompleted();
            }
        })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }

    public void testFlatMap() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("xx");
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just("flapMap");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                PrintUtil.print(s);
            }
        });

    }

    public void testOnErr() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                PrintUtil.print("before onError");
                subscriber.onNext("onNext before onError");
                subscriber.onError(new Exception());

                PrintUtil.print("after onError");
                subscriber.onNext("onNext after onError");

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });
    }

    public void testSubscribeOn() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                for (int i = 0; i < 3; i++) {

                    subscriber.onNext("task 1 " + i);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print(Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print(Thread.currentThread().getName());
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });
    }

    public void testConcat() {

        Observable.concat(
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for (int i = 0; i < 3; i++) {

                            subscriber.onNext("task 1 " + i);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io()),
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for (int i = 0; i < 3; i++) {
                            subscriber.onNext("task 2 " + i);
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
        ).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String st) {
                PrintUtil.print(st);
            }
        });
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//
//                for(int i = 0; i < 3; i++){
//
//                    subscriber.onNext("task 1 "+i);
//                    try{
//                        Thread.sleep(500);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        })
//        .concatWith( Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//
//                for(int i = 0; i < 3; i++){
//                    subscriber.onNext("task 2 "+i);
//                    try{
//                        Thread.sleep(100);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        })).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onNext(String st) {
//                PrintUtil.print(st);
//            }
//        });

    }

    public void testFinalSub() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                subscriber.onNext("task start");
                new Thread() {

                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {

                            subscriber.onNext("task 1 " + i);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        subscriber.onCompleted();
                    }
                }.start();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });
    }

    public void testFilter() {

        PrintUtil.print("main" + Thread.currentThread());

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                subscriber.onNext("task start");
            }
        })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        PrintUtil.print("fliter 1" + Thread.currentThread());
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        PrintUtil.print("fliter 2" + Thread.currentThread());
                        return true;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });
    }

    public void testOnError() {

        Observable.merge(
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        while (true) {
                            try {

                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            subscriber.onNext("task1");
                        }
                    }
                }).subscribeOn(Schedulers.io()),
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {

                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        subscriber.onError(new Exception());
                    }
                }).subscribeOn(Schedulers.io())
        )
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });

    }

    public void testOnErrorResume() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                for (int i = 0; i < 4; i++) {
                    try {

                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    subscriber.onNext("task1");
                }

                subscriber.onError(new Exception());
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<String>>() {
            @Override
            public Observable<String> call(Throwable throwable) {
                return Observable.just("xxxooo");
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });
    }

    public void testDistinct() {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(false);
                subscriber.onNext(false);
                subscriber.onNext(false);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(false);
                subscriber.onNext(false);

            }
        }).distinct()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Boolean s) {
                        PrintUtil.print(s.toString());
                    }
                });

    }


    public void testDistinctUtilChange() {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(false);
                subscriber.onNext(false);
                subscriber.onNext(false);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(true);
                subscriber.onNext(false);
                subscriber.onNext(false);

            }
        }).distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Boolean s) {
                        PrintUtil.print(s.toString());
                    }
                });

    }

    public void testBehaviorPublish() {

        BehaviorSubject<Integer> subject = BehaviorSubject.create();

        subject.onNext(1);
        subject.onCompleted();

        subject.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext "+integer);
            }
        });


    }

    public void testSubscribOnOnOn() {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        PrintUtil.print("call 1" + Thread.currentThread());
                    }
                }).subscribeOn(Schedulers.io())
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        PrintUtil.print("call 2" + Thread.currentThread());
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                PrintUtil.print("onNext" + Thread.currentThread());
            }
        });

    }

    public void testMap() {

        Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        PrintUtil.print("call " + Thread.currentThread().getName());
                        subscriber.onNext(true);
                    }
                }).map(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean aBoolean) {
                PrintUtil.print("map " + Thread.currentThread().getName());
                return aBoolean;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Boolean s) {
                        PrintUtil.print("next " + Thread.currentThread().getName());
                    }
                });
    }


    public void testFlatMap2() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                PrintUtil.print("call1 " + Thread.currentThread().getName());
                subscriber.onNext("xx");
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        PrintUtil.print("call2 " + Thread.currentThread().getName());
                        subscriber.onNext("xx2");
                    }
                });
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                PrintUtil.print("next " + Thread.currentThread().getName());
            }
        });

    }

    private void delay(int delay) {

        try {

            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    PublishSubject<String> mPublishSubject2 = PublishSubject.create();

    public void testError() {


        new Thread() {

            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    mPublishSubject2.onNext("thread 1 " + i);
                    delay(50);
                }
            }
        }.start();


        new Thread() {

            @Override
            public void run() {

                for (int i = 0; i < 200; i++) {
                    mPublishSubject2.onNext("thread 2 " + i);
                    delay(30);
                }
            }
        }.start();

        mPublishSubject2
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String o) {
                        PrintUtil.print("sub1 " + o);
                        delay(100);
                    }
                });

        mPublishSubject2
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String o) {
                        PrintUtil.print("sub2 " + o);
                        delay(500);
                    }
                });

    }

    public void testMergeComplete() {

        Observable
                .merge(
                        Observable.create(new Observable.OnSubscribe<Observable<?>>() {
                            @Override
                            public void call(Subscriber<? super Observable<?>> subscriber) {
                                PrintUtil.print("ob1 onComplete");
                                subscriber.onCompleted();
                            }
                        }),
                        Observable.create(new Observable.OnSubscribe<Observable<?>>() {
                            @Override
                            public void call(Subscriber<? super Observable<?>> subscriber) {
                                PrintUtil.print("ob2 onComplete");
                                subscriber.onCompleted();
                            }
                        }))
                .subscribeOn(Schedulers.io())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        PrintUtil.print("onComplete");
                    }
                })
                .subscribe(new Subscriber<Observable<?>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Observable<?> observable) {

                    }
                });

    }

    public void testObserveOn() {

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        PrintUtil.print("next " + Thread.currentThread().getName());
                        subscriber.onNext("1");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        delay(1000);
                        PrintUtil.print("onNext1 " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print("onNext2 " + Thread.currentThread().getName());
                    }
                })
                .subscribe();
    }

    public void testBehavior() {

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        return Observable.just("");
                    }
                });


        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.onNext("1");

        subject.subscribe(
                new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                }
        );

        subject.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                PrintUtil.print(s);
            }
        });

    }

    public void testInterval() {

        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println(aLong);
                    }
                });
    }

    public void testMap2() {

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("111");
                        subscriber.onCompleted();
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + "map before subscribeOn " + s);
                        return s;
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " doOnNext before subscribeOn " + s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " map after subscribeOn " + s);
                        return s;
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " doOnNext after subscribeOn " + s);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " map after observeOn " + s);
                        return s;
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " doOnNext after observeOn " + s);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " " + s);
                    }
                });
    }

    private String mLastString;

    public void testMissingBackPressure() {

        final PublishSubject<String> publishSubject = PublishSubject.create();

//        Observable
//                .merge(
//                        Observable
//                                .create(new Observable.OnSubscribe<String>() {
//                                    @Override
//                                    public void call(final Subscriber<? super String> subscriber) {
//                                        publishSubject.subscribe(new Action1<String>() {
//                                            @Override
//                                            public void call(String s) {
//                                                mLastString = s;
//                                                subscriber.onNext(s);
//                                            }
//                                        });
//                                    }
//                                })
//                                .onBackpressureBuffer()
//                                .observeOn(Schedulers.io()),
//                        Observable.just(mLastString)
//                );


        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print("custom " + s);

//                        if(s.equals("1"))
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

//        publishSubject
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//        .subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                PrintUtil.print(s);
//
//                                          try {
//                                Thread.sleep(5000);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//            }
//        });


        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                        try{
                            int i = 0;
                            while (true) {
                                publishSubject.onNext(i + "");
                                i++;
                            }
                        }catch (Throwable e){
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                    }
                });

    }

    public void testBackPressure() {

        final BackPressureBehaviorSubject<String> behaviorSubject = new BackPressureBehaviorSubject<String>();

        behaviorSubject
                .asObservable()
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print("custom1 " + s);

//                        if(s.equals("1"))
                        {
                            try {
                                Thread.sleep(5000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        behaviorSubject
                                .asObservable()
                                .observeOn(Schedulers.io())
                                .subscribe(new Action1<String>() {
                                    @Override
                                    public void call(String s) {
                                        PrintUtil.print("custom2 " + s);

//                        if(s.equals("1"))
                                        {
                                            try {
                                                Thread.sleep(5000);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();


        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        int i = 0;
                        while (true) {
                            behaviorSubject.onNext(i + "");
                            i++;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {
                    }
                });

    }

    private void delay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testContact() {

        Observable
                .concat(

                        Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {

                                        System.out.println("run observable1");

                                        delay(1000);
                                        subscriber.onNext("1");
                                        subscriber.onCompleted();
                                    }
                                })
                                .subscribeOn(Schedulers.io()),

                        Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {

                                        System.out.println("run observable2");

                                        delay(1000);
                                        subscriber.onNext("2");
                                        subscriber.onCompleted();
                                    }
                                })
                                .subscribeOn(Schedulers.io()),

                        Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {

                                        System.out.println("run observable3");

                                        delay(1000);
                                        subscriber.onNext("3");
                                        subscriber.onCompleted();
                                    }
                                })
                                .subscribeOn(Schedulers.io())

                )
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String o) {

                        System.out.println(o);
                    }
                });

    }

    public void testCompose() {

        observable1()
                .compose(new Observable.Transformer<String, String>() {
                    @Override
                    public Observable<String> call(Observable<String> stringObservable) {
                        return stringObservable.subscribeOn(Schedulers.io());
                    }
                })
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

    }

    private Observable<String> observable1() {

        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("run observable1");

                        delay(2000);
                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());

    }

    private Observable<String> observable2() {

        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("run observable2");

                        delay(1000);
                        subscriber.onNext("2");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    private Observable<String> observable3() {

        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("run observable3");

                        delay(1000);
                        subscriber.onNext("3");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    private Observable<String> observable4() {

        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("run observable4");

                        delay(1000);
                        subscriber.onNext("4");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }


    public void testMultiObservable() {

        List<Observable<String>> observables = new ArrayList<Observable<String>>();
        observables.add(observable1());
        observables.add(observable2());
        observables.add(observable3());
        observables.add(observable4());

        OrderSubjectUtil.ContactObservable(observables)
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
    }

    public void testReplaySubject() {

        ReplaySubject<String> replaySubject1 = ReplaySubject.create();

        replaySubject1.onNext("11");
        replaySubject1.onNext("12");

        ReplaySubject<String> replaySubject2 = ReplaySubject.create();

        replaySubject2.onNext("21");
        replaySubject2.onNext("22");

        ReplaySubject<String> replaySubject3 = ReplaySubject.create();

        replaySubject3.onNext("31");
        replaySubject3.onNext("32");

        Observable
                .concat(
                        replaySubject1,
                        replaySubject2,
                        replaySubject3
                )
                .observeOn(Schedulers.io())
                .subscribe(
                        new Subscriber<String>() {
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
                        }
                );

        delay(1000);
        replaySubject2.onCompleted();

        delay(1000);
        replaySubject1.onCompleted();

        delay(1000);
        replaySubject3.onCompleted();
    }

    private Observable<List<String>> search(int offset, final int count, String key) {

        return Observable.just(null);
    }

    private Observable<List<String>> testxxx(final int offset, final int count, final String key) {
        return null;
    }

    public void testUnsubscribeDoOnNext() {

        final Subscription subscription =
                Observable
                        .create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {

                                for (int i = 0; i < 5; i++) {

                                    subscriber.onNext(i + "");
                                    delay(1000);
                                }
                                subscriber.onCompleted();
                            }
                        })
                        .observeOn(Schedulers.io())
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                PrintUtil.print("map:" + s);
                                return s;
                            }
                        })
                        .doOnNext(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                PrintUtil.print("doOnNext:" + s);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                PrintUtil.print("onNext:" + s);
                            }
                        });


        new Thread() {

            @Override
            public void run() {

                delay(2000);
                subscription.unsubscribe();
            }
        }.start();


    }

    public void testReplay() {
        PrintUtil.print(Thread.currentThread().getName());
        ConnectableObservable testObservable = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        PrintUtil.print(Thread.currentThread().getName());
                        subscriber.onNext("1");
                        PrintUtil.print("1");
                        subscriber.onNext("2");
                        PrintUtil.print("2");
                        subscriber.onNext("3");
                        PrintUtil.print("3");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .replay(1);

        testObservable.connect();

//        testObservable
//                .subscribeOn(Schedulers.io())
//                .subscribe();
    }

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

    public Observable<String> getOnlyOneRunDemoObservable() {

        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    public void call(Subscriber<? super String> subscriber) {
                        for (int i = 0; i < 10; i++) {
                            delay(1000);
                            subscriber.onNext(i + "");
                        }
                    }
                })
                .compose(onlyOneRunnerScheduler);
    }

    private Observable.Transformer<String, String> onlyOneRunnerScheduler = new Observable.Transformer<String, String>() {

        private PublishSubject<String> mPublishSubject;

        public Observable<String> call(Observable<String> stringObservable) {

            if (mPublishSubject != null) {
                return mPublishSubject.asObservable();
            }

            mPublishSubject = PublishSubject.create();
            stringObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<String>() {
                        public void onCompleted() {
                            mPublishSubject.onCompleted();
                        }

                        public void onError(Throwable throwable) {
                            mPublishSubject.onError(throwable);
                        }

                        public void onNext(String s) {
                            mPublishSubject.onNext(s);
                        }
                    });

            return mPublishSubject;
        }
    };

    public void testOnlyRunObservable() {

        getOnlyOneRunDemoObservable()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " sub1 " + s);
                    }
                });

        getOnlyOneRunDemoObservable()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " sub2 " + s);
                    }
                });

        getOnlyOneRunDemoObservable()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(Thread.currentThread().getName() + " sub3 " + s);
                    }
                });

    }

    public void testRetry1(){

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("1");
                        subscriber.onNext("2");
                        String s = null;
                        s.length();
                    }
                })
                .subscribeOn(Schedulers.io())
                .retry(2)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });

    }


    public void testRetry2(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        Observable
                .merge(
                        publishSubject
                                .subscribeOn(Schedulers.io()),
                        Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {

                                        subscriber.onNext("1");
                                        delay(1000);
                                        subscriber.onNext("2");
                                        delay(1000);
                                        String s = null;
                                        s.length();
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                )
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        PrintUtil.print("err");
                    }
                })
                .retry()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        PrintUtil.print("err");
                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(s);
                    }
                });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                        for(int i = 0; i < 20; i++){
                            publishSubject.onNext("push "+i+"");
                            delay(1000);
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    public void testObserveOnSubscribeOn(){

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("xxxx");
                        PrintUtil.print("subscribeOn "+Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
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
                        PrintUtil.print("onNext "+Thread.currentThread().getName());
                    }
                });

    }

    public void testUnsubscribe(){

        final Subscription subscription =
                Observable
                    .create(new Observable.OnSubscribe<Integer>() {
                        @Override
                        public void call(Subscriber<? super Integer> subscriber) {

                            for(int i = 0; i < 10; i++){

                                System.out.println("next "+i);
                                subscriber.onNext(i);
                                delay(1000);

                                if(i == 4){
                                    System.out.println("error");
                                    subscriber.onError(new Exception());
                                }
                            }

                        }
                    })
                    .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                    .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {
                            System.out.println("onError ");
                            throwable.printStackTrace();
                        }

                        @Override
                        public void onNext(Integer integer) {
                            System.out.println("onNext " + integer);
                        }
                    });

        new Thread(){

            @Override
            public void run() {

                delay(2000);
                subscription.unsubscribe();

            }
        }.start();

    }

    public void testTake(){

        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {

                        for(int i = 0; i < 10; i++){

                            System.out.println("next "+i);
                            subscriber.onNext(i);
                            delay(1000);

                            if(i == 4){
                                System.out.println("error");
                                subscriber.onError(new Exception());
                            }
                        }

                    }
                })
                .take(1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext " + integer);
                    }
                });

    }


    public void testTake2(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("doOnNext "+s);
                    }
                })
                .take(1)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String integer) {
                        System.out.println("onNext " + integer);
                    }
                });


        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {

                        for (int i = 0; i < 10; i++) {

                            System.out.println("next " + i);
                            publishSubject.onNext(i+"");
                            delay(1000);
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();



    }

    public void testRxCompose(){

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for(int i = 0; i < 10; i++){
                            subscriber.onNext(i+"");
                            delay(1000);
                        }

                        subscriber.onCompleted();

                    }
                })
                .compose(new Observable.Transformer<String, String>() {
                    @Override
                    public Observable<String> call(Observable<String> stringObservable) {
                        return stringObservable
                                .subscribeOn(Schedulers.io());
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s +" "+Thread.currentThread().getName());
                    }
                });


    }

    public void testUnSub(){

        final Subscription subscription = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for(int i = 0; i < 10; i++){

                            subscriber.onNext(i+"");
                            delay(1000);
                        }
                    }
                })
                .compose(applyDefaultSchedulers())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {

                        String s = (String)o;
                        System.out.println("next "+ s);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                delay(3000);
                subscription.unsubscribe();

            }
        }.start();

    }

    public static <T> Observable.Transformer<T, T> applyDefaultSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
            }
        };
    }

    public void testException(){

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        String s = null;
                        s.length();

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
//
//        Observable
//                .create(new Observable.OnSubscribe<Object>() {
//                    @Override
//                    public void call(Subscriber<? super Object> subscriber) {
//                        String s = null;
//                        s.length();
//
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Subscriber<Object>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//
//                    }
//                });
    }

    public void testOnErrorResume2(){


        final PublishSubject<String> publishSubject = PublishSubject.create();

        Observable
                .mergeDelayError(
                        Observable
                                .just("1112"),
                        Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {
                                        delay(2000);
                                        String s = null;
                                        s.length();
                                    }
                                })

                                .subscribeOn(Schedulers.io()),
                        publishSubject
                                .asObservable()
                                .subscribeOn(Schedulers.io())
                )
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                int i = 0;
                while(true){

                    publishSubject.onNext(i+"");
                    i++;

                    delay(1000);
                }
            }
        }.start();

    }

    public void testOnErrorResume3(){

        ArrayList<Observable<String>> observables = new ArrayList<Observable<String>>();


        observables.add(Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("start");

                        delay(2000);
                        String s = null;
                        s.length();
                    }
                }));

        for(Observable<String> observable:observables){

            observable.onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                @Override
                public Observable<? extends String> call(Throwable throwable) {
                    return Observable.just("onError");
                }
            });
        }

        Observable
                .merge(observables)
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
                        System.out.println("onNext " + s);
                    }
                });

    }

    public void testUnsubscribe2(){

        final Subscription subscription = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {


                        int i = 0;
                        while(true){
                            subscriber.onNext(i+"");
                            i++;
                            delay(1000);
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        System.out.println("onMap: " + s);
                        return s;
                    }
                })
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
                        System.out.println("onNext " + s);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                delay(4000);

                subscription.unsubscribe();

            }
        }.start();

    }

    public void testCombineLatest(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        final Subscription subscription = Observable
                .combineLatest(
                        Observable
                            .create(new Observable.OnSubscribe<String>() {
                                @Override
                                public void call(Subscriber<? super String> subscriber) {
                                    int i = 0;
                                    while(true){
                                        subscriber.onNext("create "+i+" ");
                                        i++;
                                        delay(1000);
                                    }
                                }
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()),
                        publishSubject.asObservable()
                                .subscribeOn(Schedulers.io()),
                        new Func2<String, String, String>() {
                            @Override
                            public String call(String s, String s2) {
                                return s+" "+s2;
                            }
                        }
                )
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String o) {
                        System.out.println("onNext " + o);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                int i = 0;
                while(true){

                    publishSubject.onNext("publish "+i+" ");
                    i++;

                    delay(1000);
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run() {

                delay(4000);

                subscription.unsubscribe();

            }
        }.start();
    }

    public void test(){

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Object, Observable<?>>() {
                    @Override
                    public Observable<?> call(Object o) {

                        if(o != null){
                            return Observable.just(o);
                        }

                        return Observable
                                .just("xxx")
                                .observeOn(Schedulers.io())
                                .doOnNext(new Action1<String>() {
                                    @Override
                                    public void call(String s) {

                                    }
                                })
                                .observeOn(Schedulers.io())
                                .map(new Func1<String, Object>() {
                                    @Override
                                    public Object call(String s) {
                                        return null;
                                    }
                                })
                                .observeOn(Schedulers.io());
                    }
                });

    }

    public void testPublishError(){

        final BehaviorSubject<String> publishSubject = BehaviorSubject.create();

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted1");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error1");
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted2");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error2");
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted3");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error3");
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        new Thread(){

            @Override
            public void run() {

                delay(1000);
                publishSubject.onCompleted();

            }
        }.start();

    }


    public void testUnsub2(){


        final Subscription subscription = Observable
                .create(new Observable.OnSubscribe<String>() {

                    boolean finish = false;

                    @Override
                    public void call(Subscriber<? super String> subscriber) {


                        Subscription subscription = Subscriptions.create(new Action0() {
                            @Override
                            public void call() {
                                finish = true;
                                System.out.println("inner subscription");
                            }
                        });

                        subscriber.add(subscription);


                        while(true){


                            if(finish) {

                                break;
                            }
                        }



                        for(int i = 0; i < 4; i++){
                            delay(1000);
                            subscriber.onNext("sss");
                        }

//                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext");
                    }
                });

        new Thread(){

            @Override
            public void run() {

                delay(1000);
                subscription.unsubscribe();

            }
        }.start();

    }

    public void testUnsub3(){

        final Subscription subscription = Observable
                .create(new Observable.OnSubscribe<Object>() {

                    boolean finishFlag = false;

                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                        subscriber.add(Subscriptions.create(new Action0() {
                            @Override
                            public void call() {
                                finishFlag = true;
                            }
                        }));

                        while(true){
                            delay(1000);
                            subscriber.onNext("1");

                            if(finishFlag){
                                break;
                            }
                        }
                        subscriber.onCompleted();
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("ob1 onUnsubscribe");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("ob1 onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("ob1 "+o.toString());
                    }
                });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        subscriber.add(subscription);

                        delay(2000);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();


    }

    public void onError(){

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(final Subscriber<? super Object> subscriber) {
                        subscriber.onNext("1");
                        subscriber.onCompleted();

                        new Thread(){
                            @Override
                            public void run() {

                                delay(1000);
                                subscriber.onError(new Exception("xxcva"));
                            }
                        }.start();

                    }
                })
                .flatMap(new Func1<Object, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(final Object o) {

                        return Observable
                                .create(new Observable.OnSubscribe<Object>() {
                                    @Override
                                    public void call(final Subscriber<? super Object> subscriber) {

                                        new Thread(){

                                            @Override
                                            public void run() {

                                                delay(2000);

                                                subscriber.onNext(o.toString()+" "+1);

                                                subscriber.onCompleted();

                                            }
                                        }.start();

                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("ob1 onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("ob1 onError");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("ob1 " + o.toString());
                    }
                });

    }

    private Subscription mTes1Sub;
    public void test1(){

        mTes1Sub =
                Observable
                    .create(new Observable.OnSubscribe<String>() {
                        @Override
                        public void call(Subscriber<? super String> subscriber) {

                            for(int i = 0; i < 5; i++){
                                delay(1000);
                                subscriber.onNext(i+"");
                            }
                            subscriber.onCompleted();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {

                        }

                        @Override
                        public void onNext(String s) {
                            System.out.println("sub: "+mTes1Sub);
                            System.out.println("sub: "+this);
                        }
                    });

        System.out.println("mainThread: "+mTes1Sub);

    }

    CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void testCompSub(){

        Subscription subscription = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for(int i = 0; i < 5; i++){
                            delay(1000);
                            subscriber.onNext(i+"");
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("sub: "+mTes1Sub);
                        System.out.println("sub: "+this);
                    }
                });
        compositeSubscription.add(subscription);

        delay(2000);
        compositeSubscription.clear();

        subscription = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for(int i = 0; i < 5; i++){
                            delay(1000);
                            subscriber.onNext(i+"");
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("sub: "+mTes1Sub);
                        System.out.println("sub: "+this);
                    }
                });
        compositeSubscription.add(subscription);

        delay(2000);
        compositeSubscription.clear();

    }

    public void testTake4(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnUnsubscribe "+Thread.currentThread().getName());
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnCompleted " + Thread.currentThread().getName());
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("doOnNext " + s + " " + Thread.currentThread().getName());
                    }
                })
                .takeFirst(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.equals("1");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }
                });

        delay(1000);

        publishSubject.onNext("111");

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 5; i++){
                    publishSubject.onNext(i+"");
                    delay(1000);
                }
                publishSubject.onCompleted();
            }
        }.start();

    }

    public void testBehavior2(){

        final BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.onNext("1");
//        behaviorSubject.onCompleted();

        behaviorSubject
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

    }

    public void test4(){

        System.out.println(Thread.currentThread().getName()+" ");

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(Thread.currentThread().getName() + " ");
                    }

                    @Override
                    public void onNext(String o) {
                        System.out.println(Thread.currentThread().getName() + " " + o);
                    }
                });

        delay(1000);
        publishSubject.onNext("1");

        new Thread(){

            @Override
            public void run() {
                publishSubject.onNext("2");
                publishSubject.onCompleted();

            }
        }.start();
    }



    public void test3(){

        System.out.println(Thread.currentThread().getName()+" ");

        final PublishSubject<String> publishSubject = PublishSubject.create();
        final PublishSubject<String> publishSubject2 = PublishSubject.create();

        Observable
                .zip(
                        publishSubject,
                        publishSubject2,
                        new Func2<String, String, String>() {
                            @Override
                            public String call(String s, String s2) {
                                System.out.println(Thread.currentThread().getName() + " zip");
                                return s + " " + s2;
                            }
                        }
                )
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(Thread.currentThread().getName() + " ");
                    }

                    @Override
                    public void onNext(String o) {
                        System.out.println(Thread.currentThread().getName() + " " + o);
                    }
                });


        delay(1000);


        publishSubject.onNext("1");

        Executors
                .newFixedThreadPool(5)
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " ");
                        publishSubject.onNext("2");
                    }
                });

        delay(1000);

        publishSubject2.onNext("1");


    }

    public void testZip(){

        BehaviorSubject<String> behaviorSubject1 = BehaviorSubject.create();
        BehaviorSubject<String> behaviorSubject2 = BehaviorSubject.create();



        behaviorSubject1.onNext("1");

        behaviorSubject2.onNext("2");

        Observable
                .zip(
                        behaviorSubject1,
                        behaviorSubject2,
                        new Func2<String, String, String>() {
                            @Override
                            public String call(String s, String s2) {
                                System.out.println(Thread.currentThread().getName() + " zip");
                                return s + " " + s2;
                            }
                        }
                )
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + " " + s);
                    }
                });


        delay(1000);
        behaviorSubject1.onNext("0");
        behaviorSubject2.onNext("3");

    }

    public void testCompose3(){

        final CompositeSubscription compSub = new CompositeSubscription();

        final Subscription subscription = Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });


        compSub.add(subscription);

    }

    public void testFilter3(){



        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("subscriber " + Thread.currentThread().getName());
                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {

                        System.out.println("filter "+Thread.currentThread().getName());

                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String o) {

                        System.out.println("onNext "+Thread.currentThread().getName());
                    }
                });
    }

    public void testScheduler(){

        PrintUtil.print(Thread.currentThread().getName());

        Executor executor = Executors.newSingleThreadExecutor();

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        delay(2000);

                        subscriber.onNext("task1");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.from(executor))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(Thread.currentThread().getName()+" onNext "+s);
                    }
                });

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("task2");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.from(executor))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print(Thread.currentThread().getName()+" onNext "+s);
                    }
                });

    }

    public void testObservableError(){


        Observable
                .just("1234")
                .mergeWith(Observable.<String>error(new Exception("111")))
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        PrintUtil.print("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        PrintUtil.print("onNext "+s);
                    }
                });

    }

    public void testFunction(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        Observable
                .combineLatest(
                        publishSubject,
                        Observable
                            .create(new Observable.OnSubscribe<String>() {
                                @Override
                                public void call(Subscriber<? super String> subscriber) {
                                    subscriber.onNext("111");
                                    subscriber.onCompleted();
                                }
                            })
                            .subscribeOn(Schedulers.io()),
                        new Func2<String, String, Object>() {
                            @Override
                            public String call(String s, String s2) {

                                PrintUtil.print(Thread.currentThread().getName());

                                return s+" "+s2;
                            }
                        }
                )
                .subscribeOn(Schedulers.io())
                .subscribe();

        delay(1000);
        publishSubject.onNext("111");

        new Thread(){

            @Override
            public void run() {

                delay(1000);
                publishSubject.onNext("222");
            }
        }.start();

    }

    public void testInterval2(){

        PrintUtil.print(Thread.currentThread().getName());

        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(final Long aLong) {

                        PrintUtil.print(Thread.currentThread().getName());
                    }
                });

    }


    public void testMissingBackPressure2() {

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PrintUtil.print("custom " + s);

                        delay(1000);
                    }
                });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {

                        int i = 0;
                        while (true) {
                            publishSubject.onNext(i + "");
                            i++;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    public void testDebound(){


        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for(int i = 0; i < 100; i++){
                            delay(100);
                            subscriber.onNext(i+"");
                        }
                        subscriber.onCompleted();
                    }
                })
                .sample(500, TimeUnit.MILLISECONDS, Schedulers.io())

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

    }

    public void testExceptionXXX(){

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        try{
                            MyAdd add = new MyAdd(0);

                            subscriber.onNext(""+1);
                            subscriber.onCompleted();
                        }catch (Exception e){
                            subscriber.onError(e);
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error ");
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

    }

    private int add(int pData){
        if(pData <= 0){
            throw new IllegalArgumentException("can not small than zero");

        }

        return pData;
    }

    public void testPublishOnNext(){

        final PublishSubject<String> publishSubject = PublishSubject.create();


        System.out.println("run "+Thread.currentThread().getName());


        publishSubject
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("1 "+s+" "+Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("2 "+s+" "+Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("3 "+s+" "+Thread.currentThread().getName());
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s+" "+Thread.currentThread().getName());
                    }
                });

        delay(1000);

        publishSubject.onNext("111");

        delay(1000);


        new Thread(){

            @Override
            public void run() {
                publishSubject.onNext("222");

            }
        }.start();
    }

    public void testMerge(){

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        delay(1000);
                        subscriber.onNext("create");
                        subscriber.onCompleted();
                    }
                })
                .mergeWith(Observable.just("just"))
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
                        System.out.println("onNext "+s);
                    }
                });

        delay(2000);

        Observable.merge(

                Observable
                        .create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                delay(1000);
                                subscriber.onNext("create");
                                subscriber.onCompleted();
                            }
                        })
                        .subscribeOn(Schedulers.io()),
                Observable.just("just2")
        )
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });


    }

    Subscription subscription;
    public void testTimtOunt(){

        PublishSubject<String> publishSubject = PublishSubject.create();


        subscription = publishSubject
                .observeOn(Schedulers.io())
                .timeout(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });

        delay(1000);

        publishSubject.onNext("1");

        delay(5000);

        if(subscription != null){
            System.out.println(subscription.isUnsubscribed());
        }

        delay(1000);
    }

    private String xxTest(){

        PrintUtil.print(Thread.currentThread().getName());

        return "xxTest";
    }

    public void testRxJust(){

        Observable
                .just(xxTest())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {
                        PrintUtil.print(Thread.currentThread().getName());
                        PrintUtil.print("onNext "+o.toString());
                    }
                });

    }

    public void testInerval(){

        Observable
                .interval(10, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) {

                        PrintUtil.print("onNext "+aLong);
                        delay(2000);
                    }
                });

        delay(5000);
    }

    public void testTimer(){

        Observable
                .timer(2000,TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted "+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) {

                        System.out.println("create "+Thread.currentThread().getName());
                        PrintUtil.print("onNext "+aLong);
//                        delay(2000);
                    }
                });

        delay(5000);
    }

    public void testComputation(){


        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("create "+Thread.currentThread().getName());
                        subscriber.onNext(1+"");
                        subscriber.onNext(2+"");
                        subscriber.onCompleted();

                    }
                })
                .throttleFirst(500, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+Thread.currentThread().getName());
                        System.out.println(s);
                    }
                });

        delay(2000);
    }

    private String just(){

        System.out.println("just "+Thread.currentThread().getName());
        return "111";

    }

    public void testJustFlatMap(){

        System.out.println("testJustFlatMap "+Thread.currentThread().getName());
        Observable
                .just(just())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("just next " + Thread.currentThread().getName());
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {

                        System.out.println("Func1 " + Thread.currentThread().getName());

                        return Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {

                                        System.out.println("Observable create " + Thread.currentThread().getName());

                                        subscriber.onNext("222");
                                        subscriber.onCompleted();
                                    }
                                });
                    }
                })
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
                        System.out.println("onNext "+Thread.currentThread().getName());
                    }
                });

    }

    public void testRxSubscription(){

        System.out.println("onNext "+Thread.currentThread().getName());
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("111");
                        subscriber.onCompleted();
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("onNext "+Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    public void testBuffer(){

        final ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject
                .buffer(1000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(List<String> s) {

                        System.out.println("onNext "+s.size());
                        for(String s1:s){

                                System.out.print(s1+" ");
                        }
                        System.out.println();

                    }
                });

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 10000; i++){
                    replaySubject.onNext(i+"");
                    delay(100);
                }
            }
        }.start();

        delay(1000000);
    }

    public void testSubscription(){


        final PublishSubject<String> publishSubject = PublishSubject.create();

        final Subscription subscription1 = publishSubject
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 4; i++){
                    System.out.println("Thread 1"+i);
                    publishSubject.onNext("1 "+i+"");
                    delay(1000);
                }
            }
        }.start();

        delay(10000);

        subscription1.unsubscribe();
        System.out.println("after unsub");

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 4; i++){

                    System.out.println("Thread 2"+i);
                    publishSubject.onNext("2 "+i+"");
                    delay(1000);
                }
            }
        }.start();
    }


    public void testSubscription2(){

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext "+s);
            }
        };

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for(int i = 0; i < 100; i++){
                            System.out.println("Thread 1"+i);
                            subscriber.onNext("1 "+i+"");
                            delay(1000);
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(subscriber);


        delay(5000);

        subscriber.unsubscribe();


        delay(5000);
    }


    public void testSubscribeOn2() {

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("onNext " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("onNext " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + Thread.currentThread().getName());
                    }
                });

        new Thread(){

            @Override
            public void run() {
                publishSubject.onNext("11");
            }
        }.start();

        delay(1000);

        publishSubject.onNext("222");

        delay(1000);
    }

    public void testPushishOnResume(){

        final PublishSubject<String> publishSubject = PublishSubject.create();
//        publishSubject.onError(new Exception());



        publishSubject
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("onNext " + Thread.currentThread().getName());
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        System.out.println("onNext " + Thread.currentThread().getName());
                        return s;
                    }
                })
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
                        System.out.println("onNext " + Thread.currentThread().getName());
                    }
                });

        delay(1000);

        publishSubject.onNext("1111");


        if(publishSubject.hasThrowable()){
            System.out.println("error");
        }else{
            System.out.println("no error");
        }


    }

    public void testBehaviorSubject(){

        BehaviorSubject<Boolean> behaviorSubject = BehaviorSubject.create();

        if(behaviorSubject.getValue() == null){
            System.out.println("xxx");
        }

        System.out.println(behaviorSubject.getValue());

        behaviorSubject.onNext(true);

        System.out.println(behaviorSubject.getValue());

    }

    public void testPublishSubject2(){

        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {
                                        for(int i = 0; i < 10; i++){
                                            subscriber.onNext(i+" ");
                                            delay(1000);

                                            if(i == 2)
                                                throw new RuntimeException("xxxx");
                                        }
                                        subscriber.onCompleted();
                                    }
                                })
                                .subscribeOn(Schedulers.io());
                    }
                })
                .distinctUntilChanged()
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s);
                    }
                });

        publishSubject.onNext("xxxx");

        delay(3000);

//        publishSubject.onError(new Exception("xxx"));


        System.out.println("publishSubject "+publishSubject.hasThrowable());

    }

    public void testBackPressure2(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .onBackpressureDrop()
                .observeOn(Schedulers.io())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("publishSubject "+publishSubject.hasThrowable());
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        delay(1000);
                        System.out.println("onNext " + s);
                    }
                });

        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        for(int i = 0; i < 1000; i++){
                            publishSubject.onNext(i+"");
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("xxxx");
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

        delay(3000);

        System.out.println("publishSubject "+publishSubject.hasThrowable()+" "+publishSubject.getValues().length);


    }

    public void testMergetPusblishSubject(){

        final PublishSubject<String> publishSubject1 = PublishSubject.create();

        final PublishSubject<String> publishSubject2 = PublishSubject.create();


        publishSubject1
                .mergeWith(publishSubject2)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                        System.out.println("onNext "+s);
                    }
                });

        delay(1000);

        publishSubject1.onNext("1 + fuck");

        delay(1000);

        publishSubject2.onNext("2 + fuck");

        delay(1000);
    }

    public void testSample(){


        final PublishSubject<String> publishSubject1 = PublishSubject.create();

        publishSubject1
                .sample(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                        System.out.println("onNext "+s);
                    }
                });

        for(int i = 0; i < 10; i++){

            publishSubject1.onNext(i+" + fuck");
            delay(100);
        }

        delay(5000);

    }

    public<T> Observable<T> transformTimeOut(Observable<T> srcObs, long timeOut, TimeUnit timeUnit) {

        if(timeOut == 0){
            return srcObs;
        }

        return Observable
                .merge(
                        srcObs,
                        Observable
                                .timer(timeOut, timeUnit)
                                .map(new Func1<Long, T>() {
                                    @Override
                                    public T call(Long aLong) {
                                        return null;
                                    }
                                })
                )
                .take(1);
    }

    public void testTimeOutRx(){


        Observable<String> srcObs = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        delay(1000);
                        subscriber.onNext("1111");
                        subscriber.onCompleted();

                    }
                })
                .subscribeOn(Schedulers.io());


        transformTimeOut(srcObs, 200, TimeUnit.MILLISECONDS)

                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });

        delay(2000);

        System.out.println("end");

    }

    public void testPublishOnComplete(){

        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("1 onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("1 onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("1 onNext "+s);
                    }
                });

        publishSubject.onNext("111");
        publishSubject.onError(new Exception());

        publishSubject
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("2 onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("2 onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("2 onNext "+s);
                    }
                });

        delay(1000);

    }

    public void multiPublishSubject(){

        final PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject
                .buffer(100, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        System.out.println("onNext "+Thread.currentThread().getName()+" size"+strings.size());
                    }
                });


        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 10; i++){

                    System.out.println("next 1"+Thread.currentThread().getName());
                    publishSubject.onNext("1 "+i);
                    delay(20);
                }

            }
        }.start();


        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 10; i++){

                    System.out.println("next 2"+Thread.currentThread().getName());
                    publishSubject.onNext("2 "+i);
                    delay(20);
                }

            }
        }.start();

        delay(5000);
    }

    public void testBehavorSubject(){

        final BehaviorSubject<String> subject = BehaviorSubject.create();

        subject
//                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        delay(50);
                        System.out.println("next 1 "+s);
                    }
                });

        subject
//                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                        delay(20);
                        System.out.println("next 2 "+s);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 100; i++){

                    subject.onNext("1 " + i + "");
                    delay(10);
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 100; i++){

                    subject.onNext("2 " + i + "");
                    delay(10);
                }
            }
        }.start();

        delay(20000);

    }


    public void testSubjectToBlock(){

        final BehaviorSubject<String> subject = BehaviorSubject.create();

        subject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });


        new Thread(){

            @Override
            public void run() {

                String s = subject.toBlocking().first();
                System.out.println("Thread "+s);
            }
        }.start();

        delay(1000);

        new Thread(){

            @Override
            public void run() {

                subject.onNext("1");

                delay(1000);
                subject.onNext("2");

                delay(1000);

                subject.onCompleted();

            }
        }.start();


        delay(3000);

        String s = subject.toBlocking().first();
        System.out.println("main "+s);
    }

    public void testBufferSubject(){

        final PublishSubject<String> publishSubject = PublishSubject.create();


        publishSubject
                .buffer(300, TimeUnit.MILLISECONDS)
                .filter(new Func1<List<String>, Boolean>() {
                    @Override
                    public Boolean call(List<String> strings) {
                        return !strings.isEmpty();
                    }
                })
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        System.out.println("onNext " + strings.size() + " " + Thread.currentThread().getName());
                        delay(500);
                    }
                });

        for(int i = 0 ; i < 100; i++){

            new Thread(){

                @Override
                public void run() {

                    publishSubject.onNext("1 ");

                    System.out.println("Finish");
                }
            }.start();

            delay(100);
        }



        delay(5000);

    }

    public void testDebound2(){

        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted ");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s);

                    }
                });

        for(int i = 0; i < 10; i++){

            publishSubject.onNext(i+"");
            delay(100);
        }


        delay(4000);
        publishSubject.onCompleted();
    }

    public void testPublishXXX(){

        final BehaviorSubject<String> publishSubject = BehaviorSubject.create();

        publishSubject.onNext("1111");

        publishSubject
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("1 doOnNext "+Thread.currentThread().getName());
                    }
                })
//                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("2 doOnNext " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("3 doOnNext " + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        delay(1000);

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 10; i++){
                    publishSubject.onNext("1111");
                    delay(1000);
                }
                publishSubject.onCompleted();
            }
        }.start();

        delay(20000);

    }

    public void testObservon(){


        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("create "+Thread.currentThread().getName());
                        subscriber.onNext("1");
                        subscriber.onCompleted();


                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("doOnNext1 "+Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("doOnNext2 "+Thread.currentThread().getName());
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s) {

                        return Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {
                                        System.out.println("create2 "+Thread.currentThread().getName());
                                        subscriber.onNext(s);
                                        subscriber.onCompleted();
                                    }
                                });
                    }
                })
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
                        System.out.println("onNext "+Thread.currentThread().getName());
                    }
                });

        delay(1000);


    }

    public void testConnect(){

        final PublishSubject<String> publishSubject = PublishSubject.create();
        ConnectableObservable<String> co = publishSubject.publish();
        co.connect();

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 1000; i++){

                    publishSubject.onNext(i+"");
                    delay(1000);
                }

            }
        }.start();

        delay(3000);

        co.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("action1 "+s);
            }
        });

        co.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("action2 "+s);
            }
        });



    }

    public void testTimer2(){

        Subscription subscription1 = Observable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted ");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("onNext "+aLong);
                    }
                });

        delay(1000);
        subscription1.unsubscribe();

        System.out.println("unsubscribe ");

        delay(3000);

    }

    public void testGetValue(){

//        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
//
//        behaviorSubject.onNext("111");
//        behaviorSubject.onCompleted();
//
//        System.out.println(behaviorSubject.getValue());

        ReplaySubject<String> replaySubject = ReplaySubject.create();

//        replaySubject.onNext("222");
//        replaySubject.onCompleted();

        System.out.println(replaySubject.getValue());
    }

    private ReplaySubject<String> mStringReplaySubject = ReplaySubject.create();


    public void testXX(){


        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("1111");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mStringReplaySubject.onCompleted();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mStringReplaySubject.onError(throwable);
                    }

                    @Override
                    public void onNext(String s) {
                        mStringReplaySubject.onNext(s);
                    }
                });

        delay(1000);

        mStringReplaySubject
                .asObservable()
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just("xxx "+s);
                    }
                })
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


    }

    public void testSubjectBlocking(){

        final ReplaySubject<String> replaySubject = ReplaySubject.create();


        new Thread(){

            @Override
            public void run() {

                Iterator<String> iterator = replaySubject.toBlocking().getIterator();

                while(iterator.hasNext()){

                    String s= iterator.next();
                    System.out.println("onBlick " + s);
                }

            }
        }.start();


        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 10; i++){
                    System.out.println("next "+i);
                    replaySubject.onNext(i+"");
                    delay(1000);
                }
                replaySubject.onCompleted();

            }
        }.start();

        delay(20000);
    }

    public Observable<String> testMultiRx2(List<Observable<String>> observables){

        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        Observable<String> resultOb = null;
        for(Observable<String> observable: observables){

            ConnectableObservable<String> ob = observable
                    .subscribeOn(Schedulers.io())
                    .replay();

            if(resultOb == null){
                resultOb = ob;
            }else{
                resultOb = resultOb.concatWith(ob);
            }

            compositeSubscription.add(ob.connect());
        }

        return resultOb
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        compositeSubscription.unsubscribe();
                    }
                });
    }

    public void testMultiRunRx(){

        List<Observable<String>> observables = new ArrayList<Observable<String>>();
        observables.add(Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println(Thread.currentThread().getName() + " rx1");
                        delay(1000);
                        System.out.println(Thread.currentThread().getName()+" next 1");
                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                }));

        observables.add(Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println(Thread.currentThread().getName() + " rx2");
                        System.out.println(Thread.currentThread().getName()+" next 2");
                        subscriber.onNext("2");
                        subscriber.onCompleted();
                    }
                }));

        observables.add(Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println(Thread.currentThread().getName() + " rx3");
                        delay(500);
                        System.out.println(Thread.currentThread().getName()+" next 3");
                        subscriber.onNext("3");
                        subscriber.onCompleted();
                    }
                }));

        testMultiRx2(observables)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {

                        System.out.println(Thread.currentThread().getName() + " onNext");
                        System.out.println("onNext " + s);
                    }
                });
    }

    public void testRetry(){

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s) {
                        return Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {

                                        subscriber.onNext(s+" "+"2");

                                        delay(1000);

                                        throw new RuntimeException("on purpose");

//                                        subscriber.onCompleted();
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .retry(3)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

    }

    public void testBehaviorOncomplete(){


        final BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.onNext("2");
        behaviorSubject.onCompleted();

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return behaviorSubject.asObservable();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted ");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });
    }

    public void testSubjectError(){

        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {

                        delay(1000);
                    }
                });

        for(int i = 0; i < 1000; i++){
            publishSubject.onNext(i+"");
        }

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

        for(int i = 0; i < 10; i++){
            publishSubject.onNext(i+"");
        }
    }

    public void testSubjectOnBackpressureDrop() {

//        System.out.println("start");
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        for (int i = 0; i < 500; i++) {
                            subscriber.onNext(i + "");
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        delay(1000);
                    }
                });
//
//        delay(1000);

        final PublishSubject<String> publishSubject = PublishSubject.create();

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return publishSubject;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                        delay(10);
                    }
                });

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

        delay(1000);

        for (int i = 0; i < 1000; i++) {
            publishSubject.onNext(i + "");
        }
    }

    public void testPublishAndRetry(){

        final PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onError(new Exception("111"));

        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });

//        new Thread(){
//            @Override
//            public void run() {
//
//                for(int i = 0; i < 10; i++){
//
//                    delay(1000);
//
//                    if( i != 1){
//                        System.out.println("next "+i);
//                        publishSubject.onNext(i+"");
//                    }else{
//                        System.out.println("error "+i);
//                        publishSubject.onError(new Exception(i+""));
//                    }
//                }
//
//            }
//        }.start();


        delay(2000);
        publishSubject
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext "+s);
                    }
                });

    }


    public void testBufferOnNext(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .buffer(5)
                .doOnNext(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {

                        System.out.println(Thread.currentThread().getName());
                        delay(1000);
                    }
                })
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        System.out.println("onNext " + strings.size() + "first " + strings.get(0));
                    }
                });
        System.out.println(Thread.currentThread().getName());
        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 50; i++){
                    publishSubject.onNext(i+"");
                }
                publishSubject.onCompleted();

            }
        }.start();
    }

    public void testRxBackpressLatest(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject
                .onBackpressureLatest()
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        delay(1000);

                        System.out.println(s);
                    }
                });

        new Thread(){

            @Override
            public void run() {
                for(int i = 0; i < 1000; i++){
                    publishSubject.onNext("1 "+i);
                    delay(1);
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run() {
                for(int i = 0; i < 1000; i++){
                    publishSubject.onNext("2 "+i);
                    delay(1);
                }
            }
        }.start();
    }

    public void testMergeFrom(){

        System.out.println("main "+Thread.currentThread().getName());
        Observable<String> observable1 = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("1 "+Thread.currentThread().getName());
                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                });

        Observable<String> observable2 = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("2 "+Thread.currentThread().getName());
                        subscriber.onNext("2");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.newThread());

        Observable<String> observable3 = Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("3 "+Thread.currentThread().getName());
                        subscriber.onNext("3");
                        subscriber.onCompleted();
                    }
                });

        Observable<String>[] obs = new Observable[]{observable1, observable2, observable3};

        Observable.concat(Observable.from(obs))
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

                }
            });

        delay(1000);

    }

    public void testPublishNextObsOnComplete(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        Observable
                .merge(
                        Observable
                            .create(new Observable.OnSubscribe<String>() {
                                @Override
                                public void call(Subscriber<? super String> subscriber) {
                                    System.out.println(Thread.currentThread().getName()+" create ");
                                    subscriber.onNext("1");
                                    subscriber.onCompleted();
                                }
                            }),
                        publishSubject
                            .doOnNext(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    System.out.println(Thread.currentThread().getName()+" publishSubject Action1 "+s);
                                }
                            })
                )
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String o) {
                        return Observable
                                .create(new Observable.OnSubscribe<String>() {
                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {
                                        System.out.println(Thread.currentThread().getName()+" create2 ");
                                        subscriber.onNext(o);
                                        subscriber.onCompleted();
                                    }
                                });
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {

                        System.out.println(Thread.currentThread().getName()+" map "+s);
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName()+" onNext "+s);
                    }
                });

        delay(1000);

        new Thread(){
            @Override
            public void run() {

                publishSubject.onNext("2");
            }
        }.start();

        delay(5000);

//        PublishSubject<String> publishSubject = PublishSubject.create();
//
//
//        publishSubject
//                .observeOn(Schedulers.io())
//                .switchMap(new Func1<String, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(final String s) {
//                        return Observable
//                                .create(new Observable.OnSubscribe<String>() {
//                                    @Override
//                                    public void call(Subscriber<? super String> subscriber) {
//
//                                        subscriber.onNext(s+"switch");
//                                        subscriber.onCompleted();
//                                    }
//                                })
//                                .subscribeOn(Schedulers.io());
//                    }
//                })
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        System.out.println("onNext "+s);
//                    }
//                });
//
//        delay(1000);
//
//        publishSubject.onNext("1");
//
//        delay(1000);
//
//        publishSubject.onNext("2");
//
//        delay(1000);
    }

    public void testSingleTest(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        final PublishSubject<String> publishSubject1 = PublishSubject.create();

        Subscription subscription1 = publishSubject1.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName()+" 1onNext "+s);
            }
        });


        new Thread(){

            @Override
            public void run() {
                publishSubject1.onNext("1");
            }
        }.start();

        delay(1000);

        subscription1.unsubscribe();

        new Thread(){

            @Override
            public void run() {
                publishSubject1.onNext("1");
            }
        }.start();

        delay(1000);


//        final PublishSubject<String> publishSubject2 = PublishSubject.create();
//
//        final PublishSubject<String> publishSubject3 = PublishSubject.create();
//
//        publishSubject1
//                .observeOn(Schedulers.from(executorService))
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        System.out.println(Thread.currentThread().getName()+" 1onNext "+s);
//                    }
//                });
//
//        publishSubject2
//                .observeOn(Schedulers.from(executorService))
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        System.out.println(Thread.currentThread().getName()+" 2onNext "+s);
//                        delay(2000 );
//                    }
//                });
//
//        publishSubject3
//                .observeOn(Schedulers.from(executorService))
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        System.out.println(Thread.currentThread().getName()+" 3onNext "+s);
//                    }
//                });
//
//        delay(1000);
//
//        publishSubject1.onNext("1");
//        publishSubject2.onNext("2");
//        publishSubject3.onNext("3");
//
//        delay(5000);

    }

    public void testSwitchMap(){

//        Observable
//                .create(new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> subscriber) {
//
//                        subscriber.onNext("1");
//                        subscriber.onCompleted();
//                    }
//                })
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        System.out.println(Thread.currentThread().getName());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        System.out.println(Thread.currentThread().getName());
//                    }
//                })
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//                });
//
//        delay(1000);

        final PublishSubject<String> publishSubject = PublishSubject.create();

        final PublishSubject<String> publishSubject2 = PublishSubject.create();

        publishSubject
                .mergeWith(Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        delay(1000);
                        subscriber.onNext("create 1");
                        subscriber.onCompleted();
                    }
                }))
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("doOnNext " + s + " " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s + " " + Thread.currentThread().getName());
                    }
                });

//        Observable
//                .merge(
//                        publishSubject
//                            .observeOn(Schedulers.io()),
//                        publishSubject2
//                            .observeOn(Schedulers.io())
//                )
//                .doOnNext(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println("doOnNext " + s + " " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.newThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        delay(1000);
//                        System.out.println("onNext " + s + " " + Thread.currentThread().getName());
//                    }
//                });

//        publishSubject
//                .mergeWith(publishSubject2)
//                .observeOn(Schedulers.io())
//                .doOnNext(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println("doOnNext " + s + " " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.newThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        System.out.println("onNext " + s + " " + Thread.currentThread().getName());
//                        delay(1000);
//                    }
//                });
//        final PublishSubject<String> publishSubject2 = PublishSubject.create();

//        publishSubject
//                .observeOn(Schedulers.newThread())
//                .switchMap(new Func1<String, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(String s) {
//                        return publishSubject2
//                                .doOnUnsubscribe(new Action0() {
//                                    @Override
//                                    public void call() {
//                                        System.out.println("doOnUnsubscribe");
//                                    }
//                                })
//                                .doOnError(new Action1<Throwable>() {
//                                    @Override
//                                    public void call(Throwable throwable) {
//                                        System.out.println("doOnError");
//                                    }
//                                })
//                                .doOnCompleted(new Action0() {
//                                    @Override
//                                    public void call() {
//                                        System.out.println("doOnCompleted");
//                                    }
//                                });
//                    }
//                })
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//                });

        new Thread(){

            @Override
            public void run() {

                publishSubject.onNext("1");
                delay(50);
                publishSubject.onNext("2");
                delay(50);
                publishSubject.onNext("3");
                delay(50);
                publishSubject.onNext("4");
                delay(50);
                publishSubject.onNext("5");


            }
        }.start();

        new Thread(){

            @Override
            public void run() {

                publishSubject2.onNext("11");
                delay(50);
                publishSubject2.onNext("12");
                delay(50);
                publishSubject2.onNext("13");
                delay(50);
                publishSubject2.onNext("14");
                delay(50);
                publishSubject2.onNext("15");


            }
        }.start();

        delay(10000);
    }

    public static void testFlatMapPublish(){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        System.out.println("create "+Thread.currentThread().getName());

                        subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s1) {
                        return publishSubject.asObservable()
                                .map(new Func1<String, String>() {
                                    @Override
                                    public String call(String s2) {

                                        System.out.println("map "+Thread.currentThread().getName());

                                        return s1+" "+s2;
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {

                        System.out.println("onNext "+Thread.currentThread().getName()+" "+s);
                    }
                });

        new Thread(){

            @Override
            public void run() {

                System.out.println("thread "+Thread.currentThread().getName());

                for(int i = 0; i < 5; i++){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    publishSubject.onNext(i+"");
                }

            }
        }.start();

    }
}