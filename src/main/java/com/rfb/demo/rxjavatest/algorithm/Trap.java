package com.rfb.demo.rxjavatest.algorithm;

import java.util.Stack;

public class Trap {

    public static class Info{
        int height;
        int index;

        public Info(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    public int trap(int[] height) {

        if(height.length == 0){
            return 0;
        }

        int container = 0;
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(height[0], 0));

        for(int i = 1, size = height.length; i < size; i++){

            int cur = height[i];
            Info info = stack.peek();
            if(info.height > cur){
                stack.push(new Info(cur, i));
                continue;
            }

            stack.pop();
            if(stack.isEmpty()){
                stack.push(new Info(cur, i));
                continue;
            }

            int sum = 0;
            while (!stack.isEmpty()){
                Info temp = stack.peek();
                sum += info.height * (info.index - temp.index);
                info = temp;

                if(temp.height > cur){
                    break;
                }
                stack.pop();
            }

            int high = info.height;
            if(high > cur){
                high = cur;
            }
            int rect = high*(i - info.index - 1);

            rect -= sum;
            container += rect;
            stack.push(new Info(cur, i));
        }

        return container;
    }

    public void test(){

        System.out.println(trap(new int[]{5,5,1,7,1,1,5,2,7,6}));

        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

        System.out.println(trap(new int[]{4,2,0,3,2,5}));

    }

}
