package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class delNodes {

      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    private Map<Integer, TreeNode> map = new HashMap<>();
    private Map<TreeNode, TreeNode> parents = new HashMap<>();

    private void dfs(TreeNode parent, TreeNode root){
        if(root == null){
            return;
        }

        map.put(root.val, root);
        parents.put(root, parent);

        dfs(root, root.left);
        dfs(root, root.right);
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        if(root == null){
            return new ArrayList<>();
        }

        List<TreeNode> result = new ArrayList();

        dfs(null, root);

        result.add(root);
        for(int i = 0; i < to_delete.length; i++){
            int num = to_delete[i];
            TreeNode cur = map.get(num);

            if(cur == null){
                continue;
            }
            TreeNode parent = parents.get(cur);

            result.remove(cur);
            if(parent != null){
                if(parent.left == cur){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }

            if(cur.left != null){
                result.add(cur.left);
            }

            if(cur.right != null){
                result.add(cur.right);
            }
        }

        return result;
    }

    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        delNodes(root, new int[]{3, 5});
    }

    
}
