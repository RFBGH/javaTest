package com.rfb.demo.rxjavatest.algorithm;

import java.util.Stack;

public class Trap2 {

    public int trap(int[] height) {

        if(height.length == 0){
            return 0;
        }

        int container = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i = 1, size = height.length; i < size; i++){

            int cur = height[i];
            int info = stack.peek();
            if(height[info] > cur){
                stack.push(i);
                continue;
            }

            stack.pop();
            if(stack.isEmpty()){
                stack.push(i);
                continue;
            }

            int sum = 0;
            while (!stack.isEmpty()){
                int temp = stack.peek();
                sum += height[info] * (info - temp);
                info = temp;

                if(height[temp] > cur){
                    break;
                }
                stack.pop();
            }

            int high = height[info];
            if(high > cur){
                high = cur;
            }
            int rect = high*(i - info - 1);

            rect -= sum;
            container += rect;
            stack.push(i);
        }

        return container;
    }

    public void test(){

        System.out.println(trap(new int[]{5,5,1,7,1,1,5,2,7,6}));

        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

        System.out.println(trap(new int[]{4,2,0,3,2,5}));

    }

}
