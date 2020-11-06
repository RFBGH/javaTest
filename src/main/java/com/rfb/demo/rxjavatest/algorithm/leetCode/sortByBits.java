package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class sortByBits {

    private int getOneCount(int i){
        int count = 0;
        while (i != 0){
            if((i & 0x1) == 1){
                count++;
            }
            i >>= 1;
        }
        return count;
    }

    public int[] sortByBits(int[] arr) {
        if(arr.length == 0 || arr.length == 1){
            return arr;
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0, size = arr.length; i < size; i++){
            list.add(arr[i]);
        }

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                int count1 = getOneCount(o1);
                int count2 = getOneCount(o2);
                if(count1 < count2){
                    return -1;
                }

                if(count1 > count2){
                    return 1;
                }

                if(o1 < o2){
                    return -1;
                }

                if(o1 > o2){
                    return 1;
                }

                return 0;
            }
        });

        for(int i = 0, size = list.size(); i < size; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

    public void test(){



    }
}
