package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class scheduleCourse {


    private static class Node{

        int duration;
        int end;

        public Node(int duration, int end){
            this.duration = duration;
            this.end = end;
        }
    }

    public int scheduleCourse(int[][] courses) {


        List<Node> nodes = new ArrayList<>();
        for(int i = 0; i < courses.length; i++){
            nodes.add(new Node(courses[i][0], courses[i][1]));
        }

        Collections.sort(nodes, new Comparator<Node>(){

            @Override
            public int compare(Node n1, Node n2){

                if(n1.end < n2.end){
                    return -1;
                }

                if(n1.end > n2.end){
                    return 1;
                }

                return 0;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b)->b-a);
        int curTime = 0;
        for(Node node : nodes){

            if(curTime + node.duration <= node.end){
                queue.offer(node.duration);
                curTime += node.duration;
            }else if(!queue.isEmpty() && queue.peek() > node.duration){
                curTime +=  node.duration - queue.poll();
                queue.offer(node.duration);
            }
        }

        return queue.size();
    }

    public void test(){


        System.out.println(scheduleCourse(new int[][]{{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}}));
    }
}
