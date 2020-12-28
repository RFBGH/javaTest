package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class fallingSquares {

    private static class Node{
        int from;
        int to;
        int height;

        public Node(int from, int to, int height) {
            this.from = from;
            this.to = to;
            this.height = height;
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {

        List<Integer> result = new ArrayList<>();

        List<Node> nodes = new ArrayList<>();
        int max = 0;
        for(int[] pos : positions){

            if(nodes.isEmpty()){
                nodes.add(new Node(pos[0], pos[0]+pos[1]-1, pos[1]));
                if(max < pos[1]){
                    max = pos[1];
                }
                result.add(max);
                continue;
            }

            int i;
            for(i = 0; i < nodes.size(); i++){
                Node node = nodes.get(i);
                if(pos[0] <= node.to){
                    break;
                }
            }

            int to = pos[0] + pos[1] - 1;
            if(i == nodes.size()){
                nodes.add(new Node(pos[0], to, pos[1]));
                if(max < pos[1]){
                    max = pos[1];
                }
                result.add(max);
                continue;
            }

            int temp = nodes.get(i).height;
            int j;
            for(j = i+1; j < nodes.size(); j++){
                if(to < nodes.get(j).from){
                    break;
                }

                if(temp < nodes.get(j).height){
                    temp = nodes.get(j).height;
                }
            }

            j--;
            if(i == j){

                if (nodes.get(i).from > to) {
                    if(temp + pos[1] > max){
                        max = temp+pos[1];
                    }
                    result.add(max);
                }else{
                    if(temp + pos[1] > max){
                        max = temp+pos[1];
                    }
                    result.add(max);
                }

            }else{
                if(temp + pos[1] > max){
                    max = temp+pos[1];
                }
                result.add(max);
            }

            if(i == j){

                if(pos[0] > nodes.get(i).from){

                    nodes.add(i+1, new Node(pos[0], to, temp+pos[1]));
                    if(to < nodes.get(i).to){
                        nodes.add(i+2, new Node(to+1, nodes.get(i).to, nodes.get(i).height));
                    }
                    nodes.get(i).to = pos[0]-1;
                }else{

                    if(to < nodes.get(i).to){
                        nodes.add(i+1, new Node(to+1, nodes.get(i).to, nodes.get(i).height));
                    }
                    nodes.get(i).from = pos[0];
                    nodes.get(i).to = to;
                    nodes.get(i).height = temp+pos[1];
                }
            }else{

                for(int k = i+1; k < j; k++){
                    nodes.remove(i+1);
                }

                j = i+1;
                if(pos[0] > nodes.get(i).from){


                    if(to < nodes.get(j).to){
                        nodes.add(j+1, new Node(to+1, nodes.get(j).to, nodes.get(j).height));
                    }

                    nodes.get(j).from = pos[0];
                    nodes.get(j).to = to;
                    nodes.get(j).height = pos[1] + temp;

                    nodes.get(i).to = pos[0]-1;
                }else{

                    if(to < nodes.get(j).to){
                        nodes.get(j).from = to+1;
                    }else{
                        nodes.remove(j);
                    }
                    nodes.get(i).from = pos[0];
                    nodes.get(i).to = to;
                    nodes.get(i).height = pos[1] + temp;
                }
            }
        }

        return result;
    }

    public void test(){

        fallingSquares(new int[][]{{6,1},{9,2},{2,4}});
    }

}
