package com.rfb.demo.rxjavatest.algorithm.leetcode4

class regionsBySlashes {

    private lateinit var parent:IntArray

    private fun getLevelAndAns(s:Int):Pair<Int, Int>{

        var cur = s
        var level = 0
        while (parent[cur] != -1){
            cur = parent[cur]
            level++
        }

        return Pair(level, cur)
    }

    private fun union(u:Int?, v:Int?):Boolean{

        if(u == null || v == null){
            return false
        }

        val pairU = getLevelAndAns(u)
        val pairV = getLevelAndAns(v)

        if(pairU.second == pairV.second){
            return false
        }

        if(pairU.first < pairV.first){
            parent[pairU.second] = pairV.second
        }else{
            parent[pairV.second] = pairU.second
        }
        return true
    }

    private enum class Type{
        left,
        right,
        all
    }

    private data class Node(val i:Int, val j:Int, val type:Type){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Node

            if (i != other.i) return false
            if (j != other.j) return false
            if (type != other.type) return false

            return true
        }

        override fun hashCode(): Int {
            var result = i
            result = 31 * result + j
            result = 31 * result + type.hashCode()
            return result
        }
    }

    fun regionsBySlashes(grid: Array<String>): Int {

        val n = grid.size * grid.size * 2
        parent = IntArray(n){-1}

        val map = HashMap<Node, Int>()

        for(i in grid.indices){
            for(j in grid[i].indices){
                val c = grid[i][j]
                when (c) {
                    ' ' -> {
                        map[Node(i, j, Type.all)] = map.size
                    }
                    else -> {
                        map[Node(i, j, Type.left)] = map.size
                        map[Node(i, j, Type.right)] = map.size
                    }
                }
            }
        }

        for(i in grid.indices) {
            for (j in grid[i].indices) {

                val c = grid[i][j]
                val upC = if(i==0){null}else{grid[i-1][j]}
                val nodeUP = if(upC == ' '){Node(i-1, j, Type.all)}else if(upC == '/'){Node(i-1, j, Type.right)}
                                else if(upC == '\\'){Node(i-1, j, Type.left)}else null

                val downC = if(i == grid.size-1){null}else{grid[i+1][j]}
                val nodeDown = if(downC == ' '){Node(i+1, j, Type.all)}else if(downC == '/'){Node(i+1, j, Type.left)}
                                else if(downC == '\\'){Node(i+1, j, Type.right)}else null

                val leftC = if(j == 0){null}else{grid[i][j-1]}
                val nodeLeft = if(leftC == ' '){Node(i, j-1, Type.all)}else if(leftC == '/'){Node(i, j-1, Type.right)}
                                else if(leftC == '\\'){Node(i, j-1, Type.right)}else null

                val rightC = if(j == grid[0].length-1){null}else{grid[i][j+1]}
                val nodeRight = if(rightC == ' '){Node(i, j+1, Type.all)}else if(rightC == '/'){Node(i, j+1, Type.left)}
                                else if(rightC == '\\'){Node(i, j+1, Type.left)}else null

                if(c == ' '){
                    val node = Node(i, j, Type.all)
                    union(map[node], map[nodeUP])
                    union(map[node], map[nodeDown])
                    union(map[node], map[nodeLeft])
                    union(map[node], map[nodeRight])
                } else if(c == '/'){
                    val node1 = Node(i, j, Type.left)
                    union(map[node1], map[nodeUP])
                    union(map[node1], map[nodeLeft])

                    val node2 = Node(i, j, Type.right)
                    union(map[node2], map[nodeDown])
                    union(map[node2], map[nodeRight])
                }else{
                    val node1 = Node(i, j, Type.left)
                    union(map[node1], map[nodeDown])
                    union(map[node1], map[nodeLeft])

                    val node2 = Node(i, j, Type.right)
                    union(map[node2], map[nodeUP])
                    union(map[node2], map[nodeRight])
                }
            }
        }

        val set = HashSet<Int>()
        for (i in 0 until map.size){
            val pair = getLevelAndAns(i)
            set.add(pair.second)
        }

        return set.size
    }
}