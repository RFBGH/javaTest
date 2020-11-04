package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class maxPathSum {

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

    Map<TreeNode, Integer> idMap = new HashMap<>();
    Map<Integer, TreeNode> treeMap = new HashMap<>();
    private void dfs(TreeNode root){

        if(root == null){
            return;
        }

        int size = idMap.size();
        idMap.put(root, size);
        treeMap.put(size, root);
        dfs(root.left);
        dfs(root.right);
    }

    private int maxSum[];

    private int maxDfs(TreeNode root){

        if(root == null){
            return Integer.MIN_VALUE;
        }

        if(maxSum[idMap.get(root)] != Integer.MIN_VALUE){
            return maxSum[idMap.get(root)];
        }

        int max = root.val;
        TreeNode left = root.left;
        int maxLeft = Integer.MIN_VALUE;
        if(left != null){
            maxLeft = maxDfs(left);
            maxSum[idMap.get(left)] = maxLeft;
        }


        TreeNode right = root.right;
        int maxRight = Integer.MIN_VALUE;
        if(right != null){
            maxRight = maxDfs(root.right);
            maxSum[idMap.get(right)] = maxRight;
        }

        if(maxLeft > 0 && maxLeft > maxRight){
            max += maxLeft;
        }

        if(maxRight > 0 && maxRight > maxLeft){
            max += maxRight;
        }
        return max;
    }

    public int maxPathSum(TreeNode root) {

        if(root == null){
            return 0;
        }

        dfs(root);
        int size = idMap.size();
        if(size == 1){
            return root.val;
        }

        int max = Integer.MIN_VALUE;
        maxSum = new int[size];
        for(int i = 0; i < size; i++){
            maxSum[i] = Integer.MIN_VALUE;
        }

        for(int i = 0; i < size; i++){
            TreeNode cur = treeMap.get(i);
            int maxLeft = maxDfs(cur.left);
            int maxRight = maxDfs(cur.right);

            int sum = cur.val;
            if(maxLeft > 0){
                sum += maxLeft;
            }
            if(maxRight > 0){
                sum += maxRight;
            }

            if(sum > max){
                max = sum;
            }
        }

        return max;
    }

    public void test(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(maxPathSum(root));

    }
}
