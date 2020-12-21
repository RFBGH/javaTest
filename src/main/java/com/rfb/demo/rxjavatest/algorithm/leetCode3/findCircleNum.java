package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.LinkedList;
import java.util.Queue;

public class findCircleNum {

    public int findCircleNum(int[][] M) {

        int n = M.length;
        int[] belongs = new int[n];
        for(int i = 0; i < n; i++){
            belongs[i] = -1;
        }

        int group = 0;
        for(int i = 0; i < n; i++){

            if(belongs[i] != -1){
                continue;
            }

            belongs[i] = group;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            while (!queue.isEmpty()){
                int cur = queue.poll();
                for(int j = 0; j < n; j++){
                    if(belongs[j] != -1){
                        continue;
                    }

                    if(M[cur][j] == 0){
                        continue;
                    }

                    belongs[j] = group;
                    queue.offer(j);
                }
            }

            group++;
        }

        return group;
    }

    public void test(){
        System.out.println(findCircleNum(new int[][]{{1,1,0},
                {1,1,1},
                {0,1,1}}));
    }
}
