package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BSTSequences {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int n = 0;

    private List<List<Integer>> result = new ArrayList<>();
    private Set<Integer> gone = new HashSet<>();
    private void dfs(TreeNode cur, List<TreeNode> availible, List<Integer> list){


        if(cur.left != null){
            availible.add(cur.left);
        }

        if(cur.right != null){
            availible.add(cur.right);
        }

        list.add(cur.val);
        if(list.size() == n){
            result.add(new ArrayList<>(list));
            list.remove(list.size()-1);
            return;
        }

        List<TreeNode> availableTemp = new ArrayList<>(availible);
        for(TreeNode next : availableTemp){
            if(gone.contains(next.val)){
                continue;
            }
            gone.add(next.val);
            dfs(next, availible, list);
            gone.remove(next.val);
        }
        list.remove(list.size()-1);

        if(cur.left != null){
            availible.remove(availible.size()-1);
        }

        if(cur.right != null){
            availible.remove(availible.size()-1);
        }
    }

    private int dfsSize(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = dfsSize(root.left);
        int right = dfsSize(root.right);
        return left + right + 1;
    }

    public List<List<Integer>> BSTSequences(TreeNode root) {

        if(root == null){
            result.add(new ArrayList<>());
            return result;
        }

        n = dfsSize(root);
        List<TreeNode> availible = new ArrayList<>();
        availible.add(root);
        gone.add(root.val);
        dfs(root, availible, new ArrayList<>());
        return result;

    }

    public void test(){

//        TreeNode root = new TreeNode(2);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(3);

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        BSTSequences(root);
    }

}
