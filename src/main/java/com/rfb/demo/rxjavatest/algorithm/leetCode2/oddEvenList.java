package com.rfb.demo.rxjavatest.algorithm.leetCode2;


public class oddEvenList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode oddEvenList(ListNode head) {

        if(head == null){
            return null;
        }

        ListNode odd = head;
        ListNode even = odd.next;
        if(even == null){
            return head;
        }

        ListNode nextOdd = even.next;
        if(nextOdd == null){
            return head;
        }

        while (true){
            even.next = nextOdd.next;
            ListNode oddNext = odd.next;
            odd.next = nextOdd;
            nextOdd.next = oddNext;
            odd = nextOdd;
            even = even.next;
            if(even == null){
                break;
            }
            nextOdd = even.next;
            if(nextOdd == null){
                break;
            }
        }

        return head;
    }

    public void test(){


    }
}
