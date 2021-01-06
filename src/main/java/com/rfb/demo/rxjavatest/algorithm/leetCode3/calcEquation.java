package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class calcEquation {

    private Map<String, Integer> variableMap = new HashMap<>();

    private static class Node{
        int index;
        double value;

        public Node(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

    private double query(double[][] G, int from, int to){

        if(from == to){
            return 1;
        }

        boolean[] gone = new boolean[G.length];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(from, 1));
        gone[from] = true;

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i = 0; i < G.length; i++){

                if(G[cur.index][i] == 0){
                    continue;
                }

                if(gone[i]){
                    continue;
                }
                gone[i] = true;

                if(i == to){
                    return cur.value*G[cur.index][i];
                }

                queue.offer(new Node(i, cur.value*G[cur.index][i]));
            }
        }

        return -1;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        for(List<String> equation : equations){

            String a = equation.get(0);
            if(!variableMap.containsKey(a)){
                variableMap.put(a, variableMap.size());
            }

            String b = equation.get(1);
            if(!variableMap.containsKey(b)){
                variableMap.put(b, variableMap.size());
            }
        }

        double[][] G = new double[variableMap.size()][variableMap.size()];
        for(int i = 0; i < equations.size(); i++){
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);

            G[variableMap.get(a)][variableMap.get(b)] = values[i];
            G[variableMap.get(b)][variableMap.get(a)] = 1/values[i];
        }

        double[] ans = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++){
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);

            if(!variableMap.containsKey(a) || !variableMap.containsKey(b)){
                ans[i] = -1.0;
                continue;
            }

            ans[i] = query(G, variableMap.get(a), variableMap.get(b));
        }

        return ans;
    }

    public void test(){

        List<List<String>> equations = new ArrayList<>();
        List<String> equation0 = new ArrayList<>();
        equation0.add("a");
        equation0.add("b");
        equations.add(equation0);

        List<String> equation1 = new ArrayList<>();
        equation1.add("b");
        equation1.add("c");
        equations.add(equation1);

        double[] values = new double[]{2.0, 3.0};
        List<List<String>>queries = new ArrayList<>();
        List<String> query0 = new ArrayList<>();
        query0.add("a");
        query0.add("c");
        queries.add(query0);

        List<String> query1 = new ArrayList<>();
        query1.add("b");
        query1.add("a");
        queries.add(query1);

        List<String> query2 = new ArrayList<>();
        query2.add("a");
        query2.add("e");
        queries.add(query2);

        List<String> query3 = new ArrayList<>();
        query3.add("a");
        query3.add("a");
        queries.add(query3);

        calcEquation(equations, values, queries);
    }
}
