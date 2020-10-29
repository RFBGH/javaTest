package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class MaxSumBST {

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
    private int maxSonSum = 0;
    Map<TreeNode, Boolean> isBstMap = new HashMap<>();
    Map<TreeNode, Integer> sumMap = new HashMap<>();

    private int sum(TreeNode root){

        if(root == null){
            return 0;
        }

        if(sumMap.containsKey(root)){
            return sumMap.get(root);
        }

        int left = sum(root.left);
        int right = sum(root.right);
        int sum = left + right + root.val;
        if(sum > maxSonSum){
            maxSonSum = sum;
        }

        sumMap.put(root, sum);
        return sum;
    }

    private boolean isBST(TreeNode root){

        if(root == null){
            return true;
        }

        if(isBstMap.containsKey(root)){
            return isBstMap.get(root);
        }

        TreeNode leftSon = root.left;
        if(!isBST(leftSon)){
            return false;
        }

        TreeNode rightSon = root.right;
        if(!isBST(rightSon)){
            return false;
        }

        if(leftSon != null){
            TreeNode cur = leftSon;
            while (cur.right != null){
                cur = cur.right;
            }

            if(cur.val >= root.val){
                return false;
            }
        }

        if(rightSon != null){
            TreeNode cur = rightSon;
            while (cur.left != null){
                cur = cur.left;
            }

            if(cur.val <= root.val){
                return false;
            }
        }

        return true;
    }

    private void dfs(TreeNode root){

        if(root == null){
            return;
        }

        boolean isBst = isBST(root);

        if(isBst){
            isBstMap.put(root, true);
        }else{
            isBstMap.put(root, false);
        }

        if(isBst){
            maxSonSum = 0;
            sum(root);
            if(max < maxSonSum){
                max = maxSonSum;
            }
            return;
        }

        TreeNode left = root.left;
        dfs(left);

        TreeNode right = root.right;
        dfs(right);
    }

    public int maxSumBST(TreeNode root) {
        max = 0;
        isBstMap.clear();
        sumMap.clear();
        dfs(root);
        return max;
    }

    public void test(){
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(4);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);
//
//        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(5);
//        root.right.right.left = new TreeNode(4);
//        root.right.right.right = new TreeNode(6);

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(1);
        root.left.left.left = new TreeNode(9);
        root.left.right.left = new TreeNode(-5);
        root.left.right.right = new TreeNode(4);
        root.left.right.left.right = new TreeNode(-3);
        root.left.right.right.right = new TreeNode(10);


        System.out.println(maxSumBST(root));
    }
}
