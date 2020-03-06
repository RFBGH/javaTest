package com.rfb.demo.rxjavatest;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest {

    public static class Data{
        public String text;
        public long delay;

        public Data(String text, long delay) {
            this.text = text;
            this.delay = delay;
        }
    }

    final private List<Data> mDatas = new ArrayList<Data>();
    private boolean mIsFinish = false;
    private int mNextIndex = 0;
    final private Object mWaitLook = new Object();
    private Subscription mFireSub;

    public void init(List<Data> data){
        mDatas.clear();
        mDatas.addAll(data);
        mIsFinish = false;
    }

    public void start(){

        mFireSub = Observable
                .create(new Observable.OnSubscribe<Data>() {
                    public void call(Subscriber<? super Data> subscriber) {

                        while (!mIsFinish){

                            long delay = 0;
                            while (!mIsFinish){

                                try {
                                    Thread.sleep(delay);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }

                                Data data = null;
                                synchronized (mDatas){
                                    if(mNextIndex < mDatas.size()){
                                        data = mDatas.get(mNextIndex);
                                        mNextIndex++;
                                    }
                                }

                                if(data == null){
                                    break;
                                }

                                subscriber.onNext(data);
                                delay = data.delay;
                            }

                            try {
                                synchronized (mWaitLook){
                                    mWaitLook.wait(5000);
                                }

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            synchronized (mDatas){
                                if(mNextIndex >= mDatas.size()){
                                    mNextIndex = 0;
                                }
                            }
                        }

                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Data>() {
                    public void onCompleted() {
                        System.out.println("onCompleted ");
                    }

                    public void onError(Throwable throwable) {
                        System.out.println("onError ");
                        throwable.printStackTrace();
                    }

                    public void onNext(Data s) {
                        System.out.println("fire "+s.text);
                    }
                });

    }

    public void add(List<Data> data){

        if(mIsFinish){
            return;
        }

        boolean needNotify = false;
        synchronized (mDatas){
            if(mNextIndex == mDatas.size()){
                needNotify = true;
            }

            mDatas.addAll(mNextIndex, data);
        }

        if(needNotify){
            synchronized (mWaitLook) {
                mWaitLook.notify();
            }
        }
    }

    public void stop(){
        mWaitLook.notify();
        mIsFinish = true;
        if(mFireSub != null){
            mFireSub.unsubscribe();
        }
    }

    public static void test(){

        final List<Data> datas = new ArrayList<Data>();
        datas.add(new Data("init0", 1000));
        datas.add(new Data("init1", 1000));
        datas.add(new Data("init2", 1000));
        datas.add(new Data("init3", 1000));
        datas.add(new Data("init4", 1000));
        datas.add(new Data("init5", 1000));
        datas.add(new Data("init6", 1000));
        datas.add(new Data("init7", 1000));
        datas.add(new Data("init8", 1000));
        datas.add(new Data("init9", 1000));

        final ThreadTest threadTest = new ThreadTest();
        threadTest.init(datas);
        threadTest.start();

        new Thread(){

            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Data data0 = new Data("add0", 1000);
                List<Data> datas0 = new ArrayList<Data>();
                datas0.add(data0);
                threadTest.add(datas0);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Data data11 = new Data("add11", 1000);
                Data data12 = new Data("add12", 1000);
                List<Data> datas1 = new ArrayList<Data>();
                datas1.add(data11);
                datas1.add(data12);
                threadTest.add(datas1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Data data2 = new Data("add2", 1000);
                List<Data> datas2 = new ArrayList<Data>();
                datas2.add(data2);
                threadTest.add(datas2);
            }
        }.start();


        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
