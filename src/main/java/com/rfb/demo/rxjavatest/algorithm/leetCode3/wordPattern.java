package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class wordPattern {

    public boolean wordPattern(String pattern, String s) {

        String[] map = new String[26];
        String[] words = s.split(" ");
        Set<String> takes = new HashSet<>();

        if(pattern.length() != words.length){
            return false;
        }

        for(int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            String word = words[i];
            if(map[c-'a'] == null){
                if(takes.contains(word)){
                    return false;
                }
                map[c-'a'] = word;
                takes.add(word);
            }else{

                String temp = map[c-'a'];
                if(!temp.equals(word)){
                    return false;
                }
            }
        }
        return true;
    }

}
