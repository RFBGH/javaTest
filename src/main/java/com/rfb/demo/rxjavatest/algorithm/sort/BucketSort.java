package com.rfb.demo.rxjavatest.algorithm.sort;

/**
 * Created by Administrator on 2020/8/13 0013.
 */
public class BucketSort {

    private static void sort(int [] a, int n){

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(max < a[i]){
                max = a[i];
            }
        }

        int count = 0;
        while (max > 0){
            max /= 10;
            count++;
        }

        int[] bucket = new int[10];
        int base = 1;
        int[] b = new int[n];
        for(int i = 0; i < count; i++){

            for(int j = 0; j < 10; j++){
                bucket[j] = 0;
            }

            for(int j = 0; j < n; j++){
                int cur = (a[j] / base) % 10;
                bucket[cur]++;
            }

            for(int j = 1; j < 10; j++){
                bucket[j] += bucket[j-1];
            }

            for(int j = n-1; j >= 0; j--){
                int index = (a[j] / base) % 10;
                b[--bucket[index]] = a[j];
            }

            int[] t = a;
            a = b;
            b = t;
            base *= 10;
        }

        for(int i = 0; i < n; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();

    }


    public static void sort2(int [] a, int n){

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(max < a[i]){
                max = a[i];
            }
        }

        int count = 0;
        while (max > 0){
            max /= 10;
            count++;
        }

        int[] bucket = new int[10];
        int base = 1;
        int[] b = new int[n];
        int[] t = new int[n];

        for(int i = 0; i < n; i++){
            b[i] = i;
        }

        for(int i = 0; i < count; i++){

            for(int j = 0; j < 10; j++){
                bucket[j] = 0;
            }

            for(int j = 0; j < n; j++){
                int cur = (a[b[j]] / base) % 10;
                bucket[cur]++;
            }

            for(int j = 1; j < 10; j++){
                bucket[j] += bucket[j-1];
            }

            for(int j = n-1; j >= 0; j--){
                int index = (a[b[j]] / base) % 10;
                t[--bucket[index]] = b[j];
            }

            int [] temp = b;
            b = t;
            t = temp;

            base *= 10;
        }

        for(int i = 0; i < n; i++){
            System.out.print(a[b[i]]+" ");
        }
        System.out.println();

    }

    public static void test(){

        int[] a = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 120, 111, 323, 48};
//        sort(a, a.length);
        sort2(a, a.length);
    }

}
