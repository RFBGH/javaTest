package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class inorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {

        if(root == null){
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        Set<TreeNode> hasLeft = new HashSet<>();
        Set<TreeNode> hasRight = new HashSet<>();

        while (!stack.isEmpty()){

            TreeNode top = stack.peek();
            if(!hasLeft.contains(top) && top.left != null){
                stack.push(top.left);
                hasLeft.add(top);
                continue;
            }

            if(!hasRight.contains(top)){
                result.add(top.val);
                if(top.right != null){
                    stack.push(top.right);
                    hasRight.add(top);
                }else{
                    stack.pop();
                }
            }else{
                stack.pop();
            }
        }

        return result;
    }

    public void test(){
        TreeNode root =new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        inorderTraversal(root);
    }

}
