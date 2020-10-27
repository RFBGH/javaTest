package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LongestSonString {

    public int lengthOfLongestSubstring(String s) {

        int max = 0;
        Map<Character, Integer> integerMap = new HashMap<>();
        int fromIndex = -1;
        for(int i = 0, size = s.length(); i < size; i++){
            Character c = s.charAt(i);
            if(!integerMap.containsKey(c)){
                integerMap.put(c, i);
                if(i - fromIndex > max){
                    max = i - fromIndex;
                }
            }else{
                int newfromIndex = integerMap.get(c);

                for(int j = fromIndex+1; j < newfromIndex; j++){
                    Character t = s.charAt(j);
                    integerMap.remove(t);
                }
                fromIndex = newfromIndex;
                integerMap.put(c, i);
            }
        }

        return max;
    }

    public void test(){

        System.out.println(lengthOfLongestSubstring("tmmzuxt"));

        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));


    }

}
