package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class shortestSeq {

    public int[] shortestSeq(int[] big, int[] small) {


        Map<Integer, Integer> checkMap = new HashMap<>();

        for(int i = 0, size = small.length; i < size; i++){
            int t = small[i];
            if(checkMap.containsKey(t)){
                int sum = checkMap.get(t);
                checkMap.put(t, sum+1);
            }else{
                checkMap.put(t, 1);
            }
        }

        Map<Integer, Integer> sumMap = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int targetFrom = 0;
        int targetTo = 0;
        int sum = 0;
        int start = 0;
        for(int i = 0, size = big.length; i < size; i++){

            int t = big[i];
            if(!checkMap.containsKey(t)){
                continue;
            }

            Integer count = sumMap.get(t);
            if(count == null){
                count = 1;
            }else{
                count++;
            }

            sumMap.put(t, count);
            if(count <= checkMap.get(t)){
                sum++;
            }

            while (true){

                int from = big[start];
                if(!checkMap.containsKey(from)){
                    start++;
                    continue;
                }

                Integer temp = sumMap.get(from);
                if(temp == null){
                    break;
                }

                if(temp > checkMap.get(from)){
                    temp--;
                    sumMap.put(from, temp);
                    start++;
                    continue;
                }

                break;
            }

            if(sum == small.length){
                if(i - start + 1 < min){
                    min = i - start + 1;
                    targetFrom = start;
                    targetTo = i;
                }
            }
        }

        if(sum != small.length){
            return new int[]{};
        }

        return new int[]{targetFrom, targetTo};
    }

    public void test(){
//        System.out.println(shortestSeq(new int[]{7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7 }, new int[]{1,5,9}));
        System.out.println(shortestSeq(new int[]{1,2,3 }, new int[]{4}));
    }

}
