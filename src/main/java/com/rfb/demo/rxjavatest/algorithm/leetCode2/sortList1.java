package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sortList1 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {

        if(head == null){
            return null;
        }

        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }

        Collections.sort(list);
        cur = head;
        for(int i : list){
            cur.val = i;
            cur = cur.next;
        }
        return head;
    }
}
