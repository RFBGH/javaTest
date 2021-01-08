package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class maxProfitAssignment {

    private static class Work{
        int difficulty;
        int profit;

        public Work(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {

        List<Work> works = new ArrayList<>();
        for(int i = 0; i < difficulty.length; i++){
            works.add(new Work(difficulty[i], profit[i]));
        }

        Collections.sort(works, new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                if(o1.difficulty < o2.difficulty){
                    return -1;
                }

                if(o1.difficulty > o2.difficulty){
                    return 1;
                }
                return 0;
            }
        });

        int K = 1;
        for(K = 1; (1 << K) < works.size(); K++);

        int[][] dp = new int[K][works.size()];
        for(int i = 0; i < works.size(); i++){
            dp[0][i] = works.get(i).profit;
        }

        for(int k = 1; k < K; k++){
            for(int i = 0; i < works.size(); i++){
                if(i+(1<<(k-1)) < works.size()){
                    dp[k][i] = Math.max(dp[k-1][i], dp[k-1][i+(1<<(k-1))]);
                }
            }
        }

        int sum = 0;
        for(int worker : workers){

            int left = 0;
            int right = works.size()-1;
            while (true){

                if(left == right){
                    break;
                }

                int mid = (left + right) / 2;
                if(works.get(mid).difficulty <= worker){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }

            if(works.get(left).difficulty > worker){
                left--;
            }

            if(left == -1){
                continue;
            }

            int k;
            for(k = 1; (1 << k) <= left; k++);
            k--;

            sum += Math.max(dp[k][0], dp[k][(left-(1<<k)+1)]);
        }
        return sum;
    }

    public void test(){
        System.out.println(maxProfitAssignment(new int[]{85,47,57}, new int[]{24,66,99}, new int[]{40,25,25}));
    }
}
