package com.rfb.demo.rxjavatest.kotlin.leetcode

class maxCoins {

    private fun dfs(nums: IntArray, from:Int, to:Int):Int{

        if(from == to){
            val left = if(from == 0){1}else{nums[from-1]}
            val right = if(from == nums.size-1){1}else{nums[from+1]}
            return left * right * nums[from]
        }

        var max = 0
        for(i in from..to){

            val left = if(i == from){ 0 }else{ dfs(nums, from, i-1) }
            val right = if(i == to){ 0 }else{ dfs(nums, i+1, to)}
            val value = left + right + nums[i]
            if(value > max){
                max = value
            }

            if(from == 0 && to == 3){
                println("$i $max $value")
            }
        }

        return max
    }

    fun maxCoins(nums: IntArray): Int {
        return dfs(nums, 0, nums.size-1)
    }

    fun test(){
//        println(maxCoins(intArrayOf(3,1,5,8)))
        println(maxCoins(intArrayOf(3,1,5,8)))
    }
}