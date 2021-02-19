package com.rfb.demo.rxjavatest.algorithm.leetcode4

class longestOnes {

    fun longestOnes(A: IntArray, K: Int): Int {

        var ans = 0
        var left = 0
        var right = 0
        var rest = K
        while (right < A.size){

            if(A[right] == 1){
                right++
            }else{
                if(rest > 0){
                    rest--
                    right++
                }else{
                    if(A[left] == 0){
                        rest++
                    }
                    left++
                }
            }

            if (right - left > ans){
                ans = right - left
            }
        }

        return ans
    }

}