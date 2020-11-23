package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class deleteNode {

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

    public TreeNode deleteNode(TreeNode root, int key) {

        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null){
            if(cur.val == key){
                break;
            }

            parent = cur;
            if(cur.val < key){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }

        if(cur == null){
            return root;
        }

        if(cur.left != null){

            while (true){
                TreeNode replaceNode = cur.left;
                TreeNode lastReplaceNode = null;
                while (replaceNode.right != null){
                    lastReplaceNode = replaceNode;
                    replaceNode = replaceNode.right;
                }
                cur.val = replaceNode.val;
                if(lastReplaceNode != null){
                    if(replaceNode.left == null){
                        lastReplaceNode.right = null;
                        break;
                    }else{
                        cur = replaceNode;
                    }
                }else{
                    cur.left = replaceNode.left;
                    break;
                }
            }
        }else if(cur.right != null){

            while (true){
                TreeNode replaceNode = cur.right;
                TreeNode lastReplaceNode = null;
                while (replaceNode.left != null){
                    lastReplaceNode = replaceNode;
                    replaceNode = replaceNode.left;
                }

                cur.val = replaceNode.val;
                if(lastReplaceNode != null){
                    if(replaceNode.right == null){
                        lastReplaceNode.left = null;
                        break;
                    }else{
                        cur = replaceNode;
                    }
                }else{
                    cur.right = replaceNode.right;
                    break;
                }
            }
        }else{

            if(parent == null){
                return null;
            }

            if (parent.left == cur) {
                parent.left = null;
            }else{
                parent.right = null;
            }
        }

        return root;
    }

}
