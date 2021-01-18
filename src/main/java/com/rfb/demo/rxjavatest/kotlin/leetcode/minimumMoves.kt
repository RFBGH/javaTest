package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*

class minimumMoves {

    data class Node(val head:IntArray, val tail:IntArray, val prev:Node?, val step:Int)

    fun minimumMoves(grid: Array<IntArray>): Int {

        val goneH = Array(grid.size){BooleanArray(grid[0].size)}
        val goneV = Array(grid.size){BooleanArray(grid[0].size)}

        val queue = LinkedList<Node>()
        queue.offer(Node(intArrayOf(0, 1), intArrayOf(0, 0), null, 0))
        goneH[0][0] = true
        goneH[0][1] = true

        while (!queue.isEmpty()){
            val cur = queue.poll()
            if(cur.head[0] == cur.tail[0]){

                var headI = cur.head[0]
                var headJ = cur.head[1]+1

                if(headJ < grid[0].size && grid[headI][headJ] == 0 && !goneH[headI][headJ]){
                    goneH[headI][headJ] = true
                    if(headI == grid.size-1 && headJ == grid[0].size-1){
                        return cur.step+1
                    }
                    queue.offer(Node(intArrayOf(headI, headJ), cur.head, cur, cur.step+1))
                }

                headI = cur.tail[0]+1
                headJ = cur.tail[1]
                if(cur.head[0]+1 < grid.size && grid[cur.head[0]+1][cur.head[1]] == 0 && grid[cur.tail[0]+1][cur.tail[1]] == 0
                        && !goneV[headI][headJ]){
                    goneV[headI][headJ] = true
                    queue.offer(Node(intArrayOf(headI, headJ), cur.tail, cur, cur.step+1))
                }

                headI = cur.head[0]+1
                headJ = cur.head[1]
                if(cur.head[0]+1 < grid.size && grid[cur.head[0]+1][cur.head[1]] == 0 && grid[cur.tail[0]+1][cur.tail[1]] == 0
                        && !goneH[headI][headJ]){
                    if(headI == grid.size-1 && headJ == grid[0].size-1){
                        return cur.step+1
                    }

                    goneH[headI][headJ] = true
                    queue.offer(Node(intArrayOf(headI, headJ), intArrayOf(cur.tail[0]+1, cur.tail[1]), cur, cur.step+1))
                }
            }else{

                var headI = cur.head[0]+1
                var headJ = cur.head[1]
                if(headI < grid.size && grid[headI][headJ] == 0 && !goneV[headI][headJ]){
                    goneV[headI][headJ] = true
                    queue.offer(Node(intArrayOf(headI, headJ), cur.head, cur, cur.step+1))
                }

                headI = cur.tail[0]
                headJ = cur.tail[1]+1
                if(cur.tail[1]+1 < grid[0].size && grid[cur.head[0]][cur.head[1]+1] == 0 && grid[cur.tail[0]][cur.tail[1]+1] == 0
                        && !goneH[headI][headJ]){
                    goneH[headI][headJ] = true
                    queue.offer(Node(intArrayOf(headI, headJ), cur.tail, cur, cur.step+1))
                }

                headI = cur.head[0]
                headJ = cur.head[1]+1
                if(cur.tail[1]+1 < grid[0].size && grid[cur.head[0]][cur.head[1]+1] == 0 && grid[cur.tail[0]][cur.tail[1]+1] == 0
                        && !goneV[headI][headJ]){


                    goneV[headI][headJ] = true
                    queue.offer(Node(intArrayOf(headI, headJ), intArrayOf(cur.tail[0], cur.tail[1]+1), cur, cur.step+1))
                }
            }
        }

        return -1
    }


}