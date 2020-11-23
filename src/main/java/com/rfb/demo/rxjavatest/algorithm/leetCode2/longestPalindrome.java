package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class longestPalindrome {

    public int longestPalindrome(String s) {
        int[] count = new int[256];
        for(int i = 0, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            count[c]++;
        }

        int sum = 0;
        boolean hasOdd = false;
        for(int i = 0; i < 256; i++){
            if(count[i] % 2 == 0){
                sum += count[i];
            }else{
                sum += count[i]-1;
                hasOdd = true;
            }
        }

        if(hasOdd){
            sum++;
        }
        return sum;
    }
}
