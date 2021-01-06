package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class allPathsSourceTarget {

    List<List<Integer>> result = new ArrayList<>();
    private void dfs(int[][] graph, int cur, List<Integer> path){
        if(cur == graph.length-1){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int next : graph[cur]){
            path.add(next);
            dfs(graph, next, path);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, 0, path);
        return result;
    }
}
