package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class MinWindow {

    private int nextIndex(String s, int start, int[] map){
        for(int i = start, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            if(map[c] != 0){
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

        int[] sumMap = new int[256];
        int[] map = new int[256];

        for(int i = 0, size = t.length(); i < size; i++){
            char c = t.charAt(i);
            map[c]++;
        }

        int size = s.length();
        int start = nextIndex(s, 0, map);

        if(start == size){
            return "";
        }

        char c = s.charAt(start);
        sumMap[c] = 1;

        int from = start;
        int to = size;

        int sum = 0;
        if(sumMap[c] == map[c]){
            sum++;
        }

        for(int i = start+1; i < size; i++){

            c = s.charAt(i);
            if(map[c] == 0){
                continue;
            }

            sumMap[c]++;
            if(sumMap[c] < map[c]){
                continue;
            }

            if(sumMap[c] == map[c]){
                sum += sumMap[c];
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

            sumMap[c]--;
            while (true){
                start = nextIndex(s, start+1, sumMap);
                if(start == size){
                    break;
                }

                startC = s.charAt(start);
                if(sumMap[startC] <= map[startC]){
                    break;
                }

                sumMap[startC]--;
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
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("babb", "baba"));
    }
}
