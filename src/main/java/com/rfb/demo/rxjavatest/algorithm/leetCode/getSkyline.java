package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class getSkyline {

    private static class Node{
        int from;
        int to;
        int h;

        public Node(int from, int to, int h) {
            this.from = from;
            this.to = to;
            this.h = h;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        if(buildings.length == 0){
            return new ArrayList<>();
        }

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(buildings[0][0], buildings[0][1], buildings[0][2]));

        for(int i = 1, size = buildings.length; i < size; i++){

            int from = buildings[i][0];
            int to = buildings[i][1];
            int h = buildings[i][2];

            int k;
            int nodeSize = nodes.size();
            for(k = 0; k < nodeSize; k++){
                Node node = nodes.get(k);
                if(node.from > from){
                    break;
                }
            }

            k--;
            while (true){

                if(from >= to){
                    break;
                }

                if(k >= nodes.size()){
                    nodes.add(new Node(from, to, h));
                    break;
                }

                Node findNode = nodes.get(k);
                if(from < findNode.to){
                    if(h <= findNode.h){
                        from = findNode.to;
                        k++;
                    }else{
                        int tempTo = findNode.to;
                        findNode.to = from;
                        if(to >= tempTo){
                            nodes.add(k+1, new Node(from, tempTo, h));
                            from = tempTo;
                            k += 2;
                        }else{
                            nodes.add(k+1, new Node(from, to, h));
                            nodes.add(k+2, new Node(to, tempTo, findNode.h));
                            break;
                        }
                    }
                }else{
                    nodes.add(new Node(from, to, h));
                    break;
                }
            }
        }

        Stack<Node> stack = new Stack<>();
        for(int i = 0, size = nodes.size(); i < size; i++){

            Node cur = nodes.get(i);
            if(cur.from == cur.to){
                continue;
            }

            if(stack.isEmpty()){
                stack.push(cur);
            }else{
                Node last = stack.peek();
                if(last.to == cur.from && last.h == cur.h){
                    last.to = cur.to;
                    continue;
                }else{
                    stack.push(cur);
                }
            }
        }

        List<List<Integer>> lists = new ArrayList<>();
        for(int i = 0, size = stack.size(); i < size; i++){
            Node node = stack.get(i);
            Node next = null;
            if(i < size-1){
                next = stack.get(i+1);
            }
            List<Integer> list = new ArrayList<>();
            list.add(node.from);
            list.add(node.h);
            lists.add(list);

            if(next == null || node.to != next.from){
                list = new ArrayList<>();
                list.add(node.to);
                list.add(0);
                lists.add(list);
            }
        }

        return lists;
    }

    public void test(){

        getSkyline(new int[][]{{0,3,3},{1,5,3},{2,4,3},{3,7,3}});

//        getSkyline(new int[][]{{1,2,1},{1,2,2},{1,2,3}});

    }
}
