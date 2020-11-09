package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.Arrays;
import java.util.Comparator;

public class countRangeSum {

    private static class Node{
        long sum;
        int index;

        public Node(long sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums.length == 0){
            return 0;
        }

        Node[] nodes = new Node[nums.length];
        nodes[0] = new Node(nums[0], 0);
        for(int i = 1, size = nums.length; i < size; i++){
            nodes[i] = new Node(nodes[i-1].sum+nums[i], i);
        }

        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.sum < o2.sum){
                    return -1;
                }

                if(o1.sum > o2.sum){
                    return 1;
                }

                return 0;
            }
        });

        int result = 0;
        for(int i = 0, size = nums.length; i < size; i++){
            for(int j = i; j < size; j++){

                int index = nodes[i].index;
                long sum = nodes[j].sum - nodes[i].sum + nums[index];
                if(sum < lower){
                    continue;
                }

                if(sum >= lower && sum <= upper){
                    result++;
                }

                if(sum > upper){
                    break;
                }
            }
        }
        return result;
    }

    public void test(){
        System.out.println(countRangeSum(new int[]{-2,5,1}, -2, 2));

    }
}
