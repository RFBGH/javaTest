package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pathSum2 {

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


    private int dfs(TreeNode cur, int sum){

        int count = 0;

        if(sum == cur.val){
            count++;
        }

        if(cur.left != null){
            count += dfs(cur.left, sum-cur.val);
        }

        if(cur.right != null){
            count += dfs(cur.right, sum-cur.val);
        }
        return count;
    }

    public int pathSum(TreeNode root, int SUM) {

        if(root == null){
            return 0;
        }

        int count = dfs(root, SUM);
        count += pathSum(root.left, SUM);
        count += pathSum(root.right, SUM);
        return count;
    }

    public void test(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);

//        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(1);

        System.out.println(pathSum(root, 8));
    }

}
