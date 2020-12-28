package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class minNumberOperations {

    private int dfs(int[] target, int l, int r){

        int min = Integer.MAX_VALUE;
        for(int i = l; i <= r; i++){

            if(target[i] == 0){
                continue;
            }

            if(target[i] < min){
                min = target[i];
            }
        }

        if(min == Integer.MAX_VALUE){
            return 0;
        }

        int count = min;
        int i = l;
        while (i <= r){

            target[i] -= min;
            if(target[i] == 0){
                i++;
                continue;
            }

            int j;
            for(j = i+1; j <= r; j++){
                target[j] -= min;
                if(target[j] == 0){
                    break;
                }
            }

            count += dfs(target, i, j-1);
            i = j+1;
        }

        return count;
    }

    public int minNumberOperations(int[] target) {
        return dfs(target, 0, target.length-1);
    }

    public void test(){
        System.out.println(minNumberOperations(new int[]{3,1,5,4,2}));
    }
}
