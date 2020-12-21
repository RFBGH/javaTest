package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class smallestRange1 {

    public int[] smallestRange(List<List<Integer>> nums) {

        int from = -100000;
        int to = 100000;

        int n = nums.size();
        int[] indexs = new int[n];
        while (true){

            int max = Integer.MIN_VALUE;
            int maxIndex = -1;
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int i = 0; i < n; i++){

                int value = nums.get(i).get(indexs[i]);
                if(max < value){
                    max = value;
                    maxIndex = i;
                }

                if(min > value){
                    min = value;
                    minIndex = i;
                }
            }

            if(max - min < to - from){
                from = min;
                to = max;
            }

            if(indexs[minIndex] == nums.get(minIndex).size()-1){
                break;
            }

            indexs[minIndex]++;
        }

        return new int[]{from, to};
    }

    public void test(){
        List<List<Integer>>lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{4,10,15,24,26}));
        lists.add(list);
        list = new ArrayList<>(Arrays.asList(new Integer[]{0,9,12,20}));
        lists.add(list);
        list = new ArrayList<>(Arrays.asList(new Integer[]{5,18,22,30}));
        lists.add(list);

        smallestRange(lists);
    }
}
