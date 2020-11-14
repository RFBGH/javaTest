package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.Arrays;

public class relativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] counts = new int[1005];
        int n = arr1.length;
        for(int i = 0; i < n; i++){
            counts[arr1[i]]++;
        }

        int m = arr2.length;
        int[] result = new int[n];
        int resultCount = 0;
        for(int i = 0; i < m; i++){
            int count = counts[arr2[i]];
            while (count > 0){
                result[resultCount++] = arr2[i];
                count--;
            }
            counts[arr2[i]] = 0;
        }

        if(resultCount < n){
            int[] rest = new int[n-resultCount];
            int restCount = 0;
            for(int i = 0; i < n; i++){
                if(counts[arr1[i]] == 0){
                    continue;
                }
                rest[restCount++] = arr1[i];
            }
            Arrays.sort(rest);

            for(int i = 0, size = rest.length; i < size; i++){
                result[resultCount++] = rest[i];
            }
        }

        return result;
    }

    public void test(){
        relativeSortArray(new int[]{2,3,1,3,2,4,6,7,9,2,19}, new int[]{2,1,4,3,9,6});
    }

}
