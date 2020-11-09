package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur == null){
                sb.append("#");
            }else{
                sb.append(cur.val);
                queue.add(cur.left);
                queue.offer(cur.right);
            }
            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data == null){
            return null;
        }

        String[] segments = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(Integer.parseInt(segments[0]));
        queue.add(root);

        int index = 1;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            String left = segments[index++];
            if(!left.equals("#")){
                cur.left = new TreeNode(Integer.parseInt(left));
                queue.offer(cur.left);
            }
            String right = segments[index++];
            if(!right.equals("#")){
                cur.right = new TreeNode(Integer.parseInt(right));
                queue.offer(cur.right);
            }
        }

        return root;
    }
}
