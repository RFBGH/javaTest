package com.rfb.demo.rxjavatest.algorithm.leetcode4

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.min

class shortestPathAllKeys {

    companion object{
        val move = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    }

    private data class Node(val i:Int, val j:Int, val step:Int)

    private data class KeyInfo(val i:Int, val j:Int, val c:Int)

    var ans = Int.MAX_VALUE

    private fun minStep(grid: Array<String>, sI:Int, sJ:Int, eI:Int, eJ:Int, keys:HashSet<Int>):Int{

        val queue = LinkedList<Node>()
        queue.offer(Node(sI, sJ, 0))

        val gone = Array(grid.size){BooleanArray(grid[0].length){false} }
        gone[sI][sJ] = true

        while (!queue.isEmpty()){
            val cur = queue.poll()
            for(k in 0..3){

                val i = cur.i + move[k][0]
                val j = cur.j + move[k][1]

                if(i < 0 || i >= grid.size || j < 0 || j >= grid[0].length){
                    continue
                }

                if(grid[i][j] == '#' || gone[i][j]){
                    continue
                }

                if(grid[i][j] == '.'
                        || grid[i][j] == '@'
                        || grid[i][j] in 'a'..'f'
                        || grid[i][j] in 'A'..'F' && keys.contains(grid[i][j]-'A')){

                    if(i == eI && j == eJ){
                        return cur.step+1
                    }

                    gone[i][j] = true
                    queue.offer(Node(i, j, cur.step+1))
                }
            }
        }

        return -1
    }

    private fun dfs(si:Int, sj:Int, grid: Array<String>,  keyInfos:List<KeyInfo>, takes:ArrayList<Int>){

        if(takes.size == keyInfos.size){

            val keys = HashSet<Int>()
            var count = minStep(grid, si, sj, keyInfos[takes[0]].i, keyInfos[takes[0]].j, keys)
            if(count == -1){
                return
            }

            keys.add(keyInfos[takes[0]].c)
            for(i in 1 until keyInfos.size){
                val temp = minStep(grid, keyInfos[takes[i-1]].i, keyInfos[takes[i-1]].j, keyInfos[takes[i]].i, keyInfos[takes[i]].j, keys)
                if(temp == -1){
                    return
                }
                count += temp
                keys.add(keyInfos[takes[i]].c)
            }

            if(count < ans){
                ans = count
            }
            return
        }

        for(i in keyInfos.indices){

            if(takes.contains(i)){
                continue
            }

            takes.add(i)
            dfs(si, sj, grid, keyInfos, takes)
            takes.remove(i)
        }
    }

    fun shortestPathAllKeys(grid: Array<String>): Int {

        val keyInfos = ArrayList<KeyInfo>()

        var si = 0
        var sj = 0
        for(i in grid.indices){
            for(j in grid[0].indices){

                if(grid[i][j] == '@'){
                    si = i
                    sj = j
                }

                if(grid[i][j] in 'a'..'f'){
                    keyInfos.add(KeyInfo(i, j, grid[i][j]-'a'))
                }
            }
        }

        dfs(si, sj, grid, keyInfos, ArrayList())

        if(ans == Int.MAX_VALUE){
            return -1
        }
        return ans
    }

}