package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.Map;

public class subtreeWithAllDeepest {

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

    private Map<TreeNode, Integer> heightMap = new HashMap<>();

    private int dfs(TreeNode root){

        if(root == null){
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        int height = left;
        if(height < right){
            height = right;
        }

        height++;
        heightMap.put(root, height);
        return height;
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        if(root == null){
            return root;
        }

        dfs(root);
        TreeNode cur = root;
        while(true){
            if(cur.left == null && cur.right == null){
                return cur;
            }

            if(cur.left == null){
                cur = cur.right;
                continue;
            }

            if(cur.right == null){
                cur = cur.left;
                continue;
            }

            int heightLeft = heightMap.get(cur.left);
            int heightRight = heightMap.get(cur.right);
            if(heightLeft == heightRight){
                return cur;
            }

            if(heightLeft > heightRight){
                cur = cur.left;
            }else{
                cur = cur.right;
            }
        }
    }

    public void test(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        subtreeWithAllDeepest(root);
    }
}
