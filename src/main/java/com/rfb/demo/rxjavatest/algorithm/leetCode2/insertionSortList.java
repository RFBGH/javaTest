package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class insertionSortList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode insertionSortList(ListNode head) {

        ListNode cur = head;
        while (cur != null){

            ListNode next = cur.next;
            if(next == null){
                break;
            }

            cur.next = next.next;

            ListNode insert = head;
            ListNode lastNext = null;

            while (insert != cur.next){
                if(next.val <= insert.val){
                    break;
                }

                lastNext = insert;
                insert = insert.next;
            }

            if(lastNext == null){
                next.next = head;
                head = next;
            }else{
                next.next = lastNext.next;
                lastNext.next = next;
                if(lastNext == cur){
                    cur = cur.next;
                }
            }
        }

        return head;
    }

    public void test(){

        ListNode listNode = new ListNode(-1);
        listNode.next = new ListNode(5);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(0);
        insertionSortList(listNode);
    }
}
