package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class smallestSubsequence {

    private List<Character> getSmallest(String s, int k){

        int start = 0;
        int rest = k;
        int n = s.length();
        List<Character> result = new ArrayList<>();
//        while (true){
//
//            for(int i = start, size = n-rest+1; i < size; i++){
//
//            }
//
//        }

        return result;
    }

    public String smallestSubsequence(String s) {
        Set<Character> set = new HashSet<>();
        for(int i = 0, size = s.length(); i < size; i++){
            set.add(s.charAt(i));
        }

        return null;
    }
}
