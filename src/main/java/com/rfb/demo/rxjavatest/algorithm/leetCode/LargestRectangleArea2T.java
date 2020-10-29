package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LargestRectangleArea2T {

    public int largestRectangleArea(int[] heights) {

        if(heights.length == 0){
            return 0;
        }

        if(heights.length == 1){
            return heights[0];
        }

        int max = 0;
        Map<Integer, Integer> leftRectMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1, size = heights.length; i < size; i++){

            int top = stack.peek();
            if(heights[top] < heights[i]){
                stack.push(i);
                continue;
            }

            if(heights[i] == 0){

                while (!stack.isEmpty()){
                    int temp = stack.pop();
                    int rect;
                    if(heights[temp] == 0){
                        rect = heights[top] * (top - temp);
                    }else{
                        rect = heights[temp] * (top - temp + 1);
                    }

                    if(rect > max){
                        max = rect;
                    }
                    top = temp;
                }

                if(heights[top] > max){
                    max = top;
                }

                stack.push(i);
                continue;
            }

            stack.pop();
            while (!stack.isEmpty()){
                int temp = stack.peek();

                int rect = heights[temp] * (top - temp + 1);
                if(leftRectMap.containsKey(temp)){
                    rect += leftRectMap.get(temp);
                }

                if(rect > max){
                    max = rect;
                }

                if (heights[temp] < heights[i]) {
                    break;
                }
                stack.pop();
            }

            if(stack.isEmpty()){
                top--;
            }

            int rect = heights[i] * (i - top);
            leftRectMap.put(i, rect);

            if(max < rect){
                max = rect;
            }

            stack.push(i);
        }

        if(stack.isEmpty()){
            return max;
        }

        int top = stack.pop();
        while (!stack.isEmpty()){
            int temp = stack.pop();
            int rect;
            if(heights[temp] == 0){
                rect = heights[top] * (top - temp);
            }else{
                rect = heights[temp] * (top - temp + 1);
            }

            if(rect > max){
                max = rect;
            }
            top = temp;
        }

        if(heights[top] > max){
            max = top;
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
