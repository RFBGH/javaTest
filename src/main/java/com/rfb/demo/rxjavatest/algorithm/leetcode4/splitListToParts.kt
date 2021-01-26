package com.rfb.demo.rxjavatest.algorithm.leetcode4

class splitListToParts {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun splitListToParts(root: ListNode?, K: Int): Array<ListNode?> {

        val ans = Array<ListNode?>(K){null}

        var cur = root
        var len = 0
        while (cur != null){
            cur = cur.next
            len++
        }

        val cut = len / K
        var rest = len % K
        cur = root
        for(k in 0 until K){

            var head = cur
            var next = cur?.next

            if(cut > 0){
                for(i in 0 until cut-1){
                    cur = next
                    next = cur?.next
                }

                if(rest > 0){
                    rest--
                    cur = next
                    next = cur?.next
                }
            }

            if(cur != null){
                cur.next = null
            }
            cur = next
            ans[k] = head
        }

        return ans
    }

}