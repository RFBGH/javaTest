package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sortList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private ListNode mergeList(ListNode start0, int count0, ListNode start1, int count1){

        ListNode head = null;
        ListNode temp = null;
        while (count0 != 0 && count1 != 0){
            if(start0.val < start1.val){
                if(temp == null){
                    temp = start0;
                    head = start0;
                }else{
                    temp.next = start0;
                    temp = start0;
                }
                start0 = start0.next;
                count0--;
            }else{
                if(temp == null){
                    temp = start1;
                    head = start1;
                }else{
                    temp.next = start1;
                    temp = start1;
                }
                start1 = start1.next;
                count1--;
            }
        }

        while (count0 != 0){
            if(temp == null){
                temp = start0;
                head = start0;
            }else{
                temp.next = start0;
                temp = start0;
            }
            count0--;
            start0 = start0.next;
        }

        while (count1 != 0){
            if(temp == null){
                temp = start1;
                head = start1;
            }else{
                temp.next = start1;
                temp = start1;
            }
            count1--;
            start1 = start1.next;
        }

        temp.next = null;
        return head;
    }

    private ListNode dfs(ListNode head, int from, int to){

        if(from == to){
            return head;
        }

        int mid = (from + to) / 2;
        ListNode cur = head;
        for(int i = from; i <= mid; i++){
            cur = cur.next;
        }

        ListNode start0 = dfs(head, from, mid);
        ListNode start1 = dfs(cur, mid+1, to);

        return mergeList(start0, mid-from+1, start1, to-mid);
    }

    public ListNode sortList(ListNode head) {

        if(head == null){
            return null;
        }

        int count = 0;
        ListNode cur = head;
        while (cur != null){
            count++;
            cur = cur.next;
        }

        return dfs(head, 0, count-1);
    }

    public void test(){
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        ListNode temp = sortList(head);
        System.out.println(temp.toString());
    }
}
