package com.rfb.demo.rxjavatest.algorithm.niuke;

import java.util.Stack;

public class isValid {

    public boolean isValid (String s) {
        // write code here

        Stack<Character> stack = new Stack<>();
        for(int i = 0, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
                continue;
            }

            if(stack.isEmpty()){
                return false;
            }

            char top = stack.pop();
            if(c == ')' && top != '('){
                return false;
            }

            if(c == ']' && top != '['){
                return false;
            }

            if(c == '}' && top != '{'){
                return false;
            }
        }

        return stack.isEmpty();
    }

}
