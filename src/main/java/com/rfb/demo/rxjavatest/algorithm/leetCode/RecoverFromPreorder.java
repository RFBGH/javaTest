package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RecoverFromPreorder {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode recoverFromPreorder(String S) {



        int size = S.length();
        int val = 0;
        int i = 0;

        while (true){
            val *= 10;
            val += S.charAt(i) - '0';
            i++;
            if(i == size){
                break;
            }

            if(S.charAt(i) < '0' || S.charAt(i) > '9'){
                break;
            }
        }

        TreeNode root = new TreeNode(val);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 0);

        while (i < size){

            char c = S.charAt(i);
            int level = 0;
            while (c == '-'){
                i++;
                level++;
                c = S.charAt(i);
            }

            val = 0;
            while (true){
                val *= 10;
                val += S.charAt(i) - '0';
                i++;
                if(i == size){
                    break;
                }

                if(S.charAt(i) < '0' || S.charAt(i) > '9'){
                    break;
                }
            }

            TreeNode node = new TreeNode(val);
            levelMap.put(node, level);

            TreeNode top = stack.peek();

            int lastLevel = levelMap.get(top);
            while (lastLevel >= level){
                stack.pop();
                top = stack.peek();
                lastLevel = levelMap.get(top);
            }

            if(top.left == null){
                top.left = node;
            }else{
                top.right = node;
            }
            stack.push(node);
        }

        return root;
    }

    public void test(){

//        TreeNode node = recoverFromPreorder("1-2--3--4-5--6--7");
        TreeNode node = recoverFromPreorder("5-4--4");

        System.out.println(node);

    }

}
