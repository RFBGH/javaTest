package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class countNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        return dfs(root.left)+dfs(root.right)+1;
    }

    public int countNodes(TreeNode root) {
        return dfs(root);
    }

}
