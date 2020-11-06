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
                        if(findNode.from == from){

                            if(to >= tempTo){
                                findNode.h = h;
                                from = tempTo;
                                k++;
                            }else{
                                int tempH = findNode.h;
                                findNode.h = h;
                                findNode.to = to;
                                nodes.add(k+1, new Node(to, tempTo, tempH));
                                break;
                            }

                        }else{
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
                }else{
                    stack.push(cur);
                }
            }
        }

        List<List<Integer>> lists = new ArrayList<>();
        for(int i = 0, size = stack.size(); i < size; i++){
            Node node = stack.get(i);

            List<Integer> list = new ArrayList<>();
            list.add(node.from);
            list.add(node.h);
            lists.add(list);

            Node next = null;
            if(i < size-1){
                next = stack.get(i+1);
            }
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

        getSkyline(new int[][]{{6765,184288,53874},{13769,607194,451649},{43325,568099,982005},{47356,933141,123943},{59810,561434,119381},{75382,594625,738524},{111895,617442,587304},{143767,869128,471633},{195676,285251,107127},{218793,772827,229219},{316837,802148,899966},{329669,790525,416754},{364886,882642,535852},{368825,651379,6209},{382318,992082,300642},{397203,478094,436894},{436174,442141,612149},{502967,704582,918199},{503084,561197,625737},{533311,958802,705998},{565945,674881,149834},{615397,704261,746064},{624917,909316,831007},{788731,924868,633726},{791965,912123,438310}});

//        getSkyline(new int[][]{{1,2,1},{1,2,2},{1,2,3}});

    }
}
