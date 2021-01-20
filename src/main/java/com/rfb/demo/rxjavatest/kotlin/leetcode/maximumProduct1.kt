package com.rfb.demo.rxjavatest.kotlin.leetcode

class maximumProduct1{

    fun maximumProduct(nums: IntArray): Int {

        nums.sort()

        val num1 = nums[nums.size-1]*nums[nums.size-2]*nums[nums.size-3]
        val num2 = nums[nums.size-1]*nums[0]*nums[1]

        if(num1 > num2){
            return num1
        }
        return num2
    }

}