package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class findMinHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if(n == 1){
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }

        List<Integer>[] G = new ArrayList[n];
        for(int i = 0; i < n; i++){
            G[i] = new ArrayList<>();
        }

        int[] degree = new int[n];
        for(int i = 0, size = edges.length; i < size; i++){
            int v = edges[i][0];
            int u = edges[i][1];
            G[v].add(u);
            G[u].add(v);
            degree[u]++;
            degree[v]++;
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(degree[i] == 1){
                list.add(i);
            }
        }

        while (true){

            List<Integer> nextList = new ArrayList<>();
            for(Integer cur : list){
                List<Integer> edge = G[cur];
                for(Integer next : edge){
                    degree[next]--;
                    if(degree[next] == 1){
                        nextList.add(next);
                    }
                }
            }

            if(nextList.isEmpty()){
                break;
            }

            list = nextList;
        }

        return list;
    }

    public void test(){
        findMinHeightTrees(6, new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}});
    }
}
