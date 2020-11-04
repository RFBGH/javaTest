package com.rfb.demo.rxjavatest;


import com.rfb.demo.rxjavatest.algorithm.*;
import com.rfb.demo.rxjavatest.algorithm.LCA.LCA;
import com.rfb.demo.rxjavatest.algorithm.LCA.LCA2;
import com.rfb.demo.rxjavatest.algorithm.LCA.LCA3;
import com.rfb.demo.rxjavatest.algorithm.RMQ.RMQ;
import com.rfb.demo.rxjavatest.algorithm.SuffixArray.SuffixArray;
import com.rfb.demo.rxjavatest.algorithm.SuffixArray.SuffixArray2;
import com.rfb.demo.rxjavatest.algorithm.leetCode.*;
import com.rfb.demo.rxjavatest.algorithm.leetCode.segmentTree.Leet218;
import com.rfb.demo.rxjavatest.algorithm.leetCode.traceback.Leet47;
import com.rfb.demo.rxjavatest.algorithm.leetCode.traceback.Leet90;
import com.rfb.demo.rxjavatest.algorithm.maxFlow.POJ2112;
import com.rfb.demo.rxjavatest.algorithm.sort.BucketSort;
import com.rfb.demo.rxjavatest.algorithm.stack.Test;
import com.rfb.demo.rxjavatest.productAndComsumer.IComsumer;
import com.rfb.demo.rxjavatest.productAndComsumer.IProducer;
import com.rfb.demo.rxjavatest.productAndComsumer.ProducerAndComsumer;
import com.rfb.demo.rxjavatest.rxjava3.RxJava3;
import com.rfb.demo.rxjavatest.thread.SynTest;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static boolean testEqual(String str, Pattern pattern, String res) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group().equals(res);
        } else {
            return res == null || res.isEmpty();
        }
    }

    private static final String PHONE_URL_STR = "((?<=\\D|^)(((\\+86)|(86))?(1[3578]\\d{9}))(?=\\D|$)" +

            "|(?<=\\D|^)(400|800)([0-9\\-]{7,10})(?=\\D|$)" +

            "|(?<=\\D|^)(9\\d{4})(?=\\D|$)" +

            "|(?<=\\D|^)(100\\d{2})(?=\\D|$)" +

            "|(?<=\\D|^)(0[1-9]\\d{1,2})?([1-9]\\d{6,7})(?=\\D|$))";


    //电话正则哦
    public static final Pattern PHONE_URL = Pattern.compile(PHONE_URL_STR);


    private static void testEx(){

        if (testEqual("中文13012312312中文", PHONE_URL,"13012312312")) {
            System.out.println("OK");
        }else{
            System.out.println("faild");
        }
        if(testEqual("中文059130303030中文", PHONE_URL,"059130303030")){
            System.out.println("OK");
        }else{
            System.out.println("faild");
        }
        if(testEqual("中文01088888888中文", PHONE_URL,"01088888888")){
            System.out.println("OK");
        }else{
            System.out.println("faild");
        };
        if(testEqual("中文12345678中文", PHONE_URL,"")){
            System.out.println("OK");
        }else{
            System.out.println("faild");
        }
        if(testEqual("中文95700中文", PHONE_URL,"95700")){
            System.out.println("OK");
        }else{
            System.out.println("faild");
        }

    }


    private static final String NORMAL_CHAR = "a-zA-Z0-9";

    private static final String EMAIL_CHAR = NORMAL_CHAR + "\\+\\-_%'";

    private static final String EMAIL_ADDRESS_LOCAL_PART =
            "[" + EMAIL_CHAR + "]" + "(?:[" + EMAIL_CHAR + "\\.]{0,62}[" + EMAIL_CHAR + "])?";

    private static final String TLD_CHAR = "a-zA-Z";

    private static final String TLD = "(" + "[" + TLD_CHAR + "]{2,63}" + ")";

    private static final String NORMAL_LABEL = "[" + NORMAL_CHAR + "](?:[" + NORMAL_CHAR + "_\\-]{0,61}[" + NORMAL_CHAR + "]){0,1}";

    private static final String HOST_NAME = "(" + NORMAL_LABEL + "\\.)+" + TLD;

    private static final String EMAIL_ADDRESS_DOMAIN = "(?=.{1,255}(?:\\s|$|^))" + HOST_NAME;


    public static final Pattern EMAIL_ADDRESS = Pattern.compile("("
            + "(?:" + EMAIL_ADDRESS_LOCAL_PART
            + "@"
            + EMAIL_ADDRESS_DOMAIN + ")"
            + ")"
    );

    public static boolean isEmailAddress(String uri) {
        return EMAIL_ADDRESS.matcher(uri).find();
    }

    public static class FlyPig{

        @Override
        protected void finalize() throws Throwable {
            super.finalize();


        }
    }


    class Tree{
        public List<Tree> sons = new ArrayList<>();
    }

    public static void dfs(List<Tree> parents, int curLevel){

        List<Tree> sons = new ArrayList<>();
        for(Tree parent : parents) {
            sons.addAll(parent.sons);
        }

        if(curLevel % 2 == 0){
            for(Tree son : sons){
                System.out.print(son);
            }
        }else{

            List<Tree> temp = new ArrayList<>(sons);
            Collections.reverse(temp);

            for(Tree son : temp){
                System.out.print(son);
            }
        }

        dfs(sons, (curLevel+1)%2);
    }


    public static void solve(int[] prices){

        int maxProfit = 0;
        int minPrice = prices[0];
        for(int i = 1, size = prices.length; i < size; i++){
            if(prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }

            if(prices[i] < minPrice){
                minPrice = prices[i];
            }
        }
    }

    private static boolean isValidLow(int[] prices, int i){

        if(i == 0){
            if (prices[i+1] > prices[i]) {
                return true;
            }
            return false;
        }

        if(i == prices.length-1){
            return false;
        }

        if(prices[i+1] > prices[i] && prices[i-1] >= prices[i]){
            return true;
        }

        return false;
    }

    private static boolean isValidHigh(int[] prices, int i){

        if(i == 0){
            return false;
        }

        if(i == prices.length-1){
            if(prices[i] > prices[i-1]){
                return true;
            }
            return false;
        }

        if(prices[i] > prices[i+1] && prices[i] >= prices[i-1]){
            return true;
        }
        return false;
    }

    public static void solve2(int[] prices){

        int validLowPos = 0;
        int size = prices.length;
        int allProfit = 0;
        while (validLowPos < size){

            if(!isValidLow(prices, validLowPos)){
                validLowPos++;
                continue;
            }

            int nextValidHighPos = validLowPos+1;
            while (nextValidHighPos < size){
                if(!isValidHigh(prices, nextValidHighPos)){
                    nextValidHighPos++;
                    continue;
                }
                break;
            }

            if(nextValidHighPos == size){
                break;
            }

            allProfit += prices[nextValidHighPos] - prices[validLowPos];
            validLowPos = nextValidHighPos+1;
        }

        System.out.println(allProfit);
    }

    public static void solve3(int[] prices){

        int allProfit = 0;
        for(int i = 1, size = prices.length; i < size; i++){
            if(prices[i] - prices[i-1] > 0){
                allProfit += prices[i] - prices[i-1];
            }
        }

    }

    public static void main(String[] args) {

        candy sum2 = new candy();
        sum2.test();
//        AtomicInteger atomicInteger;
//        atomicInteger.compareAndSet()
//
//        ReentrantLock lock;
//        lock.lock();
//
//        FlattenTree.test();
//        solve2(new int[]{7,1,5,3,6,4});
//        solve2(new int[]{7,6,4,3,2,1});

//        ProducerAndComsumer producerAndComsumer = new ProducerAndComsumer<String>(new IProducer<String>() {
//            @Override
//            public String produce() {
//                return "fuck";
//            }
//        }, new IComsumer<String>() {
//            @Override
//            public void comsume(String o) {
//                System.out.println(Thread.currentThread().getName()+" "+o);
//            }
//        });
//
//        producerAndComsumer.start();
//        System.out.println(isEmailAddress("6233737@qq.com"));
//        System.out.println(isEmailAddress("6233737@qqcom"));
//        System.out.println(isEmailAddress("6233737qq.com"));
//        System.out.println(isEmailAddress("6233737.132@qq.cm"));
//
//        final Object lock = new Object();
//        List<String> data = new ArrayList<>();
//
//        new Thread(){
//
//            @Override
//            public void run() {
//
//                while (true) {
//                    synchronized (lock) {
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    for (String dat : data) {
//                        System.out.println(dat);
//                    }
//                }
//            }
//        }.start();

//        testEx();
//        SynTest.test();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

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
//        rxjavaTest.testOnErrorResume11();
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

