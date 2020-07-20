package com.rfb.demo.rxjavatest.algorithm;

import java.util.*;

/**
 * Created by Administrator on 2020/7/20 0020.
 */
public class MaxFlow2 {

    private static class Node{
        int to;
        int cap;
        int rev;

        private Node(int to, int cap, int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
    }

    private List<List<Node>> G = new ArrayList<>();
    private List<Integer> level = new ArrayList<>();

    public void init(int n){
        G.clear();
        level.clear();

        for(int i = 0; i < n; i++){
            List<Node> nodes = new ArrayList<>();
            G.add(nodes);
            level.add(0);
        }
    }

    public void add(int from, int to, int cap){

        Node node = new Node(to, cap, G.get(to).size());
        G.get(from).add(node);

        node = new Node(from, 0, G.get(from).size()-1);
        G.get(to).add(node);
    }

    public void initLevel(int s, int e){

        int n = level.size();
        level.clear();
        while (n > 0){
            level.add(0);
            n--;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        level.set(s, 1);

        while (queue.size() > 0){

            int from = queue.poll();
            List<Node> edges = G.get(from);
            int newLevel = level.get(from) + 1;

            for(Node node:edges){
                int to = node.to;
                if(level.get(to) != 0){
                    continue;
                }

                if(node.cap <= 0){
                    continue;
                }

                level.set(to, newLevel);

                if(to == e){
                    return;
                }

                queue.offer(to);
            }
        }
    }

    public int dfs(int from, int end, int flow){

        if(from == end){
            return flow;
        }

        List<Node> edges = G.get(from);
        for(Node node:edges){

            int to = node.to;
            int cap = node.cap;

            if(level.get(from) >= level.get(to)){
                continue;
            }

            if(cap == 0){
                continue;
            }

            int f = dfs(to, end, Math.min(flow, cap));
            if(f == 0){
                continue;
            }

            System.out.print(to+" ");

            node.cap -= f;
            G.get(to).get(node.rev).cap += f;
            return f;
        }

        return 0;
    }

    public int calcMaxFlow(int s, int e){

        int maxFlow = 0;

        while(true){
            initLevel(s, e);

            if(level.get(e) == 0){
                break;
            }

            while(true){
                int f = dfs(s, e, Integer.MAX_VALUE);
                System.out.println();
                System.out.println("flow: "+f);
                if(f == 0){
                    break;
                }

                maxFlow += f;
            }
        }

        return maxFlow;
    }

    public static void test(){

        MaxFlow2 maxFlow = new MaxFlow2();
        maxFlow.init(6);
        maxFlow.add(0, 1, 10);
        maxFlow.add(0, 2, 10);
        maxFlow.add(1, 2, 2);
        maxFlow.add(1, 3, 4);
        maxFlow.add(1, 4, 8);
        maxFlow.add(2, 4, 9);
        maxFlow.add(4, 3, 6);
        maxFlow.add(3, 5, 10);
        maxFlow.add(4, 5, 10);
        int max = maxFlow.calcMaxFlow(0, 5);
        System.out.println(max);
    }

}
