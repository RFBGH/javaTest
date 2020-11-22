package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class kthSmallestTree {

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

    List<Integer> list = new ArrayList<>();
    int K;

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }

        dfs(root.left);
        list.add(root.val);
        if(list.size() >= K){
            return;
        }
        dfs(root.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        K = k;
        dfs(root);
        return list.get(k-1);
    }
}
