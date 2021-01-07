package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class sumOfDistancesInTree {


    private static class Node{
        int allDistance;
        int allChildCount;
        int parent;
    }

    private ArrayList<Integer>[] G;
    private Node[] nodes;
    private int[] result;

    private Node dfs0(int parent, int cur){

        Node node = nodes[cur];
        node.allChildCount = 1;
        node.parent = parent;

        for(int next : G[cur]){
            if(next == parent){
                continue;
            }

            Node son = dfs0(cur, next);
            node.allChildCount += son.allChildCount;
            node.allDistance += son.allDistance + son.allChildCount;
        }

        return node;
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {

        G = new ArrayList[N];
        nodes = new Node[N];
        for(int i = 0; i < N; i++){
            nodes[i] = new Node();
        }

        result = new int[N];

        for(int i = 0; i < N; i++){
            G[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            G[edge[0]].add(edge[1]);
            G[edge[1]].add(edge[0]);
        }

        dfs0(-1, 0);
        result[0] = nodes[0].allDistance;

        List<Integer> parents = new ArrayList<>();
        parents.add(0);

        while (true){

            List<Integer> sons = new ArrayList<>();
            for(int parent : parents){

                for(int son : G[parent]){

                    if(son == nodes[parent].parent){
                        continue;
                    }

                    int restDistance = result[parent] - nodes[son].allDistance - nodes[son].allChildCount;
                    result[son] = nodes[son].allDistance + restDistance + N - nodes[son].allChildCount;
                    sons.add(son);
                }
            }

            if(sons.isEmpty()){
                break;
            }

            parents = sons;
        }

        return result;
    }

    public void test(){
        int[] ans = sumOfDistancesInTree(6, new int[][]{{0,1},{0,2},{2,3},{2,4},{2,5}});
        for(int i = 0; i < ans.length; i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }
}
