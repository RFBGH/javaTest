package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class numBusesToDestination {

    private static class Node{
        int pos;
        int step;

        public Node(int pos, int step) {
            this.pos = pos;
            this.step = step;
        }
    }

    public int numBusesToDestination(int[][] routes, int S, int T) {

        if(S == T){
            return 0;
        }

        int[][] G = new int[routes.length][routes.length];

        Map<Integer, Set<Integer>> belongMap = new HashMap<>();
        for(int i = 0; i < routes.length; i++){

            for(int pos : routes[i]){

                Set<Integer> belongs = belongMap.get(pos);
                if(belongs == null){
                    belongs = new HashSet<>();
                    belongMap.put(pos, belongs);
                    belongs.add(i);
                }else{
                    for(Integer belong : belongs){
                        G[belong][i] = 1;
                        G[i][belong] = 1;
                    }
                    belongs.add(i);
                }
            }
        }


        Set<Integer> starts = belongMap.get(S);
        Set<Integer> ends = belongMap.get(T);
        if(starts == null || ends == null){
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for(int s : starts){

            if(ends.contains(s)){
                return 1;
            }

            boolean[] gone = new boolean[routes.length];
            gone[s] = true;

            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(s, 1));
            while (!queue.isEmpty()){
                Node cur = queue.poll();
                for(int i = 0; i < routes.length; i++){

                    if(G[cur.pos][i] == 0){
                        continue;
                    }

                    if(gone[i]){
                        continue;
                    }
                    gone[i] = true;
                    queue.offer(new Node(i, cur.step+1));

                    if(ends.contains(i)){
                        if(min > cur.step+1){
                            min = cur.step+1;
                        }
                        queue.clear();
                        break;
                    }
                }
            }
        }

        if(min == Integer.MAX_VALUE){
            min = -1;
        }

        return min;
    }
}
