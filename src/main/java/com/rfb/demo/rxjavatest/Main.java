package com.rfb.demo.rxjavatest;


import com.rfb.demo.rxjavatest.algorithm.*;
import com.rfb.demo.rxjavatest.algorithm.LCA.LCA;
import com.rfb.demo.rxjavatest.algorithm.LCA.LCA2;
import com.rfb.demo.rxjavatest.algorithm.LCA.LCA3;
import com.rfb.demo.rxjavatest.algorithm.RMQ.RMQ;
import com.rfb.demo.rxjavatest.algorithm.SuffixArray.SuffixArray;
import com.rfb.demo.rxjavatest.algorithm.SuffixArray.SuffixArray2;
import com.rfb.demo.rxjavatest.algorithm.leetCode.segmentTree.Leet218;
import com.rfb.demo.rxjavatest.algorithm.leetCode.traceback.Leet47;
import com.rfb.demo.rxjavatest.algorithm.leetCode.traceback.Leet90;
import com.rfb.demo.rxjavatest.algorithm.maxFlow.POJ2112;
import com.rfb.demo.rxjavatest.algorithm.sort.BucketSort;
import com.rfb.demo.rxjavatest.algorithm.stack.Test;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class Main {

    private static Map<String, Object> lockMap = new ConcurrentHashMap<String, Object>();

    public static void test() {

        String key = "1111";

        Object lock = lockMap.get(key);
        if (lock == null) {
            synchronized (lockMap) {
                lock = lockMap.get(key);
                if (lock == null) {
                    lock = new Object();
                    lockMap.put(key, lock);
                }
            }
        }

        synchronized (lock) {

            System.out.println("delay start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("delay finish");
        }

    }


    public static class Exception1 extends Exception {


    }

    public static class Excpetion2 extends Exception {


    }

    private static void testE() throws Exception1 {
        throw new RuntimeException("exxx");
    }

    public static char[] result = new char[100];
    public static int cur = 0;
    public static boolean[] used = new boolean[100];

    public static int resultCount = 0;

    private static void dfs(int leftCount, int rightCount, int n) {

        if(cur == 2*n){
            resultCount ++;
//            for(char c:result){
//                System.out.print(c);
//            }
//            System.out.println();
            return;
        }

        if(leftCount < n){
            result[cur++] = '(';
            dfs(leftCount + 1, rightCount, n);
            cur--;
        }

        if(leftCount > rightCount){
            result[cur++] = ')';
            dfs(leftCount, rightCount+1, n);
            cur--;
        }
    }



    public static void main(String[] args) {

        Leet218.test();
//        int n = 10;
//        int[][] G = new int[n][n];
//        int[] dist = new int[n];
//
//        boolean update = true;
//        while (update){
//
//            for(int i = 0; i < n; i++){
//                    if(dist[0] > dist[0] + G[0][i]){
//                        dist[i] = dist[0] + G[0][i];
//                        update = true;
//                    }
//            }
//
//        }

//        ReplaySubject<String> publishSubject = ReplaySubject.create();
//
//        publishSubject.onNext("1");
//        publishSubject.onNext("1");
//        publishSubject.onNext("1");
//        publishSubject.onCompleted();
//
//        publishSubject
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
//                        System.out.println(s);
//                    }
//                });

//        PublishSubject<String> publishSubject = PublishSubject.create();
//        publishSubject.onNext("11");
//        publishSubject.onCompleted();
//
//        System.out.println(publishSubject.hasCompleted());

//        BucketSort.test();
//        SuffixArray2.test();
//        LCA2.test();
//        System.out.println(Integer.MAX_VALUE);
//        long l = Long.MAX_VALUE;
//        System.out.println(l);
//        System.out.println(1000000000*200L);
//        try {
//            com.rfb.demo.rxjavatest.algorithm.maxFlow.poj2391.Main.main(args);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println(Long.MAX_VALUE);
//        String s = "14829735431805717965";
//        System.out.println(Long.parseLong(s));
//        MaxFlow_PigFarm.test();
//        GCutPoint.test();
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

