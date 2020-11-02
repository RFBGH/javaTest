package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();

        for(int i =0, size = nums1.length; i < size; i++){
            set.add(nums1[i]);
        }

        Set<Integer> result = new HashSet<>();
        for(int i = 0, size = nums2.length; i < size; i++){

            int value = nums2[i];
            if(!set.contains(value)){
                continue;
            }

            result.add(value);
        }

        int[] values = new int[result.size()];
        int count = 0;
        for(Integer i : result){
            values[count++] = i;
        }
        return values;
    }

}
