package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LevelOrderBottom {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    List<List<Integer>> result =new ArrayList<>();

    private void dfs(List<TreeNode> treeNodes){

        if(treeNodes.isEmpty()){
            return;
        }

        List<TreeNode> sons = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(TreeNode treeNode : treeNodes){
            list.add(treeNode.val);
            TreeNode left = treeNode.left;
            if(left != null){
                sons.add(left);
            }

            TreeNode right = treeNode.right;
            if(right != null){
                sons.add(right);
            }
        }

        dfs(sons);
        result.add(list);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        if(root == null){
            return new ArrayList<>();
        }

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        dfs(treeNodes);
        return result;
    }

}
