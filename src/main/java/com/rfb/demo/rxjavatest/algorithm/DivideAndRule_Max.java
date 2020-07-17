package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2020/7/14 0014.
 */
public class DivideAndRule_Max {


    public static int calc(List<Integer> a, int start, int end){

        if(start == end){
            return a.get(start);
        }

        if(start + 1 == end){
            int left = a.get(start);
            int right = a.get(end);
            if(right > left){
                return right;
            }
            return left;
        }

        int mid = (start + end) / 2;
        int left = calc(a, start, mid);
        int right = calc(a, mid+1, end);

        if(right > left){
            return right;
        }
        return left;
    }

    public static void test(){

        int n = 100000000;
        Random random = new Random(System.currentTimeMillis());
//        Integer[] array = new Integer[]{83, -63, -67, -68, 23, -18, 83, 24, -77, -42};
        List<Integer> a = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            int value = random.nextInt();
            a.add(value);
        }

//        for(int i = 0; i < n; i++){
//            System.out.print(a.get(i)+" ");
//        }
//        System.out.println();

        long startTime = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(max < a.get(i)){
                max = a.get(i);
            }
        }

        System.out.println("max "+max+" cost "+(System.currentTimeMillis()-startTime));

        startTime = System.currentTimeMillis();
        max = calc(a, 0, a.size()-1);
        System.out.println("max "+max+" cost "+(System.currentTimeMillis()-startTime));
    }

}
