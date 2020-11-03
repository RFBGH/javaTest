package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecoverTree4 {

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

    private Map<Integer, TreeNode> map = new HashMap<>();
    private List<Integer> list = new ArrayList<>();

    private void dfs(TreeNode root){

        if(root == null){
            return;
        }

        map.put(root.val, root);
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

    public void recoverTree(TreeNode root) {
        dfs(root);

        int i = 0;
        int size = list.size();
        while (true){
            if(i == size-1 || list.get(i) > list.get(i+1)){
                break;
            }
            i++;
        }

        int j = i+1;

        while (j < size){
            if(list.get(j) < list.get(i+1)){
                break;
            }
            j++;
        }
        if(j == size){
            j = i+1;
            while (j < size){
                if(list.get(j) < list.get(i)){
                    break;
                }
                j++;
            }
        }

        int error1 = list.get(i);
        int error2 = list.get(j);

        TreeNode node1 = map.get(error1);
        TreeNode node2 = map.get(error2);
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public void test(){

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);

//        TreeNode root = new TreeNode(2);
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(1);
//        recoverTree(root);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        recoverTree(root);

    }
}
