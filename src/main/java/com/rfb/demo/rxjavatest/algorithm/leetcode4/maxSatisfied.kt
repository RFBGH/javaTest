package com.rfb.demo.rxjavatest.algorithm.leetcode4

class maxSatisfied {

    fun maxSatisfied(customers: IntArray, grumpy: IntArray, X: Int): Int {

        var left = 0
        var right = 0
        var rest = X
        val n = grumpy.size
        var sum = 0
        var max = 0
        while (right < n){

            if(rest == X){
                if(grumpy[right] == 0){
                    sum += customers[right]
                    right++
                }else{
                    rest--
                    sum += customers[right]
                    right++
                }
            }else{

                if(grumpy[right] == 0){
                    sum += customers[right]
                    right++
                    if(rest > 0){
                        rest--
                    }
                }else{
                    if(rest > 0){
                        rest--
                        sum += customers[right]
                        right++
                    }else{
                        if(grumpy[left] == 1){
                            sum -= customers[left]
                            rest++
                        }
                        left++
                    }
                }
            }

            if(max < sum){
                max = sum
            }
        }

        return max
    }
}