package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class largeGroupPositions {

    public List<List<Integer>> largeGroupPositions(String s) {

        List<List<Integer>>result = new ArrayList<>();
        int left = 0;
        int i;
        for(i = 1; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == s.charAt(left)){
                continue;
            }

            if(i - left < 3){
                left = i;
                continue;
            }

            List<Integer> segment = new ArrayList<>();
            segment.add(left);
            segment.add(i-1);
            result.add(segment);
            left = i;
        }

        if(i - left >= 3){
            List<Integer> segment = new ArrayList<>();
            segment.add(left);
            segment.add(i-1);
            result.add(segment);
        }

        return result;
    }

    public void test(){
        largeGroupPositions("aaa");
    }
}
