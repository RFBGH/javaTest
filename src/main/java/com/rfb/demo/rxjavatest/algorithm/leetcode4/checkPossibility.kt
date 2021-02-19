package com.rfb.demo.rxjavatest.algorithm.leetcode4

class checkPossibility {

    fun checkPossibility(nums: IntArray): Boolean {

        if(nums.size == 1){
            return true
        }

        var count = 0
        var max = nums[0]
        for(i in 1 until nums.size){

            if(nums[i] > max){
                max = nums[i]
            }else{
                max = nums[i]
                count++
            }

            if(count > 1){
                return false
            }
        }

        return true
    }
}