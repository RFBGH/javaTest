package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.HashMap;
import java.util.Map;

public class lowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private Map<TreeNode, TreeNode> parents = new HashMap<>();
    private Map<TreeNode, Integer> levels = new HashMap<>();

    private void dfs(TreeNode parent, TreeNode cur, int level){

        if(cur == null){
            return;
        }

        if(parent != null){
            parents.put(cur, parent);
        }

        levels.put(cur, level);

        dfs(cur, cur.left, level+1);
        dfs(cur, cur.right, level+1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        dfs(null, root, 0);

        if(levels.get(p) < levels.get(q)){
            TreeNode t = p;
            p = q;
            q = t;
        }

        while (levels.get(p) > levels.get(q)){
            p = parents.get(p);
        }

        while (true){
            if(q == p){
                return q;
            }

            p = parents.get(p);
            q = parents.get(q);
        }
    }
}
