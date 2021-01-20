package com.rfb.demo.rxjavatest.kotlin.leetcode

class numMagicSquaresInside{

    private fun isValid(grid: Array<IntArray>, si:Int, sj:Int):Boolean{

        val check = grid[si][sj]+grid[si][sj+1]+grid[si][sj+2]
        for(i in 1..2){
            var sum = 0
            for(j in 0..2){
                sum += grid[i+si][j+sj]
            }
            if(sum != check){
                return false
            }
        }

        for(j in 0..2){
            var sum = 0
            for(i in 0..2){
                sum += grid[i+si][j+sj]
            }
            if(sum != check){
                return false
            }
        }

        var sum = 0
        for(i in 0..2){
            sum += grid[si+i][sj+i]
        }
        if(sum != check){
            return false
        }

        sum = 0
        for(i in 0..2){
            sum += grid[si+2-i][sj+i]
        }
        if(sum != check){
            return false
        }

        val num = IntArray(10)
        for(j in 0..2){
            for(i in 0..2){

                if(grid[i+si][j+sj] > 10 || grid[i+si][j+sj] < 1){
                    return false
                }
                num[grid[i+si][j+sj]]++
                if(num[grid[i+si][j+sj]] > 1){
                    return false
                }
            }
        }

        return true
    }

    fun numMagicSquaresInside(grid: Array<IntArray>): Int {

        var ans = 0
        for(i in 0..grid.size-3){
            for(j in 0..grid[0].size-3){
                if(isValid(grid, i, j)){
                    ans++
                }
            }
        }

        return ans
    }

    fun test(){
        println(numMagicSquaresInside(arrayOf(intArrayOf(4,3,8,4),intArrayOf(9,5,1,9),intArrayOf(2,7,6,2))))
    }
}