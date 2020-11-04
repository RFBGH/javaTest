package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class pathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private static class Node{
        TreeNode treeNode;
        Node pre;
        int sum;

        public Node(TreeNode cur, Node pre, int sum) {
            this.treeNode = cur;
            this.pre = pre;
            this.sum = sum;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        List<Node> targets = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, null, root.val));
        while (!queue.isEmpty()){
            Node cur = queue.poll();


            TreeNode left = cur.treeNode.left;
            if(left != null){
                queue.add(new Node(left, cur, cur.sum + left.val));
            }

            TreeNode right = cur.treeNode.right;
            if(right != null){
                queue.add(new Node(right, cur, cur.sum + right.val));
            }

            if(left == null && right == null && cur.sum == sum){
                targets.add(cur);
            }
        }

        for(Node target : targets){

            List<Integer> list = new ArrayList<>();
            Node cur = target;
            while (cur != null){
                list.add(cur.treeNode.val);
                cur = cur.pre;
            }

            Collections.reverse(list);
            result.add(list);
        }

        return result;
    }

}
