package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class findRadius {

    private boolean fit(int[] houses, int[] heaters, int R){

        int j = 0;
        int i = 0;
        while (i < houses.length && j < heaters.length){
            if(Math.abs(houses[i] - heaters[j]) <= R){
                i++;
            }else{
                j++;
            }
        }

        return i == houses.length;
    }

    public int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int max = 0;
        for(int i = 0; i < houses.length; i++){
            if(max < houses[i]){
                max = houses[i];
            }
        }

        for(int i = 0; i < heaters.length; i++){
            if(max < heaters[i]){
                max = heaters[i];
            }
        }

        int min = 0;
        while (true){
            if(max == min){
                break;
            }
            int mid = (min + max) / 2;
            if(fit(houses, heaters, mid)){
                max = mid;
            }else{
                min = mid+1;
            }
        }
        return min;
    }

    public void test(){
        System.out.println(findRadius(new int[]{1, 2, 3, 4}, new int[]{1,4}));
    }
}
