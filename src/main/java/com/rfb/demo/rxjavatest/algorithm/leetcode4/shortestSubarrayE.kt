package com.rfb.demo.rxjavatest.algorithm.leetcode4

class shortestSubarrayE {

    fun shortestSubarray(A: IntArray, K: Int): Int {

        val sum = IntArray(A.size)

        sum[0] = A[0]
        for(i in 1 until A.size){
            sum[i] = sum[i-1] + A[i]
        }

        var min = Int.MAX_VALUE
        for(i in A.indices){
            for(j in i until A.size){
                val temp = sum[j] - sum[i] + A[i]
                if(temp < K){
                    continue
                }

                if(min > j - i + 1){
                    min = j - i + 1
                }
            }
        }

        if(min == Int.MAX_VALUE){
            return -1
        }

        return min
    }

}