package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class MaxSumBST2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int max = 0;

    public static class Info{
        boolean isBst;
        int sum;

        public Info(boolean isBst, int sum) {
            this.isBst = isBst;
            this.sum = sum;
        }
    }

    private Info dfs(TreeNode root){

        if(root == null){
            return new Info(true, 0);
        }

        TreeNode leftSon = root.left;
        Info left = dfs(leftSon);

        TreeNode rightSon = root.right;
        Info right = dfs(rightSon);

        boolean isBst = left.isBst && right.isBst;

        if(isBst){

            if(leftSon != null){
                TreeNode cur = leftSon;
                while (cur.right != null){
                    cur = cur.right;
                }

                if(cur.val >= root.val){
                    isBst = false;
                }
            }

            if(rightSon != null && isBst){
                TreeNode cur = rightSon;
                while (cur.left != null){
                    cur = cur.left;
                }

                if(cur.val <= root.val){
                    isBst = false;
                }
            }
        }

        if(isBst){
            int sum = left.sum + right.sum + root.val;
            if(sum > max){
                max = sum;
            }

            return new Info(true, sum);
        }

        return new Info(false, 0);
    }

    public int maxSumBST(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

//        TreeNode root = new TreeNode(4);
//        root.left = new TreeNode(8);
//        root.left.left = new TreeNode(6);
//        root.left.right = new TreeNode(1);
//        root.left.left.left = new TreeNode(9);
//        root.left.right.left = new TreeNode(-5);
//        root.left.right.right = new TreeNode(4);
//        root.left.right.left.right = new TreeNode(-3);
//        root.left.right.right.right = new TreeNode(10);


        System.out.println(maxSumBST(root));
    }
}
