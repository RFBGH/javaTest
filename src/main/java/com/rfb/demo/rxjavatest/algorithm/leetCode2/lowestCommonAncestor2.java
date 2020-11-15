package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.HashMap;
import java.util.Map;

public class lowestCommonAncestor2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    private TreeNode dfs(TreeNode cur, TreeNode p, TreeNode q){

        if(cur == null){
            return null;
        }

        if(cur.val > p.val && cur.val > q.val){
            return dfs(cur.left, p, q);
        }

        if(cur.val < p.val && cur.val < q.val){
            return dfs(cur.right, p, q);
        }

        return cur;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }
}
