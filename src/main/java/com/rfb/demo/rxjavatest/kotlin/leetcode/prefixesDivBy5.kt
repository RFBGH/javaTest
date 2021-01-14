package com.rfb.demo.rxjavatest.kotlin.leetcode

class prefixesDivBy5 {

    fun prefixesDivBy5(A: IntArray): BooleanArray {

        val ans = BooleanArray(A.size)

        var num = 0
        for(i in A.indices){

            num = num.shl(1)
            if(A[i] == 1){
                num += 1
            }

            ans[i] = num % 5 == 0
            num %= 5
        }

        return ans
    }
}