package com.rfb.demo.rxjavatest.kotlin.leetcode

class stoneGame{

    lateinit var map:Array<Array<Array<Int>>>

    private fun dfs(lastMePick:Int, left:Int, right:Int, isMe:Boolean, piles: IntArray) : Int{

        var me = 0
        if(isMe){
            me = 1
        }

        if(map[me][left][right] != 0){
            return map[me][left][right]
        }

        if(left == right){

            val sumMe = if(isMe){
                piles[left]+lastMePick
            }else{
                lastMePick
            }

            map[me][left][right] = sumMe
            return sumMe
        }

        if(isMe){

            var sumMe = dfs(piles[left], left+1, right, false, piles)
            val temp = dfs(piles[right], left, right-1, false, piles)
            if(sumMe < temp){
                sumMe = temp
            }

//            println("$isMe $left $right $sumMe")

            sumMe += lastMePick
            map[me][left][right] = sumMe
            return sumMe
        }else{

            var sumMe = dfs(0, left+1, right, true, piles)
            val temp = dfs(0, left, right-1, true, piles)

            if(sumMe > temp){
                sumMe = temp
            }

//            println("$isMe $left $right $sumMe")

            sumMe += lastMePick
            map[me][left][right] = sumMe
            return sumMe
        }
    }

    fun stoneGame(piles: IntArray): Boolean {

        map = Array(2){
            Array(piles.size){
                Array(piles.size){
                    0
                }
            }
        }

        var sum = 0
        piles.forEach {
            sum += it
        }

        val sumMe = dfs(0, 0, piles.size-1, true, piles)

        println("$sum $sumMe")
        return sumMe > sum/2
    }

    fun test(){
        println(stoneGame(intArrayOf(3,7,2,3)))
    }
}