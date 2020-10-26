package com.rfb.demo.rxjavatest.productAndComsumer;

import java.util.concurrent.LinkedBlockingQueue;

public class ProducerAndComsumer <T>{

    private LinkedBlockingQueue<T> mData = new LinkedBlockingQueue<>();
    private IProducer<T> mProducer;
    private IComsumer<T> mComsumer;

    public ProducerAndComsumer(IProducer<T> mProducer, IComsumer<T> mComsumer) {

        if(mProducer == null || mComsumer == null){
            throw new IllegalArgumentException("comsumer or producer is null");
        }

        this.mProducer = mProducer;
        this.mComsumer = mComsumer;
    }

    private void startProducer(){

        new Thread(){
            @Override
            public void run() {
                while (true){
                    T t = mProducer.produce();
                    try {
                        mData.put(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void startComsumer(){

        new Thread(){
            @Override
            public void run() {

                while (true){
                    T t = mData.poll();
                    if(t != null){
                        mComsumer.comsume(t);
                    }
                }
            }
        }.start();

    }

    public void start(){
        startComsumer();
        startProducer();
    }
}
