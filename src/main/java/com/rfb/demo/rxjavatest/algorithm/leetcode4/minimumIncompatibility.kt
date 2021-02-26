package com.rfb.demo.rxjavatest.algorithm.leetcode4

class minimumIncompatibility {


    fun minimumIncompatibility(nums: IntArray, K: Int): Int {

        if(nums.size % K != 0){
            return -1
        }

        val k = nums.size / K

        val counts = IntArray(17)
        nums.forEach {
            counts[it]++
        }

        var sum = 0
        for(t in 0 until K){

            val path = ArrayList<Int>()
            for(i in 1..16){
                if(counts[i] == 0){
                    continue
                }

                path.add(i)
                counts[i]--
                if(path.size == k){
                    break
                }
            }

            if(path.size < k){
                return -1
            }

            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE
            path.forEach {
                if(min > it){
                    min = it
                }
                if(max < it){
                    max = it
                }
            }

            sum += max-min
        }

        return sum
    }

}