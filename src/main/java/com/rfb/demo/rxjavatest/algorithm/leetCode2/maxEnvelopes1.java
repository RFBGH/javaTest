package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class maxEnvelopes1 {

    private int[] deeps;

    private int dfs(int[][] envelopes, int cur){

        if(deeps[cur] != -1){
            return deeps[cur];
        }

        int n = envelopes.length;
        int max = -1;
        for(int i = 0; i < n; i++){
            if(i == cur){
                continue;
            }

            if(envelopes[cur][0] < envelopes[i][0] && envelopes[cur][1] < envelopes[i][1]){
                int temp = dfs(envelopes, i);
                if(temp > max){
                    max = temp;
                }
            }
        }

        max++;
        deeps[cur] = max;
        return max;
    }

    public int maxEnvelopes(int[][] envelopes) {

        if(envelopes.length == 0){
            return 0;
        }

        if(envelopes.length == 1){
            return 1;
        }

        int n = envelopes.length;
        deeps = new int[n];
        for(int i = 0; i < n; i++){
            deeps[i] = -1;
        }

        int max = 0;
        for(int i = 0; i < n; i++){
            int temp = dfs(envelopes, i);
            if(temp > max){
                max = temp;
            }
        }
        return max+1;
    }

    public void test(){
        System.out.println(maxEnvelopes(new int[][]{{4,5},{4,6},{6,7},{2,3},{1,1}}));
    }

}
