package com.rfb.demo.rxjavatest;

public class SingleInstance {

    final static private SingleInstance sInstance = new SingleInstance();

    public static SingleInstance getsInstance() {
        return sInstance;
    }

    private SingleInstance(){

    }

    public void testSyn(){

        System.out.println("testSyn");

        synchronized (SingleInstance.this){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("after syn");
        }
    }
}
