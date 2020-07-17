package com.rfb.demo.rxjavatest;


import com.rfb.demo.rxjavatest.algorithm.*;
import com.rfb.demo.rxjavatest.funny.AB_MinMax;
import com.rfb.demo.rxjavatest.funny.BinaryInsert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class Main {

    private static Map<String, Object> lockMap = new ConcurrentHashMap<String, Object>();

    public static void test(){

        String key = "1111";

        Object lock = lockMap.get(key);
        if(lock == null){
            synchronized (lockMap){
                lock = lockMap.get(key);
                if(lock == null){
                    lock = new Object();
                    lockMap.put(key, lock);
                }
            }
        }

        synchronized (lock){

            System.out.println("delay start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("delay finish");
        }

    }


    public static class Exception1 extends Exception{


    }

    public static class Excpetion2 extends Exception{


    }

    private static void testE() throws Exception1{
        throw new RuntimeException("exxx");
    }

    public static void main(String[] args) {

        DivideAndRule_MaxSonSeq.test();
//        try{
//            testE();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("end");
//        Sum.countSum(10, 10);

//        BigInteger b1 = new BigInteger("1111111111111111111111111111111111111111111");
//        BigInteger b2 = new BigInteger("23377777777777777777777777777777777777777777");
//
//        System.out.println(b1.multiply(b2));

//        Thread t1 = new Thread(){
//            @Override
//            public void run() {
//
//                test();
//
//            }
//        };
//
//
//        Thread t2 = new Thread(){
//            @Override
//            public void run() {
//
//                test();
//
//            }
//        };
//
//        t1.start();
//        t2.start();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        AB_MinMax ab_minMaxa = new AB_MinMax();
//        ab_minMaxa.deal();

//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testBlock();
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        BinaryInsert.test();
//        Map<Long, Long> map = new ConcurrentHashMap<>();
//        map.put(0L, 0L);
//
//        long value = map.get(0L);
//        System.out.println(value);

//        String s ="xxxxx";
//        String ss[] = s.split("\\.");
//
//        String mime = null;
//        int index = s.lastIndexOf(".");
//        if(index > 0){
//            mime = s.substring(index+1);
//        }
//        System.out.println(mime);
//
//        System.out.println(ss.length);
//        for(int i = 0, size = ss.length; i < size; i++){
//            System.out.println(ss[i]);
//        }
    }

}

