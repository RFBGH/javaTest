package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class RecoverTree {

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

    private TreeNode getMaxNode(TreeNode root){

        if(root == null){
            return null;
        }

        int max = Integer.MIN_VALUE;
        TreeNode find = null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur.val > max){
                max = cur.val;
                find = cur;
            }
            if(cur.left != null){
                queue.offer(cur.left);
            }

            if(cur.right != null){
                queue.offer(cur.right);
            }
        }

        return find;
    }

    private TreeNode getMinNode(TreeNode root){

        if(root == null){
            return null;
        }

        int min = Integer.MAX_VALUE;
        TreeNode find = null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur.val < min){
                min = cur.val;
                find = cur;
            }
            if(cur.left != null){
                queue.offer(cur.left);
            }

            if(cur.right != null){
                queue.offer(cur.right);
            }
        }

        return find;
    }

    private void dfs(TreeNode root){

        if(root == null){
            return;
        }

        TreeNode max = getMaxNode(root.left);
        TreeNode min = getMinNode(root.right);

        if(max != null && min != null){

            int a = max.val;
            int b = root.val;
            if(a > b){
                int temp = a;
                a = b;
                b = temp;
            }

            int c = min.val;
            if(c > b){
                max.val = a;
                root.val = b;
                min.val = c;
            }else if(c > a){
                max.val = a;
                root.val = c;
                min.val = b;
            }else{
                max.val = c;
                root.val = a;
                min.val = b;
            }

        }else if(max != null){
            if(root.val < max.val){
                int temp = root.val;
                root.val = max.val;
                max.val = temp;
            }
        }else if(min != null){
            if(root.val > min.val){
                int temp = min.val;
                min.val = root.val;
                root.val = temp;
            }
        }

        dfs(root.left);
        dfs(root.right);
    }

    public void recoverTree(TreeNode root) {
        dfs(root);
    }

    public void test(){

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        recoverTree(root);
    }
}
