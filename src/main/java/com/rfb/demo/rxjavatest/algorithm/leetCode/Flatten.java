package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class Flatten {

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

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }

        dfs(root.left);
        dfs(root.right);

        TreeNode left = root.left;
        if(left == null){
            return;
        }

        TreeNode cur = left;
        while (cur.right != null){
            cur = cur.right;
        }
        cur.right = root.right;

        root.right = root.left;
        root.left = null;
    }

    public void flatten(TreeNode root) {
        dfs(root);
    }

    public void test(){


    }

}
