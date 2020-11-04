package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class sortedListToBST {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

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

    private TreeNode dfs(List<Integer> list, int from, int to){

        if(from > to){
            return null;
        }

        int mid = (from + to) / 2;
        TreeNode treeNode = new TreeNode(list.get(mid));

        treeNode.left = dfs(list, from, mid-1);
        treeNode.right = dfs(list, mid+1, to);

        return treeNode;
    }

    public TreeNode sortedListToBST(ListNode head) {

        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }

        return dfs(list, 0, list.size()-1);
    }
}
