package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class canFinish {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<Integer>[] G = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            G[i] = new ArrayList<>();
        }

        int[] count = new int[numCourses];
        for(int i = 0, size = prerequisites.length; i < size; i++){
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            G[from].add(to);
            count[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(count[i] == 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int find = queue.poll();
            for(Integer next : G[find]){
                count[next]--;
                if(count[next] == 0){
                    queue.offer(next);
                }
            }
        }

        for(int i = 0; i < numCourses; i++){
            if(count[i] != 0){
                return false;
            }
        }
        return true;
    }

}
