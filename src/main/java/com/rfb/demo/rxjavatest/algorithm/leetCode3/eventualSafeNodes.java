package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class eventualSafeNodes {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        ArrayList<Integer>[]rG = new ArrayList[graph.length];
        for(int i = 0; i < graph.length; i++){
            rG[i] = new ArrayList<>();
        }

        int[] dep = new int[graph.length];
        for(int from = 0; from < graph.length; from++){
            dep[from] = graph[from].length;
            for(int to : graph[from]){
                rG[to].add(from);
            }
        }

        List<Integer> queue = new ArrayList<>();
        int front = 0;
        for(int i = 0; i < graph.length; i++){
            if(dep[i] == 0){
                queue.add(i);
            }
        }

        while (front < queue.size()){

            int cur = queue.get(front);
            for(int from : rG[cur]){
                dep[from]--;
                if(dep[from] == 0){
                    queue.add(from);
                }
            }

            front++;
        }

        Collections.sort(queue);
        return queue;
    }

    public void test(){
        eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}});
    }
}
