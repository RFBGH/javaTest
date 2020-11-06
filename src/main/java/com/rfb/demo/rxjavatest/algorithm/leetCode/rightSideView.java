package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class rightSideView {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private List<Integer> result = new ArrayList<>();

    private void dfs(List<TreeNode> treeNodes){

        if(treeNodes.isEmpty()){
            return;
        }

        result.add(treeNodes.get(treeNodes.size()-1).val);
        List<TreeNode> sons = new ArrayList<>();
        for(TreeNode treeNode : treeNodes){
            if(treeNode.left != null){
                sons.add(treeNode.left);
            }
            if(treeNode.right != null){
                sons.add(treeNode.right);
            }
        }

        dfs(sons);
    }

    public List<Integer> rightSideView(TreeNode root) {

        if(root == null){
            return result;
        }

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        dfs(treeNodes);
        return result;
    }
}
