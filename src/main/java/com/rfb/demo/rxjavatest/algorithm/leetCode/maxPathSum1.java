package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class maxPathSum1 {

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

    Map<TreeNode, Integer> idMap = new HashMap<>();
    Map<Integer, TreeNode> treeMap = new HashMap<>();
    private void dfs(TreeNode root){

        if(root == null){
            return;
        }

        int size = idMap.size();
        idMap.put(root, size);
        treeMap.put(size, root);
        dfs(root.left);
        dfs(root.right);
    }

    int[] levels;
    int[] parents;
    int[] sums;
    private void dfs2(TreeNode root, int sum, int level){

        if(root == null){
            return;
        }

        int id = idMap.get(root);
        sums[id] = sum + root.val;
        levels[id] = level;

        TreeNode left = root.left;
        if(left != null){
            parents[idMap.get(left)] = id;
            dfs2(left, sums[id], level+1);
        }

        TreeNode right = root.right;
        if(right != null){
            parents[idMap.get(right)] = id;
            dfs2(right, sums[id], level+1);
        }
    }

    private int lca(int x, int y){
        if(levels[x] < levels[y]){
            int t = x;
            x = y;
            y = t;
        }

        while (levels[x] > levels[y]){
            x = parents[x];
        }

        while (x != y){
            x = parents[x];
            y = parents[y];
        }
        return x;
    }

    public int maxPathSum(TreeNode root) {

        if(root == null){
            return 0;
        }

        dfs(root);
        int size = idMap.size();
        if(size == 1){
            return root.val;
        }

        parents = new int[size];
        sums = new int[size];
        levels = new int[size];

        dfs2(root, 0, 0);
        parents[idMap.get(root)] = -1;

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < size; i++){
            for(int j = i; j < size; j++){

                int lca = lca(i, j);
                int sum = sums[i] + sums[j] - sums[lca]*2 + treeMap.get(lca).val;
                if(sum > max){
                    max = sum;
                }
            }
        }

        return max;
    }

    public void test(){

        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);

        System.out.println(maxPathSum(root));

    }
}
