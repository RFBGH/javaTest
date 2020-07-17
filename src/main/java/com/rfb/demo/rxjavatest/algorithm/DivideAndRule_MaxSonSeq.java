package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2020/7/14 0014.
 */
public class DivideAndRule_MaxSonSeq {


    public static int calc(List<Integer> a, int start, int end){

        if(start == end){
            int value = a.get(start);
            if(value > 0){
                return value;
            }
            return 0;
        }

        int mid = (start + end)/2;
        int leftMax = calc(a, start, mid);
        int rightMax = calc(a, mid+1, end);

        int sum = 0;
        int maxSubLeft = 0;
        for(int i = mid; i >= start; i--){
            sum += a.get(i);
            if(maxSubLeft < sum){
                maxSubLeft = sum;
            }
        }

        sum = 0;
        int maxSubRight = 0;
        for(int i = mid+1; i <= end; i++){
            sum += a.get(i);
            if(maxSubRight < sum){
                maxSubRight = sum;
            }
        }

        int middleMax = maxSubLeft + maxSubRight;

        int temp = leftMax;
        if(middleMax > leftMax){
            temp = middleMax;
        }

        if(rightMax > temp){
            return rightMax;
        }
        return temp;
    }


    private static int calc2(List<Integer> a){

        int temp = a.get(0);
        int n = a.size();
        int max = 0;

        for(int i = 1; i < n; i++){

            if(temp < 0){
                temp = a.get(i);
            }else{
                temp += a.get(i);
            }

            if(max < temp){
                max = temp;
            }
        }

        return max;
    }


    public static void test(){

        int n = 10000;
        Random random = new Random(System.currentTimeMillis());
//        Integer[] array = new Integer[]{83, -63, -67, -68, 23, -18, 83, 24, -77, -42};
        List<Integer> a = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int value = random.nextInt(100);

            if(random.nextInt(10) < 4){
                value = -value;
            }
            a.add(value);
        }

        for(int i = 0; i < n; i++){
            System.out.print(a.get(i)+" ");
        }
        System.out.println();

        long start = System.currentTimeMillis();
        int ans = calc(a, 0, a.size()-1);
        System.out.println("ans "+ans+" cost "+(System.currentTimeMillis()-start));


        start = System.currentTimeMillis();
        ans = calc2(a);
        System.out.println("ans "+ans+" cost "+(System.currentTimeMillis()-start));


        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int sum = 0;
                for(int k = i; k <= j; k++){
                    sum += a.get(k);
                }

                if(max < sum){
                    max = sum;
                    left = i;
                    right = j;
                }
            }
        }

        System.out.println("max "+max+" "+left+" "+right);


    }

}
