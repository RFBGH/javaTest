package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class findItinerary {

    private static class Node{
        String pos;
        boolean gone;

        public Node(String pos, boolean gone) {
            this.pos = pos;
            this.gone = gone;
        }
    }

    List<String> result = new ArrayList<>();

    private int doCompare(String s1, String s2){

        for(int k = 0; k < 3; k++){
            if(s1.charAt(k) < s2.charAt(k)){
                return -1;
            }else if(s1.charAt(k) > s2.charAt(k)){
                return 1;
            }
        }
        return 0;
    }

    private boolean dfs(Map<String, List<Node>> G, String cur, List<String> travel, int n){
        if(n == travel.size()){
            result.addAll(travel);
            return true;
        }

        List<Node> nodes = G.get(cur);
        if(nodes == null){
            return false;
        }
        for(Node node: nodes){
            if(node.gone){
                continue;
            }

            node.gone = true;
            travel.add(node.pos);
            if(dfs(G, node.pos, travel, n)){
                return true;
            }
            travel.remove(travel.size()-1);
            node.gone = false;
        }
        return false;
    }

    public List<String> findItinerary(List<List<String>> tickets) {

        if(tickets.size() == 1){
            return tickets.get(0);
        }

        Map<String, List<Node>> G = new HashMap<>();

        for(List<String> list : tickets){
            String from = list.get(0);
            String to = list.get(1);
            List<Node> edges = G.get(from);
            if(edges == null){
                edges = new ArrayList<>();
                G.put(from, edges);
            }
            edges.add(new Node(to, false));
        }

        Set<String> keys = G.keySet();
        for(String key : keys){
            List<Node> value = G.get(key);
            if(value == null){
                continue;
            }
            Collections.sort(value, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return doCompare(o1.pos, o2.pos);
                }
            });
        }

        List<String> travels = new ArrayList<>();
        travels.add("JFK");
        dfs(G, "JFK", travels, tickets.size()+1);
        return result;
    }

    public void test(){
        List<List<String>> tickets = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        tickets.add(l1);

        List<String> l2 = new ArrayList<>();
        tickets.add(l2);

        List<String> l3 = new ArrayList<>();
        tickets.add(l3);

        List<String> l4 = new ArrayList<>();
        tickets.add(l4);


    }
}
