package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class MergeKLists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeList(ListNode l1, ListNode l2){

        ListNode l = null;
        ListNode cur = null;

        while (l1 != null && l2 != null){

            ListNode node;
            if(l1.val < l2.val){
                node = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                node = new ListNode(l2.val);
                l2 = l2.next;
            }

            if(l == null){
                l = node;
                cur = node;
            }else{
                cur.next = node;
                cur = node;
            }
        }

        if(l1 != null){

            if(l == null){
                return l1;
            }else{
                cur.next = l1;
            }
        }

        if(l2 != null){

            if(l == null){
                return l2;
            }else{
                cur.next = l2;
            }
        }

        return l;
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0){
            return null;
        }

        ListNode result = lists[0];
        for(int i = 1, size = lists.length; i < size; i++){

            ListNode l = lists[i];
            result = mergeList(result, l);

        }

        return result;
    }
}
