package com.rfb.demo.rxjavatest.algorithm;

import java.util.Random;

/**
 * Created by Administrator on 2020/7/23 0023.
 */
public class QuickSort {

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void QSort(int[] a, int left, int right){

        if(left == right){
            return;
        }

        if(left + 1 == right){
            if(a[left] > a[right]){
                swap(a, left, right);
            }
            return;
        }


        int hold = a[right];
        int i = left;
        int j = right-1;

        while (true){

            while (i <= right-1 && a[i] < hold){i++;};
            while (j >= left && a[j] > hold){j--;};
            if(j > i){
                swap(a, i, j);
                i++;
                j--;
            }else{
                break;
            }
        }

        if(i != right){
            swap(a, i, right);
        }

        if(i - 1 > left){
            QSort(a, left, i-1);
        }

        if(i + 1 < right){
            QSort(a, i+1, right);
        }
    }

    public void sort(int a[], int n){

        for(int i = 0; i < n; i++){
            int min = a[i];
            int k = i;
            for(int j = i+1; j < n; j++){
                if(a[j] < min){
                    min = a[j];
                    k = j;
                }
            }
            if(k != i){
                swap(a, k, i);
            }
        }
    }

    public static void test(){

        int n = 10000;
        int[] a = new int[n];//{2, 7, 3, 0, 2, 3, 0, 9, 6, 3 };
        int[] b = new int[n];//{2, 7, 3, 0, 2, 3, 0, 9, 6, 3 };
        Random random = new Random();
        for(int i = 0; i < n; i++){
            a[i] = random.nextInt(n);
            b[i] = a[i];
        }

        for(int i = 0; i < n; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();

        QuickSort sort = new QuickSort();
        sort.QSort(a, 0, n-1);

        sort.sort(b, n);

        for(int i = 0; i < n; i++){
            System.out.print(b[i]+" ");
        }
        System.out.println();

        for(int i = 0; i < n; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();

    }

}
