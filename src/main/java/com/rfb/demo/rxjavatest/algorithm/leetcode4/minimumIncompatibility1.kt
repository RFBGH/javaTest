package com.rfb.demo.rxjavatest.algorithm.leetcode4

class minimumIncompatibility1 {

    var ans = Int.MAX_VALUE

    private fun dfs(nums: IntArray, k: Int, take:BooleanArray, path:ArrayList<Int>){

        if(path.size == nums.size){

            var sum = 0
            for(offset in path.indices step k){
                var max = Int.MIN_VALUE
                var min = Int.MAX_VALUE
                for(i in 0 until k){
                    val num = path[i+offset]
                    if(max < num){
                        max = num
                    }

                    if(min > num){
                        min = num
                    }

                    sum += max - min
                }
            }

            if(ans > sum){
                ans = sum
            }
            return
        }

        for(i in nums.indices){

            if(take[i]){
                continue
            }

            val num = nums[i]
            val offset = path.size / k * k
            var isContain = false
            for(j in offset until path.size){
                if(path[j] == num){
                    isContain = true
                    break
                }
            }

            if(isContain){
                continue
            }

            take[i] = true
            path.add(num)
            dfs(nums, k, take, path)
            path.removeAt(path.size-1)
            take[i] = false
        }

    }

    fun minimumIncompatibility(nums: IntArray, k: Int): Int {

        if(nums.size % k != 0){
            return -1
        }
        val K = nums.size / k

        dfs(nums, K, BooleanArray(nums.size), ArrayList())

        if(ans == Int.MAX_VALUE){
            return -1
        }
        return ans
    }

}