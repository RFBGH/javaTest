package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Sum2 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        ListNode ans = null;
        ListNode cur = null;
        int carry = 0;
        while (cur1 != null && cur2 != null){
            int result = cur1.val + cur2.val + carry;
            carry = result / 10;

            ListNode node = new ListNode(result % 10);
            if(ans == null){
                ans = node;
                cur = node;
            }else{
                cur.next = node;
                cur = node;
            }

            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        ListNode restCur = cur1;
        if(restCur == null){
            restCur = cur2;
        }

        while (restCur != null){
            int result = restCur.val + carry;
            carry = result / 10;

            ListNode node = new ListNode(result % 10);
            if(ans == null){
                ans = node;
                cur = node;
            }else{
                cur.next = node;
                cur = node;
            }

            restCur = restCur.next;
        }

        if(carry != 0){
            cur.next = new ListNode(carry);;
        }

        return ans;
    }

    public void test(){
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        addTwoNumbers(l1, l2);
    }

}
