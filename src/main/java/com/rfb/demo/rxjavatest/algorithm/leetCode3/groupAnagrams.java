package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class groupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        for(int i = 0; i < strs.length; i++){

            if(used[i]){
                continue;
            }

            used[i] = true;
            int[] counts = new int[26];
            for(int k = 0; k < strs[i].length(); k++){
                counts[strs[i].charAt(k)-'a']++;
            }

            List<String>list = new ArrayList<>();
            list.add(strs[i]);
            for(int j = i+1; j < strs.length; j++){

                if(used[j]){
                    continue;
                }

                int sum = strs[i].length();
                int[] temp = new int[26];
                for(int k = 0; k < 26; k++){
                    temp[k] = counts[k];
                }

                int k;
                for(k = 0; k < strs[j].length(); k++){
                    int index = strs[j].charAt(k)-'a';
                    if(temp[index] > 0){
                        temp[index]--;
                        sum--;
                    }else{
                        break;
                    }
                }

                if(k != strs[j].length()){
                    continue;
                }

                if(sum != 0){
                    continue;
                }

                used[j] = true;
                list.add(strs[j]);
            }

            result.add(list);
        }

        return result;
    }

    public void test(){

        groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

    }
}
