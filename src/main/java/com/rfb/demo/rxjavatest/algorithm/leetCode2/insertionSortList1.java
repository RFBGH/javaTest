package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class insertionSortList1 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode insertionSortList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }

        Collections.sort(list);
        cur = head;
        for(int i = 0, size = list.size(); i < size; i++){
            cur.val = list.get(i);
            cur = cur.next;
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
