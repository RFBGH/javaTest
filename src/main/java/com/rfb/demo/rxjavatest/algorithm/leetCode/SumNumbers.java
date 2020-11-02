package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class SumNumbers {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int sum = 0;
    private void dfs(TreeNode root, int curValue){

        if(root == null){
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left == null && right == null){
            sum += curValue;
            return;
        }

        if(left != null){
            dfs(left, curValue*10+left.val);
        }

        if(right != null){
            dfs(right, curValue*10+right.val);
        }
    }

    public int sumNumbers(TreeNode root) {

        sum = 0;
        if(root != null){
            dfs(root, root.val);
        }
        return sum;
    }

    public void test(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        System.out.println(sumNumbers(root));
    }
}
