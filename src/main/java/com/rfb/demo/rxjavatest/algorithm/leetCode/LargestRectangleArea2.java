package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.Stack;

public class LargestRectangleArea2 {

    public int largestRectangleArea(int[] heights) {

        if(heights.length == 0){
            return 0;
        }

        if(heights.length == 1){
            return heights[0];
        }

        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1, size = heights.length; i < size; i++){

            int top = stack.peek();
            if(heights[top] < heights[i]){
                stack.push(i);
                continue;
            }

            int cur = stack.pop();
            while (!stack.isEmpty()){
                int next = stack.peek();

                int rect = (top - next) * heights[cur];
                if(rect > max){
                    max = rect;
                }

                cur = next;
                if (heights[cur] < heights[i]) {
                    break;
                }
                stack.pop();
            }

            if(stack.isEmpty()){
                int rect = (top + 1) * heights[cur];
                if(max < rect){
                    max = rect;
                }
            }

            stack.push(i);
        }

        int top = stack.pop();
        int cur = top;
        while (!stack.isEmpty()){
            int next = stack.pop();
            int rect = (top - next) * heights[cur];
            cur = next;
            if(max < rect){
                max = rect;
            }
        }

        int rect = (top + 1) * heights[cur];
        if(max < rect){
            max = rect;
        }

        return max;
    }

    public void test(){
//        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
//
//        System.out.println(largestRectangleArea(new int[]{2,4}));
////
//        System.out.println(largestRectangleArea(new int[]{0,4}));
//
//        System.out.println(largestRectangleArea(new int[]{0,0}));
//
//        System.out.println(largestRectangleArea(new int[]{9,0,10}));

        System.out.println(largestRectangleArea(new int[]{2,1,2}));
    }


}
