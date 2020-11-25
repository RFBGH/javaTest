package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class characterReplacement {

    public int characterReplacement(String s, int k) {

        int[] buck = new int[26];
        int left = 0;
        int max = 0;
        int n = s.length();
        for(int right = 0; right < n; right++){
            int index = s.charAt(right) - 'A';
            buck[index]++;

            if(max < buck[index]){
                max = buck[index];
            }

            if(right - left + 1 > max+k){
                buck[s.charAt(left)-'A']--;
                left++;
            }
        }

        return n-left;
    }

    public void test(){
        System.out.println(characterReplacement("BAAA", 0));
    }
}
