package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class isSubStructure {


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
    private boolean equals(TreeNode rootA, TreeNode rootB){

        if(rootB == null){
            return true;
        }

        if(rootA == null){
            return false;
        }

        if(rootA.val != rootB.val){
            return false;
        }

        if(!equals(rootA.left, rootB.left)){
            return false;
        }

        if(!equals(rootA.right, rootB.right)){
            return false;
        }

        return true;
    }

    private boolean dfs(TreeNode A, TreeNode B){

        if(A == null){
            return false;
        }

        if(A.val == B.val){
            if(equals(A, B)){
                return true;
            }
        }

        if(dfs(A.left, B)){
            return true;
        }

        if(dfs(A.right, B)){
            return true;
        }

        return false;
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if(A == null || B == null){
            return false;
        }

        return dfs(A, B);
    }

    public void test(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        TreeNode rootB = new TreeNode(3);
        System.out.println(isSubStructure(root, rootB));
    }
}
