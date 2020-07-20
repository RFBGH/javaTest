package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/20 0020.
 */
public class MaxFlow {

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
    private List<Boolean> used = new ArrayList<>();

    public void init(int n){
        for(int i = 0; i < n; i++){
            List<Node> nodes = new ArrayList<>();
            G.add(nodes);
            used.add(false);
        }
    }

    public void add(int from, int to, int cap){

        Node node = new Node(to, cap, G.get(to).size());
        G.get(from).add(node);

        node = new Node(from, 0, G.get(from).size()-1);
        G.get(to).add(node);
    }

    public int dfs(int from, int end, int flow){

        used.set(from, true);

        if(from == end){
            return flow;
        }

        List<Node> edges = G.get(from);
        for(Node node:edges){

            int to = node.to;
            int cap = node.cap;

            if(used.get(to)){
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

    private void resetUsed(){
        int n = used.size();
        used.clear();
        while(n > 0){
            used.add(false);
            n--;
        }
    }

    public int calcMaxFlow(int s, int e){

        int maxFlow = 0;
        while(true){

            int f = dfs(s, e, Integer.MAX_VALUE);
            System.out.println();
            if(f == 0){
                break;
            }

            System.out.println("flow:"+f);

            maxFlow += f;
            resetUsed();
        }
        return maxFlow;
    }

    public static void test(){

        MaxFlow maxFlow = new MaxFlow();
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
