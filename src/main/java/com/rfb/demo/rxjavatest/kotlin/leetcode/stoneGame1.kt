package com.rfb.demo.rxjavatest.kotlin.leetcode

class stoneGame1{

    private fun dfs(me :Int, other:Int, left:Int, right:Int, isMe:Boolean, piles: IntArray) : Boolean{

        if(left == right){
            var sumMe = me
            var sumOther = other;

            if(isMe){
                sumMe += piles[left]
            }else{
                sumOther += piles[left]
            }
            return sumMe > sumOther
        }

        if(isMe){

            if(dfs(me+piles[left], other, left+1, right, false, piles)){
                return true
            }

            if(dfs(me+piles[right], other, left, right-1, false, piles)){
                return true
            }

            return false
        }else{

            if(!dfs(me, other+piles[left], left+1, right, true, piles)){
                return false
            }

            if(!dfs(me, other+piles[right], left, right-1, true, piles)){
                return false
            }

            return true
        }
    }

    fun stoneGame(piles: IntArray): Boolean {
        return dfs(0, 0, 0, piles.size-1, true, piles)
    }

    fun test(){
        println(stoneGame(intArrayOf(7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24)))
    }
}