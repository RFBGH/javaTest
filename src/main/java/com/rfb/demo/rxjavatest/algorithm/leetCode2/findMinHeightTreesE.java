package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class findMinHeightTreesE {

    private static class Node{
        int i;
        int dep;
        int parent;

        public Node(int i, int dep, int parent) {
            this.i = i;
            this.dep = dep;
            this.parent = parent;
        }
    }
    private int treeDep(List<Integer>[] G, int root, int minDep){

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0, -1));
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int dep = cur.dep+1;
            if(dep > max){
                max = dep;
            }
            for(Integer i : G[cur.i]){

                if(i == cur.parent){
                    continue;
                }

                if(dep > minDep){
                    return Integer.MAX_VALUE;
                }

                queue.offer(new Node(i, dep, cur.i));
            }
        }

        return max;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> result = new ArrayList<>();
        if(n == 1){
            result.add(0);
            return result;
        }

        List<Integer>[] G = new ArrayList[n];
        for(int i = 0; i < n; i++){
            G[i] = new ArrayList<>();
        }

        for(int i = 0, size = edges.length; i < size; i++){
            int v = edges[i][0];
            int u = edges[i][1];
            G[v].add(u);
            G[u].add(v);
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int deep = treeDep(G, i, min);
            if(deep < min){
                result.clear();
                result.add(i);
                min = deep;
            }else if(deep == min){
                result.add(i);
            }
        }

        return result;
    }

    public void test(){
        findMinHeightTrees(6, new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}});
    }
}
