package com.rfb.demo.rxjavatest.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class LCS_E {

    private static class TreeNode{
        char c;
        Map<Character, TreeNode> son = new HashMap<>();

        public TreeNode(char c) {
            this.c = c;
        }
    }

    private void addWord(TreeNode root, String s, int start){

        TreeNode cur = root;
        for(int i = start, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            TreeNode next = cur.son.get(c);
            if(next == null){
                next = new TreeNode(c);
                cur.son.put(c, next);
            }
            cur = next;
        }
    }

    private int findLen(TreeNode root, String s, int start){

        TreeNode cur = root;
        for(int i = start, size = s.length(); i < size; i++){
            char c = s.charAt(i);
            TreeNode next = cur.son.get(c);
            if(next == null){
                return i - start;
            }
            cur = next;
        }

        return s.length()-start;
    }

    public String LCS (String str1, String str2) {
        // write code here

        TreeNode root = new TreeNode((char) 0);
        for(int i = 0, size = str1.length(); i < size; i++){
            addWord(root, str1, i);
        }

        int max = 0;
        int from = 0;
        int to = 0;
        for(int i = 0, size = str2.length(); i < size; i++){
            int len = findLen(root, str2, i);
            if(max < len){
                max = len;
                from = i;
                to = from + len;
            }
        }

        return str2.substring(from, to);
    }

    public void test(){
        System.out.println(LCS("1AB2345CD","12345EF"));
    }

}
