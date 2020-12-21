package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class countPairs {

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

    private Map<TreeNode, Integer> levelMap = new HashMap<>();
    private Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    private List<TreeNode> leaves = new ArrayList<>();

    private void dfs(TreeNode parent, TreeNode cur, int level) {

        if (cur == null) {
            return;
        }

        levelMap.put(cur, level);
        parentMap.put(cur, parent);

        dfs(cur, cur.left, level + 1);
        dfs(cur, cur.right, level + 1);

        if (cur.left == null && cur.right == null) {
            leaves.add(cur);
        }
    }

    private int distance(TreeNode t1, TreeNode t2) {

        int sum = levelMap.get(t1) + levelMap.get(t2);

        if (levelMap.get(t1) < levelMap.get(t2)) {
            TreeNode t = t1;
            t1 = t2;
            t2 = t;
        }

        while (levelMap.get(t1) > levelMap.get(t2)) {
            t1 = parentMap.get(t1);
        }

        while (t1 != t2) {
            t1 = parentMap.get(t1);
            t2 = parentMap.get(t2);
        }

        return sum - levelMap.get(t1);

    }

    public int countPairs(TreeNode root, int distance) {

        if (root == null) {
            return 0;
        }

        dfs(null, root, 0);
        int count = 0;
        for (int i = 0; i < leaves.size(); i++) {
            for (int j = i + 1; j < leaves.size(); j++) {
                if (distance(leaves.get(i), leaves.get(j)) <= distance) {
                    count++;
                }
            }
        }

        return count;
    }
}
