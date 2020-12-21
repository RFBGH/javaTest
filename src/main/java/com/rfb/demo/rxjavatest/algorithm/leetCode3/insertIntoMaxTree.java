package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class insertIntoMaxTree {

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


    public TreeNode insertIntoMaxTree(TreeNode root, int val) {

        TreeNode cur = root;
        TreeNode parent = null;

        while (true){

            if(cur == null || cur.val < val){
                TreeNode node = new TreeNode(val);
                node.left = cur;
                if(parent == null){
                    root = node;
                }else{
                    parent.right = node;
                }
                break;
            }

            parent = cur;
            cur = cur.right;
        }

        return root;
    }
}
