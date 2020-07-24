package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/24 0024.
 */
public class MaxFlowMinCost {

    private static class Node{
        public int to;
        public int cap;
        public int cost;
        public int rev;

        private Node(int to, int cap, int cost, int rev) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.rev = rev;
        }
    }

    private List<List<Node>> G;
    private int n;

    public void init(int n){
        this.n = n;

        G = new ArrayList<>();
        for(int i = 0; i < n; i++){
            List<Node> nodes = new ArrayList<>();
            G.add(nodes);
        }
    }

    public void addEdge(int f, int t, int cost, int cap){
        Node node = new Node(t, cap, cost, G.get(t).size());
        G.get(f).add(node);

        Node rNode = new Node(f, 0, -cost, G.get(f).size()-1);
        G.get(t).add(rNode);
    }

    public void calc(int s, int t){

        int maxFlow = 0;
        int allCost = 0;

        int[] dist = new int[n];
        int[] pre = new int[n];
        int[] to2Index = new int[n];

        while (true){

            for(int i = 0; i < n; i++){
                dist[i] = Integer.MAX_VALUE;
                pre[i] = -1;
                to2Index[i] = -1;
            }

            dist[s] = 0;

            boolean update = true;
            while (update){

                update = false;

                for(int i = 0; i < n; i++){

                    List<Node> edge = G.get(i);
                    for(int j = 0, size = edge.size(); j < size; j++){

                        Node node = edge.get(j);

                        if(node.cap <= 0){
                            continue;
                        }

                        if(dist[i] == Integer.MAX_VALUE){
                            continue;
                        }

                        int to = node.to;
                        int cost = dist[i] + node.cost;
                        if(cost < dist[to]){

                            to2Index[to] = j;
                            dist[to] = cost;
                            pre[to] = i;
                            update = true;
                        }
                    }
                }
            }

            if(dist[t] == Integer.MAX_VALUE){
                break;
            }

            {
                System.out.println("cost "+dist[t]);
                int back = t;
                while (back != -1){
                    System.out.print(back+" ");
                    back = pre[back];
                }
                System.out.println();
            }


            int f = Integer.MAX_VALUE;
            int backNode = t;
            while (true){

                int from = pre[backNode];
                if(from == -1){
                    break;
                }

                f = Math.min(G.get(from).get(to2Index[backNode]).cap, f);
                backNode = from;
            }

            {
                System.out.println("flow "+f);
            }
            maxFlow += f;
            allCost += f*dist[t];

            backNode = t;
            while (true){

                int from = pre[backNode];
                if(from == -1){
                    break;
                }

                Node node = G.get(from).get(to2Index[backNode]);
                node.cap -= f;
                G.get(node.to).get(node.rev).cap += f;
                backNode = from;
            }
        }

        System.out.println("maxFlow "+maxFlow+" "+"allCost "+allCost);
    }

    public static void test(){

//        MaxFlowMinCost maxFlowMinCost = new MaxFlowMinCost();
//        maxFlowMinCost.init(6);
//        maxFlowMinCost.addEdge(0, 1, 10, 3);
//        maxFlowMinCost.addEdge(0, 2, 8, 2);
//        maxFlowMinCost.addEdge(1, 3, 7, 2);
//        maxFlowMinCost.addEdge(1, 4, 2, 5);
//        maxFlowMinCost.addEdge(1, 4, 2, 5);
//        maxFlowMinCost.addEdge(2, 4, 10, 4);
//        maxFlowMinCost.addEdge(4, 3, 4, 1);
//        maxFlowMinCost.addEdge(4, 3, 4, 1);
//        maxFlowMinCost.addEdge(4, 5, 7, 4);
//        maxFlowMinCost.addEdge(3, 5, 6, 1);
//
//        maxFlowMinCost.calc(0, 5);

        MaxFlowMinCost maxFlowMinCost = new MaxFlowMinCost();
        maxFlowMinCost.init(5);
        maxFlowMinCost.addEdge(0, 1, 4, 10);
        maxFlowMinCost.addEdge(0, 2, 1, 8);
        maxFlowMinCost.addEdge(2, 1, 2, 5);
        maxFlowMinCost.addEdge(1, 4, 1, 7);
        maxFlowMinCost.addEdge(1, 3, 6, 2);
        maxFlowMinCost.addEdge(2, 3, 3, 10);
        maxFlowMinCost.addEdge(3, 4, 2, 4);

        maxFlowMinCost.calc(0, 4);
    }

}
