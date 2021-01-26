package com.rfb.demo.rxjavatest.algorithm.leetcode4

class shortestSubarray {

    fun shortestSubarray(A: IntArray, K: Int): Int {

        var min = Int.MAX_VALUE
        var left = 0
        var sum = 0
        for(i in A.indices){

            sum += A[i]

            if(sum >= K || A[left]<0){

                var skip = false
                while ((sum >= K || A[left]<0) && left < i){
                    sum -= A[left++]
                    skip = true
                }

                if(skip){
                    if(i - left + 2 < min){
                        min = i - left + 2
                    }
                }else{
                    if(i - left + 1 < min){
                        min = i - left + 1
                    }
                }

            }

        }

        if(min == Int.MAX_VALUE){
            return -1
        }

        return min
    }

}