package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class buildTreeE1 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int index = 0;
    private TreeNode dfs(int[] preorder, int[] inorder, int from, int to){

        int val = preorder[index++];
        int i;
        for(i = from; i <= to; i++){
            if(inorder[i] == val){
                break;
            }
        }

        TreeNode treeNode = new TreeNode(val);
        if(i > from){
            treeNode.left = dfs(preorder, inorder, from, i-1);
        }
        if(i < to){
            treeNode.right = dfs(preorder, inorder, i+1, to);
        }

        return treeNode;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(inorder.length == 0){
            return null;
        }

        return dfs(preorder, inorder, 0, inorder.length-1);
    }

    public void test(){
        TreeNode root = buildTree(new int[]{3,1,2,4}, new int[]{1,2,3,4});
        System.out.println("xxx"+root);
    }
}
