package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class MaxSumBST3 {

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

    private boolean dfs(TreeNode root){

        if(root == null){
            return true;
        }

        TreeNode leftSon = root.left;
        boolean isLeftBst = dfs(leftSon);

        TreeNode rightSon = root.right;
        boolean isRightBst = dfs(rightSon);

        boolean isBst = isLeftBst && isRightBst;

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

            maxSonSum = 0;
            sum(root);

            if(maxSonSum > max){
                max = maxSonSum;
            }
        }

        return isBst;
    }

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
        if(maxSonSum < sum){
            maxSonSum = sum;
        }

        sumMap.put(root, sum);
        return sum;
    }

    public int maxSumBST(TreeNode root) {
        max = 0;
        isBstMap.clear();
        sumMap.clear();

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
