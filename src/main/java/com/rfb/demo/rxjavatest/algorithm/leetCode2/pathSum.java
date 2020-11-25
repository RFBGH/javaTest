package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pathSum {

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

    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    Map<TreeNode, Integer> sumMap = new HashMap<>();
    private List<TreeNode> nodes = new ArrayList<>();

    private void dfs(TreeNode cur, int sum){

        nodes.add(cur);
        sumMap.put(cur, sum);

        if(cur.left != null){
            parentMap.put(cur.left, cur);
            dfs(cur.left, cur.left.val+sum);
        }

        if(cur.right != null){
            parentMap.put(cur.right, cur);
            dfs(cur.right, cur.right.val+sum);
        }
    }

    public int pathSum(TreeNode root, int SUM) {

        if(root == null){
            return 0;
        }

        dfs(root, root.val);

        int result = 0;
        for(TreeNode treeNode : nodes){
            int sum = sumMap.get(treeNode);
            if(sum == SUM){
                result++;
            }

            TreeNode parent = parentMap.get(treeNode);
            while (parent != null){
                sum = sumMap.get(treeNode) - sumMap.get(parent);
                if(sum == SUM){
                    result++;
                }
                parent = parentMap.get(parent);
            }
        }
        return result;
    }

    public void test(){
//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(-3);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//        root.left.left.left = new TreeNode(3);
//        root.left.left.right = new TreeNode(-2);
//        root.left.right.right = new TreeNode(1);
//        root.right.right = new TreeNode(11);

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        System.out.println(pathSum(root, 1));
    }

}
