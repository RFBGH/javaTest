package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class isAnagram {

    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()){
            return false;
        }

        int[] charCounts = new int[256];
        for(int i = 0, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            charCounts[c]++;
        }

        for(int i = 0, size = t.length(); i < size; i++){
            char c = t.charAt(i);
            if(charCounts[c] == 0){
                return false;
            }

            charCounts[c]--;
        }

        return true;
    }
}
