package com.rfb.demo.rxjavatest;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public final class PrintUtil {

    private final static Object mLock = new Object();

    public static void print(String message){

        synchronized (mLock){
            System.out.println(message);
        }
    }

    public static void printIntArray(int[] a){

        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
}
