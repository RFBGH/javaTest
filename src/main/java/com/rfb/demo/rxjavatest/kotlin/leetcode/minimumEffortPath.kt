package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*

class minimumEffortPath {

    data class Node(val i:Int, val j:Int)

    private val moves = arrayListOf(intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0))

    private fun reach(heights: Array<IntArray>, hold:Int):Boolean{

        val gone = Array(heights.size){Array(heights[0].size){false}}
        gone[0][0] = true
        val queue = LinkedList<Node>()
        queue.offer(Node(0, 0))
        while (!queue.isEmpty()){
            val cur = queue.poll()

            for(move in moves){

                val i = cur.i + move[0]
                val j = cur.j + move[1]

                if(i < 0 || i >= heights.size || j < 0 || j >= heights[0].size){
                    continue
                }

                if(gone[i][j]){
                    continue
                }

                if(Math.abs(heights[i][j] - heights[cur.i][cur.j]) > hold){
                    continue
                }

                gone[i][j] = true

                if(i == heights.size-1 && j == heights[0].size-1){
                    return true
                }

                queue.offer(Node(i, j))

            }
        }

        return false
    }

    fun minimumEffortPath(heights: Array<IntArray>): Int {

        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE

        heights.forEach {
            it.forEach {
                h ->
                if(min > h){
                    min = h
                }

                if(max < h){
                    max = h
                }
            }
        }

        var left = 0
        var right = max-min

        while (true){

            if(left == right){
                break
            }

            val mid = (left + right) / 2
            if(!reach(heights, mid)){
                left = mid+1
            }else{
                right = mid
            }
        }

        return left
    }
}