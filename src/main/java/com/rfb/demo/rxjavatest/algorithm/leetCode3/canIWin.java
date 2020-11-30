package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.Map;

public class canIWin {

    Map<Integer, Boolean> map = new HashMap<>();

    private boolean dfs(boolean[] choose, int sum, int target, boolean isMe){

        if(sum >= target) {
            return !isMe;
        }

        int key = 0;
        if(choose[1]){
            key = 1;
        }

        for(int i = 2; i < choose.length; i++){
            key <<= 1;
            if(choose[i]){
                key += 1;
            }
        }

        if(isMe){

            if(map.containsKey(key)){
                return map.get(key);
            }

            for(int i = 1; i < choose.length; i++){
                if(choose[i]){
                    continue;
                }

                choose[i] = true;
                boolean result = dfs(choose, sum+i, target, false);
                choose[i] = false;

                if(result){
                    map.put(key, true);
                    return true;
                }
            }

            map.put(key, false);
            return false;
        }else{

            key <<= 1;

            if(map.containsKey(key)){
                return map.get(key);
            }

            for(int i = 1; i < choose.length; i++){
                if(choose[i]){
                    continue;
                }

                choose[i] = true;
                boolean result = dfs(choose, sum+i, target, true);
                choose[i] = false;

                if(!result){
                    map.put(key, false);
                    return false;
                }
            }

            map.put(key, true);
            return true;
        }
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        if(maxChoosableInteger >= desiredTotal){
            return true;
        }

        if ((1 + maxChoosableInteger)*maxChoosableInteger/2 < desiredTotal) {
            return false;
        }

        boolean[] choose = new boolean[maxChoosableInteger+1];
        return dfs(choose, 0, desiredTotal, true);
    }

    public void test(){
        System.out.println(canIWin(5, 50));
    }

}
