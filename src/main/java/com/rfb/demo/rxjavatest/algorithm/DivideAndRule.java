package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2020/7/14 0014.
 */
public class DivideAndRule {

    public static long calc(List<Integer>a, int left, int right){

        if(left == right){
            return 0;
        }

        if(left + 1 == right){
            if(a.get(left) > a.get(right)){
                return 1;
            }else{
                int temp = a.get(left);
                a.set(left, a.get(right));
                a.set(right, temp);
                return 0;
            }
        }

        int mid = (left + right) / 2;

        long ans = 0;
        ans += calc(a, left, mid);
        ans += calc(a, mid+1, right);

        int i = left;
        int j = mid+1;
        List<Integer> temp = new ArrayList<>(right-left+1);
        while (i <= mid && j <= right){
            int leftValue = a.get(i);
            int rightValue = a.get(j);

            if(leftValue > rightValue){
                temp.add(leftValue);
                ans += (right-j+1);
                i++;
            }else{
                temp.add(rightValue);
                j++;
            }
        }

        while (i <= mid){
            temp.add(a.get(i++));
        }

        while(j <= right){
            temp.add(a.get(j++));
        }

        i = 0;
        for(int k = left; k <= right; k++){
            a.set(k, temp.get(i++));
        }

        return ans;
    }

    public static void test(){

        int n = 100000;
        Random random = new Random(System.currentTimeMillis());
        List<Integer> a = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            a.add(random.nextInt(100));
        }

        for(int i = 0; i < n; i++){
            System.out.print(a.get(i)+" ");
        }
        System.out.println();

        long start = System.currentTimeMillis();
        List<Integer> b = new ArrayList<>(a);
        long ans = calc(b, 0, a.size() - 1);
        System.out.println("ans "+ans+" cost "+(System.currentTimeMillis()-start));

//        for(int i = 0; i < n; i++){
//            System.out.print(b.get(i)+" ");
//        }
//        System.out.println();

//        for(int i = 0; i < n; i++){
//            System.out.print(a.get(i)+" ");
//        }
//        System.out.println();

        start = System.currentTimeMillis();
        ans = 0;
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n ;j++){
                if(a.get(j) < a.get(i)){
                    ans++;
                }
            }
        }
        System.out.println("ans "+ans+" cost "+(System.currentTimeMillis()-start));
    }

}
