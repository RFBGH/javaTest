package com.rfb.demo.rxjavatest;


import com.rfb.demo.rxjavatest.algorithm.getMoneyAmount;
import com.rfb.demo.rxjavatest.algorithm.leetCode3.*;
import com.rfb.demo.rxjavatest.algorithm.leetcode4.*;
import com.rfb.demo.rxjavatest.kotlin.HelloKotlin;
import com.rfb.demo.rxjavatest.kotlin.leetcode.*;
import com.rfb.demo.rxjavatest.kotlin.leetcode.maxCoins;
import com.rfb.demo.rxjavatest.rxjava3.RxJava3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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

    private static void dfs(List<Character> list, int leftCount, int rightCount, int n){

        if(rightCount == n){
            for(int i = 0; i < list.size(); i++){
                System.out.print(list.get(i));
            }
            System.out.println();
            return;
        }

        if(leftCount < n){
            list.add('(');
            dfs(list, leftCount+1, rightCount, n);
            list.remove(list.size()-1);
        }

        if(rightCount < leftCount){
            list.add(')');
            dfs(list, leftCount, rightCount+1, n);
            list.remove(list.size()-1);
        }
    }

    private void maj(){

        int[] a = new int[]{};
        int m = 0, count = 0;
        for(int i = 0; i < a.length; i++){
            if(count == 0){
                m = a[i];
                count++;
            }else{
                if(m == a[i]){
                    count++;
                }else{
                    count--;
                }
            }
        }



    }


    private static void tt(List<? extends Number> list){

    }

    private static void dd(List<? super Number> list){


    }

    public interface Test<T>{

        <S extends T> S test(T t);

        <S extends T> List<S> test1(T t);
    }

    public static class TestImpl implements Test<CharSequence>{

        @Override
        public <S extends CharSequence> S test(CharSequence s) {
            return null;
        }

        @Override
        public <S extends CharSequence> List<S> test1(CharSequence charSequence) {
            return null;
        }
    }

    private int solve1(int[] a){

        int max = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i: a){
            Integer count = map.get(i);
            if(count == null){
                count = 1;
            }else{
                count++;
            }
            map.put(i, count);

            if(count > max){
                max = count;
                ans = i;
            }else if(count == max && i < ans){
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

//        RxJava3 rxJava3 = new RxJava3();
//        rxJava3.testSubscrib();
//        ConsumerAndProductor consumerAndProductor = new ConsumerAndProductor();
//        consumerAndProductor.test(new int[]{10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35});
//        PrintOddEven printOddEven = new PrintOddEven(100);
//        printOddEven.test();
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//
//        tt(list);
//
//        List<? super Number> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(2.0f);
//        list1.add(1);
//
//        List<Object> list2 = new ArrayList<Object>();
//        dd(list2);


//        Object o = new Object();
//        synchronized (o){
//            System.out.println("xxxx");
//            synchronized (o){
//                System.out.println("yyyy");
//            }
//            System.out.println("xxxx");
//        }
//        Integer a = 1000, b = 1000;
//        System.out.println(a == b);//1
//        Integer c = 100, d = 100;
//        System.out.println(c == d);
//        NumMatrix matrix = new NumMatrix(new int[][]{
//                {3, 0, 1, 4, 2},
//                {5, 6, 3, 2, 1},
//                {1, 2, 0, 1, 5},
//                {4, 1, 0, 1, 7},
//                {1, 0, 3, 0, 5}
//        });
//        matrix.sumRegion(2, 1, 4,3);
//        TaskList.test(args);
//        System.out.println(Objects.hash(1, 2));
//        System.out.println(Objects.hash(2, 1));
//        AllOne.test();

//        LFUCache.test();

//        NumArray1.test();
//        WordFilter.test();

//        StreamChecker streamChecker = new StreamChecker(new String[]{"ab","ba","aaab","abab","baa"});
//
//        char[] test = new char[]{'a','a','a','a','a','b','a','b','a','b','b','b','a','b','a','b','b','b','b','a','b','a','b','a','a','a','b','a','a','a'};
//        for(char t : test){
//            System.out.println(streamChecker.query(t));
//        }

        generateParenthesis test = new generateParenthesis();
        System.out.println(test.generateParenthesis(4));
//        System.out.println(test.findCheapestPrice(17, new int[][]{{0,12,28},{5,6,39},{8,6,59},{13,15,7},{13,12,38},{10,12,35},{15,3,23},{7,11,26},{9,4,65},{10,2,38},{4,7,7},{14,15,31},{2,12,44},{8,10,34},{13,6,29},{5,14,89},{11,16,13},{7,3,46},{10,15,19},{12,4,58},{13,16,11},{16,4,76},{2,0,12},{15,0,22},{16,12,13},{7,1,29},{7,14,100},{16,1,14},{9,6,74},{11,1,73},{2,11,60},{10,11,85},{2,5,49},{3,4,17},{4,9,77},{16,3,47},{15,6,78},{14,1,90},{10,5,95},{1,11,30},{11,0,37},{10,4,86},{0,8,57},{6,14,68},{16,8,3},{13,0,65},{2,13,6},{5,13,5},{8,11,31},{6,10,20},{6,2,33},{9,1,3},{14,9,58},{12,3,19},{11,2,74},{12,14,48},{16,11,100},{3,12,38},{12,13,77},{10,9,99},{15,13,98},{15,12,71},{1,4,28},{7,0,83},{3,5,100},{8,9,14},{15,11,57},{3,6,65},{1,3,45},{14,7,74},{2,10,39},{4,8,73},{13,5,77},{10,0,43},{12,9,92},{8,2,26},{1,7,7},{9,12,10},{13,11,64},{8,13,80},{6,12,74},{9,7,35},{0,15,48},{3,7,87},{16,9,42},{5,16,64},{4,5,65},{15,14,70},{12,0,13},{16,14,52},{3,10,80},{14,11,85},{15,2,77},{4,11,19},{2,7,49},{10,7,78},{14,6,84},{13,7,50},{11,6,75},{5,10,46},{13,8,43},{9,10,49},{7,12,64},{0,10,76},{5,9,77},{8,3,28},{11,9,28},{12,16,87},{12,6,24},{9,15,94},{5,7,77},{4,10,18},{7,2,11},{9,5,41}}, 13, 4, 13));
//       System.out.println(test.gardenNoAdj(3, new int[][] {{1,2},{2,3},{3, 1}}));
//                HelloKotlin.Companion.test();
//        maxProfitAssignment sum2 = new maxProfitAssignment();
//        sum2.test();
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
//        rxjavaTest.testFilter22();
////
//        try {
//            Thread.sleep(20000);
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

