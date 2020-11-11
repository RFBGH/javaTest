package com.rfb.demo.rxjavatest.algorithm.niuke;

import java.util.Arrays;

public class findKth {

    private int dfs(int[] a, int from, int to, int k){

        int i = from+1;
        int j = to;

        while (true){

            while (j > from && a[j] >= a[from]){
                j--;
            }

            while (i <= to && a[i] < a[from]){
                i++;
            }

            if(i >= j){
                break;
            }

            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        int temp = a[from];
        a[from] = a[j];
        a[j] = temp;

        if(j == k){
            return a[j];
        }

        if(j < k){
            return dfs(a, j+1, to, k);
        }else{
            return dfs(a, from, j-1, k);
        }
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        return dfs(a, 0, n-1, K-1);
    }

    public void test(){

        int[] a = new int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
        Arrays.sort(a);
        System.out.println(a[25]);

        for(int i = 0; i < a.length; i++){
            if(a[i] == 986578){
                System.out.println(i);
                break;
            }
        }

        System.out.println(findKth(a, 49, 23));


    }
}
