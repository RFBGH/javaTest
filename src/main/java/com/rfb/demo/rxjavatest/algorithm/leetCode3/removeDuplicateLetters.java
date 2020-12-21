package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.Stack;

public class removeDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        int[] counts = new int[26];
        for(int i = 0; i < s.length(); i++){
            counts[s.charAt(i)-'a']++;
        }

        boolean[] take = new boolean[26];

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(take[c-'a']){
                counts[c-'a']--;
                continue;
            }

            while (!stack.isEmpty()){

                char top = stack.peek();
                if(top >= c && counts[top-'a'] > 0){
                    stack.pop();
                    take[top-'a'] = false;
                    continue;
                }

                break;
            }

            counts[c-'a']--;
            take[c-'a'] = true;
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < stack.size(); i++){
            sb.append(stack.get(i));
        }
        return sb.toString();
    }

    public void test(){

        System.out.println(removeDuplicateLetters("bbcaac"));

    }
}
