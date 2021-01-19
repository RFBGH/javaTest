package com.rfb.demo.rxjavatest.kotlin.leetcode

class countServers {

    data class Node(val i:Int, val j:Int)

    fun countServers(grid: Array<IntArray>): Int {

        val gone = Array(grid.size){BooleanArray(grid[0].size){false} }

        var sum = 0
        for(i in grid.indices){
            for(j in grid[0].indices){

                if (grid[i][j] == 0) {
                    continue
                }

                if(gone[i][j]){
                    continue
                }

                gone[i][j] = true

                val queue = ArrayList<Node>()
                var front = 0
                queue.add(Node(i, j))
                while (front < queue.size){
                    val cur = queue[front++]

                    for(ki in grid.indices){
                        if(grid[ki][cur.j] == 0){
                            continue
                        }

                        if(gone[ki][cur.j]){
                            continue
                        }
                        gone[ki][cur.j] = true
                        queue.add(Node(ki, cur.j))
                    }

                    for(kj in grid[0].indices){

                        if(grid[cur.i][kj] == 0){
                            continue
                        }

                        if(gone[cur.i][kj]){
                            continue
                        }
                        gone[cur.i][kj] = true
                        queue.add(Node(cur.i, kj))
                    }
                }

                if(queue.size != 1){
                    sum += queue.size
                }
            }
        }

        return sum
    }

}