package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class findOrder {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer>[] G = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            G[i] = new ArrayList<>();
        }

        int[] deep = new int[numCourses];

        for(int i = 0, n = prerequisites.length; i < n; i++){
            int v = prerequisites[i][0];
            int u = prerequisites[i][1];
            G[u].add(v);
            deep[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(deep[i] == 0){
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int resultCount = 0;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            result[resultCount++] = cur;
            for(int i : G[cur]){
                deep[i]--;

                if(deep[i] == 0){
                    queue.offer(i);
                }
            }
        }

        if(resultCount != numCourses){
            return new int[]{};
        }
        return result;
    }

    public void test(){
        findOrder(2, new int[][]{{0,1},{1,0}});
    }
}
