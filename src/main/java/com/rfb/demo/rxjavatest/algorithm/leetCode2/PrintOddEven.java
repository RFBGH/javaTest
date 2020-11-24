package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.Objects;

public class PrintOddEven {

    private final Object evenLock = new Object();
    private final Object oddLock = new Object();
    private int n;

    public PrintOddEven(int n) {
        this.n = n;
    }

    private void waitOdd(){
        synchronized (oddLock){
            try {
                oddLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyOdd(){
        synchronized (oddLock){
            oddLock.notify();
        }
    }

    private void waitEven(){
        synchronized (evenLock){
            try {
                evenLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyEven(){
        synchronized (evenLock){
            evenLock.notify();
        }
    }

    private void doPrintOdd(){
        for(int i = 1; i < n; i+=2){
            waitEven();
            System.out.println(i+" "+Thread.currentThread().getName());
            notifyOdd();
        }
    }

    private void doPrintEven(){
        for(int i = 0; i < n; i+=2){
            waitOdd();
            System.out.println(i+" "+Thread.currentThread().getName());
            notifyEven();
        }
    }

    private void startPrintOdd(){
        new Thread(){
            @Override
            public void run() {
                doPrintOdd();
            }
        }.start();
    }

    private void startPrintEven(){
        new Thread(){
            @Override
            public void run() {
                doPrintEven();
            }
        }.start();
    }

    public void test(){

        startPrintEven();
        startPrintOdd();
        notifyOdd();
    }
}
