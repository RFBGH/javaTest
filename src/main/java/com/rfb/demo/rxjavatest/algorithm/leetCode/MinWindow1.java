package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class MinWindow1 {

    private int nextIndex(String s, int start, Map<Character, Integer> map){
        for(int i = start, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                return i;
            }
        }
        return s.length();
    }

    public String minWindow(String s, String t) {

        if(t.length() > s.length()){
            return "";
        }

        if(t.length() == 1 && s.contains(t)){
            return t;
        }

        Map<Character, Integer> sumMap = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0, size = t.length(); i < size; i++){
            char c = t.charAt(i);
            if(map.containsKey(c)){
                Integer count = map.get(c);
                map.put(c, count+1);
            }else{
                map.put(c, 1);
            }
            sumMap.put(c, 0);
        }

        int size = s.length();
        int start = nextIndex(s, 0, map);

        if(start == size){
            return "";
        }

        char c = s.charAt(start);
        sumMap.put(c, 1);

        int from = start;
        int to = size;

        int sum = 0;
        if(sumMap.get(c).equals(map.get(c))){
            sum++;
        }

        for(int i = start+1; i < size; i++){

            c = s.charAt(i);
            if(!sumMap.containsKey(c)){
                continue;
            }

            int count = sumMap.get(c);
            count++;
            sumMap.put(c, count);
            if(sumMap.get(c) < map.get(c)){
                continue;
            }

            if(sumMap.get(c).equals(map.get(c))){
                sum += sumMap.get(c);
                if(sum == t.length()){
                    if(i - start < to - from){
                        to = i;
                        from = start;
                    }
                }
                continue;
            }

            char startC = s.charAt(start);
            if(startC != c){
                continue;
            }

            count--;
            sumMap.put(c, count);

            while (true){
                start = nextIndex(s, start+1, sumMap);
                if(start == size){
                    break;
                }

                startC = s.charAt(start);
                if(sumMap.get(startC) <= map.get(startC)){
                    break;
                }

                count = sumMap.get(startC);
                count--;
                sumMap.put(startC, count);
            }

            if(sum == t.length()){
                if(i - start < to - from){
                    to = i;
                    from = start;
                }
            }
        }

        if(to < size && to >= from){
            return s.substring(from, to+1);
        }
        return "";
    }

    public void test(){
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("babb", "baba"));
    }
}
