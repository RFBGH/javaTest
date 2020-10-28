package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.Stack;

public class LongestValidParentheses {

    public int longestValidParentheses(String s) {

        int max = 0;
        Stack<Object> stack = new Stack<>();

        stack.push(0);
        for(int i = 0, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
            }else{

                boolean findC = false;

                int allCount = 0;
                while (!stack.isEmpty()){
                    Object top = stack.pop();
                    if(top instanceof Integer){
                        allCount += (int)top;
                    }else{
                        allCount += 1;
                        findC = true;
                        break;
                    }
                }

                if(findC){
                    while (!stack.isEmpty()){
                        Object top = stack.peek();
                        if(top instanceof Integer){
                            allCount += (int)top;
                            stack.pop();
                        }
                        break;
                    }
                    stack.push(allCount);
                }else{
                    stack.push(0);
                }

                if(allCount > max){
                    max = allCount;
                }
            }
        }

        return max*2;
    }

    public void test(){

        System.out.println(longestValidParentheses(")()())()()("));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses("(()()"));
        System.out.println(longestValidParentheses("()(())"));
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
    }
}
