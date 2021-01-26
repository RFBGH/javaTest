package com.rfb.demo.rxjavatest.algorithm.leetcode4

class shortestPathAllKeysTimeout {

    companion object{
        val move = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    }

    private data class Node(val i:Int, val j:Int, val keys:HashSet<Int>)

    var ans = Int.MAX_VALUE

    private fun dfs(grid: Array<String>, path:ArrayList<Node>, keyCount:Int){

        val cur = path.last()
        if(cur.keys.size == keyCount){
            if(ans > path.size){
                ans = path.size
            }
            return
        }

        if(path.size > ans){
            return
        }

        for(k in 0..3){

            val i = cur.i + move[k][0]
            val j = cur.j + move[k][1]
            val keys = HashSet<Int>(cur.keys)

            if(i < 0 || i >= grid.size || j < 0 || j >= grid[0].length){
                continue
            }

            if(grid[i][j] == '#'){
                continue
            }

            val checkValid:(Int, Int, HashSet<Int>)->Boolean = {i, j, keys -> path.find { it.i == i && it.j == j && it.keys == keys} == null}

            if(grid[i][j] == '.' || grid[i][j] == '@'){

                if(checkValid(i, j, keys)){
                    path.add(Node(i, j, keys))
                    dfs(grid, path, keyCount)
                    path.removeAt(path.size-1)
                }
            }else if(grid[i][j] in 'a'..'f'){

                if(keys.contains(grid[i][j]-'a')){
                    if(checkValid(i, j, keys)){
                        path.add(Node(i, j, keys))
                        dfs(grid, path, keyCount)
                        path.removeAt(path.size-1)
                    }
                }else{
                    keys.add(grid[i][j]-'a')
                    path.add(Node(i, j, keys))
                    dfs(grid, path, keyCount)
                    path.removeAt(path.size-1)
                }
            }else{

                val lock = grid[i][j]-'A'
                if(keys.contains(lock) && checkValid(i, j, keys)){
                    path.add(Node(i, j, keys))
                    dfs(grid, path, keyCount)
                    path.removeAt(path.size-1)
                }
            }
        }

    }

    fun shortestPathAllKeys(grid: Array<String>): Int {

        val path = ArrayList<Node>()

        var keyCount = 0
        for(i in grid.indices){
            for(j in grid[0].indices){

                if(grid[i][j] == '@'){
                    path.add(Node(i, j, HashSet()))
                }

                if(grid[i][j] in 'a'..'f'){
                    keyCount++
                }
            }
        }

        dfs(grid, path, keyCount)

        if(ans == Int.MAX_VALUE){
            return -1
        }
        return ans-1
    }

}