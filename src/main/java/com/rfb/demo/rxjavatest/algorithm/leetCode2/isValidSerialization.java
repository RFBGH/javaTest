package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class isValidSerialization {

    public boolean isValidSerialization(String preorder) {

        if(preorder.equals("#")){
            return true;
        }

        if(preorder.startsWith("#")){
            return false;
        }

        String[] nodes = preorder.split(",");
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i = 1, size = nodes.length; i < size; i++){
            String node = nodes[i];
            if(node.equals("#")){

                int count = stack.pop();
                count++;
                if(count == 1){
                    stack.push(count);
                    continue;
                }

                while (!stack.isEmpty()){
                    count = stack.pop();
                    count++;
                    if(count == 1){
                        stack.push(count);
                        break;
                    }
                }

                if(stack.isEmpty()){
                    return i == size-1;
                }
            }else{
                stack.push(0);
            }
        }

        return false;
    }

    public void test(){
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(isValidSerialization("1,#"));
        System.out.println(isValidSerialization("#,#,#"));
    }

}
