package com.rfb.demo.rxjavatest.algorithm.leetcode4

class mctFromLeafValues {

    val map = HashMap<Int, Int>()

    fun dfs(arr: IntArray, left:Int, right:Int):Int{

        if(left == right){
            return arr[left]
        }

        val key = left*40+right
        if(map.containsKey(key)){
            return map[key]!!
        }

        var min = Int.MAX_VALUE
        for(i in left until right){

            var maxLeft = Int.MIN_VALUE
            for(j in left..i){
                if(arr[j] > maxLeft){
                    maxLeft = arr[j]
                }
            }

            var maxRight = Int.MIN_VALUE
            for(j in i+1..right){
                if(arr[j] > maxRight){
                    maxRight = arr[j]
                }
            }

            var sum = dfs(arr, left, i) + dfs(arr, i+1, right) + maxLeft*maxRight
            if(min > sum){
                min = sum
            }
        }

        map[key] = min
        return min
    }

    fun mctFromLeafValues(arr: IntArray): Int {

        var sum = 0
        arr.forEach {
            sum += it
        }
        return dfs(arr, 0, arr.size-1) - sum
    }

    fun test(){
        println(mctFromLeafValues(intArrayOf(6,2,4)))
    }
}