package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.Stack;

public class IsMatch {

    public boolean dfs(String s, int sStart, String p, int pStart){

        int size = s.length();
        int pSize = p.length();

        if(sStart == size){
            if (pStart == pSize) {
                return true;
            }

            Stack<Character> stack = new Stack<>();
            char c = p.charAt(pStart);
            stack.push(c);
            for(int i = pStart+1; i < pSize; i++){
                c = p.charAt(i);
                if(c == '*'){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }

            while (!stack.isEmpty()){
                c = stack.pop();
                if(c == '*'){
                    continue;
                }

                return false;
            }

            return true;
        }

        if(pStart == pSize){
            return false;
        }

        char c = s.charAt(sStart);
        char pC = p.charAt(pStart);
        if(c == pC || pC == '.'){

            if(pStart < pSize - 1){
                char pNextC = p.charAt(pStart + 1);
                if(pNextC == '*'){
                    if(dfs(s, sStart+1, p, pStart)){
                        return true;
                    }

                    if(dfs(s, sStart+1, p, pStart+2)){
                        return true;
                    }

                    return dfs(s, sStart, p, pStart+2);
                }else{
                    return dfs(s, sStart+1, p, pStart+1);
                }
            }else{
                return dfs(s, sStart+1, p, pStart+1);
            }
        }else{

            if(pStart < pSize - 1){
                char pNextC = p.charAt(pStart + 1);
                if(pNextC == '*'){
                    return dfs(s, sStart, p, pStart+2);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    public boolean isMatch(String s, String p) {
        return dfs(s, 0, p, 0);
    }

    public void test(){

        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));

//        System.out.println(isMatch("aaa", "ab*a"));
//        System.out.println(isMatch("aaa", "ab*a*c*a"));

        System.out.println(isMatch("a", "ab*"));
//        System.out.println(isMatch("bbbba", ".*a*a"));
    }

}
