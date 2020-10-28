package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class LongPalindrome {

    private int palindrome(String s, int k1, int k2){

        int count = 0;
        int size = s.length();
        while (k1 >= 0 && k2 < size){

            if(s.charAt(k1) != s.charAt(k2)){
                break;
            }

            k1--;
            k2++;
            count++;
        }
        return count;
    }

    public String longestPalindrome(String s) {

        int max = 0;
        int from = 0;
        int to = 0;
        int size = s.length();
        for(int i = 1; i < size; i++){

            int count = palindrome(s, i-1, i+1);
            if(count != 0){
                int len = count*2+1;
                if(len > max){
                    max = len;
                    from = i-count;
                    to = i+count;
                }
            }

            count = palindrome(s, i-1, i);
            if(count != 0){
                int len = count*2;
                if(len > max){
                    max = len;
                    from = i-count;
                    to = i-1+count;
                }
            }
        }

        return s.substring(from, to+1);
    }

    public void test(){
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("aaaa"));
    }
}
